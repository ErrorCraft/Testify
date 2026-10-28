package net.errorcraft.testify.api.assertion.entity;

import net.errorcraft.testify.api.assertion.base.DoublesAssert;
import net.errorcraft.testify.api.assertion.base.FloatsAssert;
import net.minecraft.world.entity.Entity;

import java.util.function.Consumer;

/// Contains methods for making assertions about entities.
@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface EntityAssert<A extends EntityAssert<A, E>, E extends Entity> {
    /// Gets the entity that is being tested.
    /// @return The entity that is being tested.
    E entity();

    /// Tests the entity's x position within the structure.
    /// @param xPosition A consumer with a new [DoublesAssert] to test the entity's x position.
    /// @return This [A] instance.
    A hasXPosition(Consumer<DoublesAssert> xPosition);

    /// Tests the entity's y position within the structure.
    /// @param yPosition A consumer with a new [DoublesAssert] to test the entity's y position.
    /// @return This [A] instance.
    A hasYPosition(Consumer<DoublesAssert> yPosition);

    /// Tests the entity's z position within the structure.
    /// @param zPosition A consumer with a new [DoublesAssert] to test the entity's z position.
    /// @return This [A] instance.
    A hasZPosition(Consumer<DoublesAssert> zPosition);

    /// Tests the entity's x rotation.
    /// @param xRotation A consumer with a new [FloatsAssert] to test the entity's x rotation.
    /// @return This [A] instance.
    A hasXRotation(Consumer<FloatsAssert> xRotation);

    /// Tests the entity's y rotation.
    /// @param yRotation A consumer with a new [FloatsAssert] to test the entity's y rotation.
    /// @return This [A] instance.
    A hasYRotation(Consumer<FloatsAssert> yRotation);
}
