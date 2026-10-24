package net.errorcraft.testify.internal.assertion.fluid;

import net.errorcraft.testify.api.Asserter;
import net.errorcraft.testify.api.assertion.fluid.FluidStateAssert;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

public class FluidStateAssertImpl implements FluidStateAssert {
    private final Asserter asserter;
    private final BlockPos pos;
    private final FluidState state;
    private final String name;

    private FluidStateAssertImpl(Asserter asserter, BlockPos pos, FluidState state, String name) {
        this.asserter = asserter;
        this.pos = pos;
        this.state = state;
        this.name = name;
    }

    public static FluidStateAssert create(Asserter asserter, BlockPos pos, FluidState state, String name) {
        return new FluidStateAssertImpl(asserter, pos, state, name);
    }

    @Override
    public FluidStateAssert is(ResourceKey<Fluid> id) {
        if (this.state.is(id)) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            this.pos,
            "test.error.registry.expected_id",
            this.name,
            this.state.typeHolder().getRegisteredName(),
            id.identifier()
        );
    }

    @Override
    public FluidStateAssert isNot(ResourceKey<Fluid> id) {
        if (!this.state.is(id)) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            this.pos,
            "test.error.registry.did_not_expect_id",
            this.name,
            id.identifier()
        );
    }

    @Override
    public FluidStateAssert is(TagKey<Fluid> tag) {
        if (this.state.is(tag)) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            this.pos,
            "test.error.registry.expected_tag",
            this.name,
            tag.location()
        );
    }

    @Override
    public FluidStateAssert isNot(TagKey<Fluid> tag) {
        if (!this.state.is(tag)) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            this.pos,
            "test.error.registry.did_not_expect_tag",
            this.name,
            tag.location()
        );
    }
}
