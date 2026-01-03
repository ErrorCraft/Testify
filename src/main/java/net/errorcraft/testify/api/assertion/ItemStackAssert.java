package net.errorcraft.testify.api.assertion;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;
import java.util.function.Consumer;

@SuppressWarnings("UnusedReturnValue")
public class ItemStackAssert {
    private final GameTestHelper helper;
    private final ItemStack stack;

    ItemStackAssert(GameTestHelper helper, ItemStack stack) {
        this.helper = Objects.requireNonNull(helper);
        this.stack = Objects.requireNonNull(stack);
    }

    public ItemStackAssert is(ResourceKey<Item> id) {
        if (!this.stack.is(id)) {
            throw this.helper.assertionException("test.error.item_stack.unexpected_item", id.identifier(), this.stack.typeHolder().getRegisteredName());
        }

        return this;
    }

    public ItemStackAssert isEmpty() {
        if (!this.stack.isEmpty()) {
            throw this.helper.assertionException("test.error.item_stack.is_not_empty", this.stack.typeHolder().getRegisteredName());
        }

        return this;
    }

    public ItemStackAssert isNotEmpty() {
        if (this.stack.isEmpty()) {
            throw this.helper.assertionException("test.error.item_stack.is_empty");
        }

        return this;
    }

    public ItemStackAssert assertCount(CountConsumer countAsserter) {
        countAsserter.accept(Assert.ints(this.helper, this.stack.getCount(), "item stack count"));
        return this;
    }

    public <T> ItemStackAssert hasComponent(DataComponentType<T> type) {
        if (!this.stack.has(type)) {
            throw this.helper.assertionException("test.error.item_stack.unknown_data_component", type);
        }

        return this;
    }

    public <T> ItemStackAssert hasComponent(DataComponentType<T> type, Consumer<T> assertion) {
        assertion.accept(this.helper.testify$getDataComponent(this.stack, type));
        return this;
    }

    public <T> ItemStackAssert doesNotHaveComponent(DataComponentType<T> type) {
        if (this.stack.has(type)) {
            throw this.helper.assertionException("test.error.item_stack.invalid_data_component", type);
        }

        return this;
    }

    @FunctionalInterface
    public interface CountConsumer {
        void accept(IntsAssert count);
    }
}
