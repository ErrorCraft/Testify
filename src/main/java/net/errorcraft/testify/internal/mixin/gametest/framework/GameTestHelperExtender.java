package net.errorcraft.testify.internal.mixin.gametest.framework;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.errorcraft.testify.internal.access.gametest.framework.GameTestHelperAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.gametest.framework.GameTestAssertException;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.Optional;

@Mixin(GameTestHelper.class)
public abstract class GameTestHelperExtender implements GameTestHelperAccess {
    @Shadow
    public abstract GameTestAssertException assertionException(String descriptionId, Object... arguments);

    @Shadow
    public abstract ServerLevel getLevel();

    @Shadow
    public abstract void useBlock(BlockPos relativePos, Player player, BlockHitResult hitResult);

    @Shadow
    public abstract Player makeMockPlayer(GameType gameType);

    @Shadow
    public abstract BlockPos absolutePos(BlockPos relativePos);

    @Override
    public <E extends Entity> List<E> testify$findEntitiesAt(EntityType<E> type, BlockPos pos) {
        return this.getLevel().getEntities(type, new AABB(this.absolutePos(pos)), Entity::isAlive);
    }

    @Override
    public <E extends Entity> E testify$findOneEntityAt(EntityType<E> type, BlockPos pos) {
        List<E> entities = this.testify$findEntitiesAt(type, pos);
        if (entities.isEmpty()) {
            throw this.assertionException("test.error.expected_entity_around", type.getDescription(), pos.getX(), pos.getY(), pos.getZ());
        }

        if (entities.size() > 1) {
            throw this.assertionException("test.error.too_many_entities", type.toShortString(), pos.getX(), pos.getY(), pos.getZ(), entities.size());
        }

        return entities.getFirst();
    }

    @Override
    public Player testify$makeMockPlayer(GameType gameMode, BlockPos pos) {
        Player player = this.makeMockPlayer(gameMode);
        player.setPos(this.absolutePos(pos).getBottomCenter());
        return player;
    }

    @Override
    public ItemStack testify$createStack(ResourceKey<Item> id) {
        return this.testify$getHolder(id)
            .map(ItemStack::new)
            .orElse(ItemStack.EMPTY);
    }

    @Override
    public ItemStack testify$createStack(ResourceKey<Item> id, int count) {
        return this.testify$getHolder(id)
            .map(item -> new ItemStack(item, count))
            .orElse(ItemStack.EMPTY);
    }

    @Override
    public ItemStack testify$createStack(ResourceKey<Item> id, int count, DataComponentPatch components) {
        return this.testify$getHolder(id)
            .map(item -> new ItemStack(item, count, components))
            .orElse(ItemStack.EMPTY);
    }

    @Override
    @NotNull
    public <T> T testify$getDataComponent(ItemStack stack, DataComponentType<T> type) {
        T component = stack.get(type);
        if (component == null) {
            throw this.assertionException("test.error.item_stack.unknown_data_component", type);
        }

        return component;
    }

    @Override
    public <T> Optional<Holder.Reference<T>> testify$getHolder(ResourceKey<T> id) {
        return this.getLevel()
            .registryAccess()
            .get(id);
    }

    @Override
    public void testify$useBlock(BlockPos pos, Player player, Direction direction) {
        BlockPos absolutePos = this.absolutePos(pos);
        this.useBlock(pos, player, new BlockHitResult(Vec3.atCenterOf(absolutePos), direction, absolutePos, true));
    }

    @ModifyReturnValue(
        method = "makeMockPlayer",
        at = @At("RETURN")
    )
    private Player setDefaultPositionSoMockPlayerIsAlwaysLoaded(Player original) {
        original.setPos(this.absolutePos(BlockPos.ZERO).getBottomCenter());
        return original;
    }
}
