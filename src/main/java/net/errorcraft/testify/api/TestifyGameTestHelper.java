package net.errorcraft.testify.api;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.gametest.framework.GameTestAssertException;
import net.minecraft.gametest.framework.GameTestAssertPosException;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.GameTestInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class TestifyGameTestHelper extends GameTestHelper {
    public TestifyGameTestHelper(GameTestInfo testInfo) {
        super(testInfo);
    }

    public Asserter assertThat() {
        return new Asserter(this);
    }

    public GameTestAssertException assertionException(Supplier<String> description) {
        return this.assertionException(Component.literal(description.get()));
    }

    public GameTestAssertPosException assertionException(BlockPos pos, Supplier<String> description) {
        return this.assertionException(pos, Component.literal(description.get()));
    }

    public FluidState getFluidState(BlockPos pos) {
        return this.getLevel().getFluidState(this.absolutePos(pos));
    }

    public <E extends Entity> List<E> findEntitiesAt(EntityType<E> type, BlockPos pos) {
        return this.getLevel()
            .getEntities(
                type,
                new AABB(this.absolutePos(pos)),
                Entity::isAlive
            );
    }

    public <E extends Entity> E findOneEntityAt(EntityType<E> type, BlockPos pos) {
        List<E> entities = this.findEntitiesAt(type, pos);
        if (entities.isEmpty()) {
            throw this.assertionException(
                "test.error.expected_entity_around",
                type.getDescription(),
                pos.getX(),
                pos.getY(),
                pos.getZ()
            );
        }

        if (entities.size() > 1) {
            throw this.assertionException(
                "test.error.too_many_entities",
                type.getDescription(),
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                entities.size()
            );
        }

        return entities.getFirst();
    }

    public Player makeMockPlayer(GameType gameMode, BlockPos pos) {
        Player player = this.makeMockPlayer(gameMode);
        player.setPos(Vec3.atBottomCenterOf(this.absolutePos(pos)));
        return player;
    }

    public ItemStack createStack(ResourceKey<Item> id) {
        return this.getHolder(id)
            .map(ItemStack::new)
            .orElse(ItemStack.EMPTY);
    }

    public ItemStack createStack(ResourceKey<Item> id, int count) {
        return this.getHolder(id)
            .map(item -> new ItemStack(item, count))
            .orElse(ItemStack.EMPTY);
    }

    public ItemStack createStack(ResourceKey<Item> id, int count, DataComponentPatch components) {
        return this.getHolder(id)
            .map(item -> new ItemStack(item, count, components))
            .orElse(ItemStack.EMPTY);
    }

    public <T> T getDataComponent(ItemStack stack, DataComponentType<T> type) {
        T component = stack.get(type);
        if (component != null) {
            return component;
        }

        throw this.assertionException(
            "test.error.general.expected_data_component",
            type
        );
    }

    public <T> Optional<Holder.Reference<T>> getHolder(ResourceKey<T> id) {
        return this.getLevel()
            .registryAccess()
            .get(id);
    }

    public void interactWithBlock(BlockPos pos, Player player, Direction direction) {
        BlockPos absolutePos = this.absolutePos(pos);
        this.useBlock(
            pos,
            player,
            new BlockHitResult(
                Vec3.atCenterOf(absolutePos),
                this.getAbsoluteDirection(direction),
                absolutePos,
                false
            )
        );
    }
}
