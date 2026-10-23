package net.errorcraft.testify.internal.assertion.item;

import net.errorcraft.testify.api.Asserter;
import net.errorcraft.testify.api.assertion.item.ItemStackAssert;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceKey;
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
        if (!this.stack.is(id)) {
            throw this.asserter.helper().assertionException(
                "test.error.general.expected_type",
                this.name,
                id.identifier(),
                this.stack.typeHolder().getRegisteredName()
            );
        }

        return this;
    }

    @Override
    public ItemStackAssert isEmpty() {
        if (!this.stack.isEmpty()) {
            throw this.asserter.helper().assertionException(
                "test.error.item_stack.expected_empty",
                this.stack.typeHolder().getRegisteredName()
            );
        }

        return this;
    }

    @Override
    public ItemStackAssert isNotEmpty() {
        if (this.stack.isEmpty()) {
            throw this.asserter.helper().assertionException("test.error.item_stack.expected_not_empty");
        }

        return this;
    }

    @Override
    public ItemStackAssert assertCount(CountConsumer countAssert) {
        countAssert.accept(this.asserter.ints(this.stack.getCount(), "item stack count"));
        return this;
    }

    @Override
    public <T> ItemStackAssert hasComponent(DataComponentType<T> type) {
        if (!this.stack.has(type)) {
            throw this.asserter.helper().assertionException("test.error.general.expected_data_component", type);
        }

        return this;
    }

    @Override
    public <T> ItemStackAssert hasComponent(DataComponentType<T> type, Consumer<T> assertion) {
        assertion.accept(this.asserter.helper().getDataComponent(this.stack, type));
        return this;
    }

    @Override
    public <T> ItemStackAssert doesNotHaveComponent(DataComponentType<T> type) {
        if (this.stack.has(type)) {
            throw this.asserter.helper().assertionException("test.error.general.did_not_expect_data_component", type);
        }

        return this;
    }
}
