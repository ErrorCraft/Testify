package net.errorcraft.testify.api.assertion.block;

import net.errorcraft.testify.api.assertion.base.IntsAssert;
import net.minecraft.gametest.framework.GameTestException;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.function.Consumer;
import java.util.function.Supplier;

/// Contains methods for making assertions about block states.
@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface BlockStateAssert {
    /// Tests whether the block state's block is the specified block.
    /// @param id The block id.
    /// @return This [BlockStateAssert] instance.
    /// @throws GameTestException If the block state's block is not the specified block.
    BlockStateAssert is(ResourceKey<Block> id);

    /// Tests whether the block state's block is not the specified block.
    /// @param id The block id.
    /// @return This [BlockStateAssert] instance.
    /// @throws GameTestException If the block state's block is the specified block.
    BlockStateAssert isNot(ResourceKey<Block> id);

    /// Tests whether the block state's block is part of the `tag` block tag.
    /// @param tag The block tag.
    /// @return This [BlockStateAssert] instance.
    /// @throws GameTestException If the block state's block is not part of the `tag` block tag.
    BlockStateAssert is(TagKey<Block> tag);

    /// Tests whether the block state's block is not part of the `tag` block tag.
    /// @param tag The block tag.
    /// @return This [BlockStateAssert] instance.
    /// @throws GameTestException If the block state's block is part of the `tag` block tag.
    BlockStateAssert isNot(TagKey<Block> tag);

    /// Tests whether the block state has a value of `expectedValue` for the block state property `property`.
    /// @param property The block state property.
    /// @param expectedValue The expected block state property value.
    /// @return This [BlockStateAssert] instance.
    /// @param <T> The type of the block state property.
    /// @throws GameTestException If the block state does not have block state property `property` or if the actual block state property value and `expectedValue` are not equal.
    /// @see BlockStateAssert#hasPropertyValue(Property, Comparable, Supplier)
    /// @see BlockStateAssert#hasPropertyValue(IntegerProperty, Consumer)
    <T extends Comparable<T>> BlockStateAssert hasPropertyValue(Property<T> property, T expectedValue);

    /// Tests whether the block state does not have a value of `unexpectedValue` for the block state property `property`.
    /// @param property The block state property.
    /// @param unexpectedValue The unexpected block state property value.
    /// @return This [BlockStateAssert] instance.
    /// @param <T> The type of the block state property.
    /// @throws GameTestException If the block state does not have block state property `property` or if the actual block state property value and `unexpectedValue` are equal.
    /// @see BlockStateAssert#doesNotHavePropertyValue(Property, Comparable, Supplier)
    <T extends Comparable<T>> BlockStateAssert doesNotHavePropertyValue(Property<T> property, T unexpectedValue);

    /// Tests whether the block state has a value of `expectedValue` for the block state property `property`.
    /// @param property The block state property.
    /// @param expectedValue The expected block state property value.
    /// @param message The message to supply when the actual block state property value and `expectedValue` are not equal.
    /// @return This [BlockStateAssert] instance.
    /// @param <T> The type of the block state property.
    /// @throws GameTestException If the block state does not have block state property `property` or if the actual block state property value and `expectedValue` are not equal.
    /// @see BlockStateAssert#hasPropertyValue(Property, Comparable)
    /// @see BlockStateAssert#hasPropertyValue(IntegerProperty, Consumer)
    <T extends Comparable<T>> BlockStateAssert hasPropertyValue(Property<T> property, T expectedValue, Supplier<String> message);

    /// Tests whether the block state does not have a value of `unexpectedValue` for the block state property `property`.
    /// @param property The block state property.
    /// @param unexpectedValue The unexpected block state property value.
    /// @param message The message to supply when the actual block state property value and `unexpectedValue` are equal.
    /// @return This [BlockStateAssert] instance.
    /// @param <T> The type of the block state property.
    /// @throws GameTestException If the block state does not have block state property `property` or if the actual block state property value and `unexpectedValue` are equal.
    /// @see BlockStateAssert#doesNotHavePropertyValue(Property, Comparable)
    <T extends Comparable<T>> BlockStateAssert doesNotHavePropertyValue(Property<T> property, T unexpectedValue, Supplier<String> message);

    /// Tests for the int block state property value.
    /// @param property The int block state property.
    /// @param value A consumer with a new [IntsAssert] to test the int block state property value.
    /// @return This [BlockStateAssert] instance.
    /// @throws GameTestException If the block state does not have int block state property `property`.
    /// @see BlockStateAssert#hasPropertyValue(Property, Comparable)
    /// @see BlockStateAssert#hasPropertyValue(Property, Comparable, Supplier)
    BlockStateAssert hasPropertyValue(IntegerProperty property, Consumer<IntsAssert> value);
}
