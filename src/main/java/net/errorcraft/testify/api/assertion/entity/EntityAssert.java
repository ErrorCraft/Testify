package net.errorcraft.testify.api.assertion.entity;

import net.errorcraft.testify.api.assertion.base.FloatsAssert;
import net.minecraft.world.entity.Entity;

import java.util.function.Consumer;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface EntityAssert<E extends Entity> {
    E entity();
    EntityAssert<E> xRotation(Consumer<FloatsAssert> xRotation);
    EntityAssert<E> yRotation(Consumer<FloatsAssert> yRotation);
}
