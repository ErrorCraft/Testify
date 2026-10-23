package net.errorcraft.testify.api.assertion.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface EntityTypeAssert {
    EntityTypeAssert existsAt(BlockPos pos);
    <A extends EntityAssert<A, E>, E extends Entity> EntityTypeAssert existsAt(BlockPos pos, Class<E> entityClass, EntityConsumer<A, E> entity);
    EntityTypeAssert doesNotExist();

    @FunctionalInterface
    interface EntityConsumer<A extends EntityAssert<A, E>, E extends Entity> {
        void accept(EntityAssert<A, E> entity);
    }
}
