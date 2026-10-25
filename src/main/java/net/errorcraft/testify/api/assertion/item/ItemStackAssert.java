package net.errorcraft.testify.api.assertion.item;

import net.errorcraft.testify.api.assertion.base.IntsAssert;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.gametest.framework.GameTestException;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

/// Contains methods for making assertions about item stacks.
@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface ItemStackAssert {
    /// Tests whether the item stack's item is the specified item.
    /// @param id The item id.
    /// @return This [ItemStackAssert] instance.
    /// @throws GameTestException If the item stack's item is not the specified item.
    ItemStackAssert is(ResourceKey<Item> id);

    /// Tests whether the item stack's item is not the specified item.
    /// @param id The item id.
    /// @return This [ItemStackAssert] instance.
    /// @throws GameTestException If the item stack's item is the specified item.
    ItemStackAssert isNot(ResourceKey<Item> id);

    /// Tests whether the item stack's item is part of the `tag` item tag.
    /// @param tag The item tag.
    /// @return This [ItemStackAssert] instance.
    /// @throws GameTestException If the item stack's item is not part of the `tag` item tag.
    ItemStackAssert is(TagKey<Item> tag);

    /// Tests whether the item stack's item is not part of the `tag` item tag.
    /// @param tag The item tag.
    /// @return This [ItemStackAssert] instance.
    /// @throws GameTestException If the item stack's item is part of the `tag` item tag.
    ItemStackAssert isNot(TagKey<Item> tag);

    /// Tests whether the item stack is empty.
    /// @return This [ItemStackAssert] instance.
    /// @throws GameTestException If the item stack is not empty.
    ItemStackAssert isEmpty();

    /// Tests whether the item stack is not empty.
    /// @return This [ItemStackAssert] instance.
    /// @throws GameTestException If the item stack is empty.
    ItemStackAssert isNotEmpty();

    /// Tests whether the item stack has a specific count.
    /// @param count A consumer with a new [IntsAssert] for making assertions about the item stack count.
    /// @return This [ItemStackAssert] instance.
    ItemStackAssert hasCount(Consumer<IntsAssert> count);

    /// Tests whether the item stack has the `expectedType` data component.
    /// @param expectedType The data component type.
    /// @return This [ItemStackAssert] instance.
    /// @param <T> The type of the data component.
    /// @throws GameTestException If the item stack does not have the `expectedType` data component.
    <T> ItemStackAssert hasComponent(DataComponentType<T> expectedType);

    /// Tests whther the item stack has the `expectedType` data component.
    /// @param expectedType The data component type.
    /// @param component A consumer with the data component [T] for making assertions about the data component.
    /// @return This [ItemStackAssert] instance.
    /// @param <T> The type of the data component.
    /// @throws GameTestException If the item stack does not have the `expectedType` data component.
    <T> ItemStackAssert hasComponent(DataComponentType<T> expectedType, Consumer<T> component);

    /// Tests whether the item stack does not have the `expectedType` data component.
    /// @param unexpectedType The data component type.
    /// @return This [ItemStackAssert] instance.
    /// @param <T> The type of the data component.
    /// @throws GameTestException If the item stack has the `unexpectedType` data component.
    <T> ItemStackAssert doesNotHaveComponent(DataComponentType<T> unexpectedType);
}
