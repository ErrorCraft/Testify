package net.errorcraft.testify.api.assertion.fluid;

import net.minecraft.gametest.framework.GameTestException;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

/// Contains methods for making assertions about fluid states.
@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface FluidStateAssert {
    /// Tests whether the fluid state's fluid is the specified fluid.
    /// @param id The fluid id.
    /// @return This [FluidStateAssert] instance.
    /// @throws GameTestException If the fluid state's fluid is not the specified fluid.
    FluidStateAssert is(ResourceKey<Fluid> id);

    /// Tests whether the fluid state's fluid is not the specified fluid.
    /// @param id The fluid id.
    /// @return This [FluidStateAssert] instance.
    /// @throws GameTestException If the fluid state's fluid is the specified fluid.
    FluidStateAssert isNot(ResourceKey<Fluid> id);

    /// Tests whether the fluid state's fluid is part of the `tag` fluid tag.
    /// @param tag The fluid tag.
    /// @return This [FluidStateAssert] instance.
    /// @throws GameTestException If the fluid state's fluid is not part of the `tag` fluid tag.
    FluidStateAssert is(TagKey<Fluid> tag);

    /// Tests whether the fluid state's fluid is not part of the `tag` fluid tag.
    /// @param tag The fluid tag.
    /// @return This [FluidStateAssert] instance.
    /// @throws GameTestException If the fluid state's fluid is part of the `tag` fluid tag.
    FluidStateAssert isNot(TagKey<Fluid> tag);
}
