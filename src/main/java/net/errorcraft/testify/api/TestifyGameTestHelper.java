package net.errorcraft.testify.api;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.gametest.framework.GameTestAssertException;
import net.minecraft.gametest.framework.GameTestAssertPosException;
import net.minecraft.gametest.framework.GameTestException;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.GameTestInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/// A custom [GameTestHelper] that provides extra utility methods and a way to assert common elements.
/// You may extend this class and use it in your GameTest methods if you need custom interactions.
///
/// @see Asserter
@SuppressWarnings("unused")
public class TestifyGameTestHelper extends GameTestHelper {
    public TestifyGameTestHelper(GameTestInfo testInfo) {
        super(testInfo);
    }

    /// Creates a new [Asserter].
    /// @return A new [Asserter].
    public Asserter assertThat() {
        return new Asserter(this);
    }

    /// Creates a [GameTestAssertException] with the specified description.
    /// @param description A description supplier.
    /// @return A new [GameTestAssertException].
    public GameTestAssertException assertionException(Supplier<String> description) {
        return this.assertionException(Component.literal(description.get()));
    }

    /// Creates a [GameTestAssertPosException] with the specified block position and description.
    /// @param pos The position the assertion failed at.
    /// @param description A description supplier.
    /// @return A new [GameTestAssertPosException].
    public GameTestAssertPosException assertionException(BlockPos pos, Supplier<String> description) {
        return this.assertionException(pos, Component.literal(description.get()));
    }

    /// Gets the [FluidState] within the structure at the specified block position.
    /// @param pos The block position.
    /// @return The [FluidState] at `pos`.
    public FluidState getFluidState(BlockPos pos) {
        return this.getLevel().getFluidState(this.absolutePos(pos));
    }

    /// Finds entities with entity type `type` at the specified block position.
    /// @param type The entity type.
    /// @param pos The block position.
    /// @return A list of entities found at `pos`.
    /// @param <E> The type of the entity.
    public <E extends Entity> List<E> findEntitiesAt(EntityType<E> type, BlockPos pos) {
        return this.getLevel()
            .getEntities(
                type,
                new AABB(this.absolutePos(pos)),
                Entity::isAlive
            );
    }

    /// Finds a single entity with entity type `type` at the specified block position.
    /// @param type The entity type.
    /// @param pos The block position.
    /// @return The single entity found at `pos`.
    /// @param <E> The type of the entity.
    /// @throws GameTestException If no entity or more than one entity was found at `pos`.
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

    /// Creates a mock Player with the specified game mode and places it at `pos`.
    /// @param gameMode The game mode of the mock Player.
    /// @param pos The position to place the Player at.
    /// @return A new mock player.
    public Player makeMockPlayer(GameType gameMode, BlockPos pos) {
        Player player = this.makeMockPlayer(gameMode);
        player.setPos(Vec3.atBottomCenterOf(this.absolutePos(pos)));
        return player;
    }

    /// Creates a new item stack without needing to reference the static fields in the [Items] class or the static item registry itself.
    /// This is more future-proof were items to be made data-driven.
    /// @param id The item id.
    /// @return A new item stack or [ItemStack#EMPTY] if the item could not be found.
    public ItemStack createStack(ResourceKey<Item> id) {
        return this.getHolder(id)
            .map(ItemStack::new)
            .orElse(ItemStack.EMPTY);
    }

    /// Creates a new item stack without needing to reference the static fields in the [Items] class or the static item registry itself.
    /// This is more future-proof were items to be made data-driven.
    /// @param id The item id.
    /// @param count The count.
    /// @return A new item stack or [ItemStack#EMPTY] if the item could not be found.
    public ItemStack createStack(ResourceKey<Item> id, int count) {
        return this.getHolder(id)
            .map(item -> new ItemStack(item, count))
            .orElse(ItemStack.EMPTY);
    }

    /// Creates a new item stack without needing to reference the static fields in the [Items] class or the static item registry itself.
    /// This is more future-proof were items to be made data-driven.
    /// @param id The item id.
    /// @param count The count.
    /// @param components The data components.
    /// @return A new item stack or [ItemStack#EMPTY] if the item could not be found.
    public ItemStack createStack(ResourceKey<Item> id, int count, DataComponentPatch components) {
        return this.getHolder(id)
            .map(item -> new ItemStack(item, count, components))
            .orElse(ItemStack.EMPTY);
    }

    /// Gets the `type` data component from `stack` and throws a [GameTestException] if the data component is not present.
    /// This is a safer and more direct way to obtain a data component without needing to check or throw anything yourself.
    /// @param stack The item stack.
    /// @param type The data component type.
    /// @return A non-null
    /// @param <T> The type of the data component.
    /// @throws GameTestException If the `type` data component is not present on `stack`.
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

    /// Gets an optional [Holder.Reference] from the registry specified by `id`.
    /// @param id The holder id.
    /// @return An [Optional] with the holder as specified by `id` or [Optional#empty()] if the holder could not be found.
    /// @param <T> The type of the holder.
    public <T> Optional<Holder.Reference<T>> getHolder(ResourceKey<T> id) {
        return this.getLevel()
            .registryAccess()
            .get(id);
    }

    /// Makes `player` interact with the block at `pos` on the `direction` side of that block.
    /// @param pos The block position of the block the Player is interacting with.
    /// @param player The Player interacting with the block.
    /// @param direction The side of the block the Player is interacting with.
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
