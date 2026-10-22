package net.errorcraft.testify.api.assertion.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface EntityTypeAssert {
    EntityTypeAssert existsAt(BlockPos pos);
    <E extends Entity> EntityTypeAssert existsAt(BlockPos pos, Class<E> entityClass, EntityConsumer<E> entityAssert);
    EntityTypeAssert doesNotExist();

    @FunctionalInterface
    interface EntityConsumer<E extends Entity> {
        void accept(EntityAssert<E> entity);
    }
}
