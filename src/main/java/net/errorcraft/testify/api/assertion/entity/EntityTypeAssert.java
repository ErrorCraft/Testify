package net.errorcraft.testify.api.assertion.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestException;
import net.minecraft.world.entity.Entity;

/// Contains methods for making assertions about entity types.
@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface EntityTypeAssert {
    /// Tests whether any entity with the entity type exists anywhere within the GameTest's bounds.
    /// @return This [EntityTypeAssert] instance.
    /// @throws GameTestException If no entity with the entity type is found.
    EntityTypeAssert exists();

    /// Tests whether any entity with the entity type exists within the specified block position.
    /// @param pos The block position.
    /// @return This [EntityTypeAssert] instance.
    /// @throws GameTestException If no entity with the entity type is found at the specified block position.
    EntityTypeAssert existsAt(BlockPos pos);

    /// Tests whether a single entity with the entity type exists withing the specified block position and tests for it.
    /// @param pos The block position
    /// @param entityClass The class of the entity.
    /// @param entity A consumer with a new [EntityTypeAssert] for making assertions about the entity [E].
    /// @return This [EntityTypeAssert] instance.
    /// @param <A> The type of the entity asserter.
    /// @param <E> The type of the entity.
    /// @throws GameTestException If no entity with the entity type is found at the specified block position, if multiple entities are found at the specified block position or if the found entity is not an instance of [E].
    <A extends EntityAssert<A, E>, E extends Entity> EntityTypeAssert existsAt(BlockPos pos, Class<E> entityClass, EntityConsumer<A, E> entity);

    /// Tests whether no entity with the entity type exists anywhere within the GameTest's bounds.
    /// @return This [EntityTypeAssert] instance.
    /// @throws GameTestException If any entity with the entity type is found within the GameTest's bounds.
    EntityTypeAssert doesNotExist();

    @FunctionalInterface
    interface EntityConsumer<A extends EntityAssert<A, E>, E extends Entity> {
        void accept(EntityAssert<A, E> entity);
    }
}
