package net.errorcraft.testify.api.assertion.block;

import net.errorcraft.testify.api.assertion.base.IntsAssert;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface BlockStateAssert {
    BlockStateAssert is(ResourceKey<Block> id);
    BlockStateAssert isNot(ResourceKey<Block> id);
    BlockStateAssert is(TagKey<Block> tag);
    BlockStateAssert isNot(TagKey<Block> tag);
    <T extends Comparable<T>> BlockStateAssert hasPropertyValue(Property<T> property, T value);
    <T extends Comparable<T>> BlockStateAssert doesNotHavePropertyValue(Property<T> property, T value);
    <T extends Comparable<T>> BlockStateAssert hasPropertyValue(Property<T> property, T value, Supplier<String> message);
    <T extends Comparable<T>> BlockStateAssert doesNotHavePropertyValue(Property<T> property, T value, Supplier<String> message);
    BlockStateAssert hasPropertyValue(IntegerProperty property, Consumer<IntsAssert> value);
}
