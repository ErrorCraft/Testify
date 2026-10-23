package net.errorcraft.testify.api.assertion.entity;

import net.errorcraft.testify.api.assertion.base.FloatsAssert;
import net.minecraft.world.entity.Entity;

import java.util.function.Consumer;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface EntityAssert<A extends EntityAssert<A, E>, E extends Entity> {
    E entity();
    A xRotation(Consumer<FloatsAssert> xRotation);
    A yRotation(Consumer<FloatsAssert> yRotation);
}
