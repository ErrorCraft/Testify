package net.errorcraft.testify.internal.assertion.item;

import net.errorcraft.testify.api.Asserter;
import net.errorcraft.testify.api.assertion.base.IntsAssert;
import net.errorcraft.testify.api.assertion.item.ItemStackAssert;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

@SuppressWarnings("UnusedReturnValue")
public class ItemStackAssertImpl implements ItemStackAssert {
    private final Asserter asserter;
    private final ItemStack stack;
    private final String name;

    private ItemStackAssertImpl(Asserter asserter, ItemStack stack, String name) {
        this.asserter = asserter;
        this.stack = stack;
        this.name = name;
    }

    public static ItemStackAssert create(Asserter asserter, @Nullable ItemStack stack, String name) {
        return new ItemStackAssertImpl(
            asserter,
            asserter.isNotNull(stack, "item stack"),
            name
        );
    }

    @Override
    public ItemStackAssert is(ResourceKey<Item> id) {
        this.isNotEmpty();
        if (this.stack.is(id)) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.registry.expected_id",
            this.name,
            this.stack.typeHolder().getRegisteredName(),
            id.identifier()
        );
    }

    @Override
    public ItemStackAssert isNot(ResourceKey<Item> id) {
        if (this.stack.isEmpty() || !this.stack.is(id)) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.registry.did_not_expect_id",
            this.name,
            id.identifier()
        );
    }

    @Override
    public ItemStackAssert is(TagKey<Item> tag) {
        this.isNotEmpty();
        if (this.stack.is(tag)) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.registry.expected_tag",
            this.name,
            tag.location()
        );
    }

    @Override
    public ItemStackAssert isNot(TagKey<Item> tag) {
        if (this.stack.isEmpty() || !this.stack.is(tag)) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.registry.did_not_expect_tag",
            this.name,
            tag.location()
        );
    }

    @Override
    public ItemStackAssert isEmpty() {
        if (this.stack.isEmpty()) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.item_stack.expected_empty",
            this.name,
            this.stack.typeHolder().getRegisteredName()
        );
    }

    @Override
    public ItemStackAssert isNotEmpty() {
        if (!this.stack.isEmpty()) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.item_stack.did_not_expect_empty",
            this.name
        );
    }

    @Override
    public ItemStackAssert hasCount(Consumer<IntsAssert> count) {
        count.accept(this.asserter.ints(this.stack.getCount(), this.name + " count"));
        return this;
    }

    @Override
    public <T> ItemStackAssert hasComponent(DataComponentType<T> type) {
        if (this.stack.has(type)) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.general.expected_data_component",
            this.name,
            type
        );
    }

    @Override
    public <T> ItemStackAssert hasComponent(DataComponentType<T> type, Consumer<T> assertion) {
        assertion.accept(this.asserter.helper().getDataComponent(this.stack, type));
        return this;
    }

    @Override
    public <T> ItemStackAssert doesNotHaveComponent(DataComponentType<T> type) {
        if (!this.stack.has(type)) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.general.did_not_expect_data_component",
            this.name,
            type
        );
    }
}
