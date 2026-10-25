package net.errorcraft.testify.internal.assertion.block;

import net.errorcraft.testify.api.Asserter;
import net.errorcraft.testify.api.assertion.base.IntsAssert;
import net.errorcraft.testify.api.assertion.block.BlockStateAssert;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestException;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BlockStateAssertImpl implements BlockStateAssert {
    private final Asserter asserter;
    private final BlockPos pos;
    private final BlockState state;
    private final String name;

    private BlockStateAssertImpl(Asserter asserter, BlockPos pos, BlockState state, String name) {
        this.asserter = asserter;
        this.pos = pos;
        this.state = state;
        this.name = name;
    }

    public static BlockStateAssert create(Asserter asserter, BlockPos pos, BlockState state, String name) {
        return new BlockStateAssertImpl(asserter, pos, state, name);
    }

    @Override
    public BlockStateAssert is(ResourceKey<Block> id) {
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
    public BlockStateAssert isNot(ResourceKey<Block> id) {
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
    public BlockStateAssert is(TagKey<Block> tag) {
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
    public BlockStateAssert isNot(TagKey<Block> tag) {
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

    @Override
    public <T extends Comparable<T>> BlockStateAssert hasPropertyValue(Property<T> property, T expectedValue) {
        if (!this.state.hasProperty(property)) {
            throw this.asserter.helper().assertionException(
                this.pos,
                "test.error.block_property_missing",
                property.getName(),
                expectedValue
            );
        }

        T actual = this.state.getValue(property);
        if (expectedValue.equals(actual)) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            this.pos,
            "test.error.block_property_mismatch",
            property.getName(),
            expectedValue,
            actual
        );
    }

    @Override
    public <T extends Comparable<T>> BlockStateAssert doesNotHavePropertyValue(Property<T> property, T unexpectedValue) {
        if (!this.state.hasProperty(property)) {
            throw this.asserter.helper().assertionException(
                this.pos,
                "test.error.block_property_missing",
                property.getName(),
                unexpectedValue
            );
        }

        T actual = this.state.getValue(property);
        if (!unexpectedValue.equals(actual)) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            this.pos,
            "test.error.block.did_not_expect_property_value",
            property.getName(),
            unexpectedValue
        );
    }

    @Override
    public <T extends Comparable<T>> BlockStateAssert hasPropertyValue(Property<T> property, T expectedValue, Supplier<String> message) {
        if (!this.state.hasProperty(property)) {
            throw this.asserter.helper().assertionException(
                this.pos,
                "test.error.block_property_missing",
                property.getName(),
                expectedValue
            );
        }

        T actual = this.state.getValue(property);
        if (expectedValue.equals(actual)) {
            return this;
        }

        throw this.asserter.helper().assertionException(this.pos, message);
    }

    @Override
    public <T extends Comparable<T>> BlockStateAssert doesNotHavePropertyValue(Property<T> property, T unexpectedValue, Supplier<String> message) {
        if (!this.state.hasProperty(property)) {
            throw this.asserter.helper().assertionException(
                this.pos,
                "test.error.block_property_missing",
                property.getName(),
                unexpectedValue
            );
        }

        T actual = this.state.getValue(property);
        if (unexpectedValue.equals(actual)) {
            return this;
        }

        throw this.asserter.helper().assertionException(this.pos, message);
    }

    @Override
    public BlockStateAssert hasPropertyValue(IntegerProperty property, Consumer<IntsAssert> value) {
        if (!this.state.hasProperty(property)) {
            throw this.asserter.helper().assertionException(
                this.pos,
                "test.error.block_property_missing",
                property.getName(),
                value
            );
        }

        int actual = this.state.getValue(property);
        try {
            value.accept(this.asserter.ints(actual, property.getName()));
            return this;
        } catch (GameTestException e) {
            throw this.asserter.helper().assertionException(
                this.pos,
                e.getDescription()
            );
        }
    }
}
