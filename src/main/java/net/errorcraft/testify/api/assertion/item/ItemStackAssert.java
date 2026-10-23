package net.errorcraft.testify.api.assertion.item;

import net.errorcraft.testify.api.assertion.base.IntsAssert;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface ItemStackAssert {
    ItemStackAssert is(ResourceKey<Item> id);
    ItemStackAssert isNot(ResourceKey<Item> id);
    ItemStackAssert is(TagKey<Item> tag);
    ItemStackAssert isNot(TagKey<Item> tag);
    ItemStackAssert isEmpty();
    ItemStackAssert isNotEmpty();
    ItemStackAssert hasCount(Consumer<IntsAssert> count);
    <T> ItemStackAssert hasComponent(DataComponentType<T> type);
    <T> ItemStackAssert hasComponent(DataComponentType<T> type, Consumer<T> assertion);
    <T> ItemStackAssert doesNotHaveComponent(DataComponentType<T> type);
}
