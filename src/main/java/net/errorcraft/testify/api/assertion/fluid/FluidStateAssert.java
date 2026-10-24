package net.errorcraft.testify.api.assertion.fluid;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface FluidStateAssert {
    FluidStateAssert is(ResourceKey<Fluid> id);
    FluidStateAssert isNot(ResourceKey<Fluid> id);
    FluidStateAssert is(TagKey<Fluid> tag);
    FluidStateAssert isNot(TagKey<Fluid> tag);
}
