package net.errorcraft.testify.api;

import net.errorcraft.testify.api.assertion.base.DoublesAssert;
import net.errorcraft.testify.api.assertion.base.FloatsAssert;
import net.errorcraft.testify.api.assertion.base.IntsAssert;
import net.errorcraft.testify.api.assertion.base.LongsAssert;
import net.errorcraft.testify.api.assertion.block.BlockStateAssert;
import net.errorcraft.testify.api.assertion.entity.EntityAssert;
import net.errorcraft.testify.api.assertion.entity.EntityTypeAssert;
import net.errorcraft.testify.api.assertion.entity.LivingEntityAssert;
import net.errorcraft.testify.api.assertion.item.ItemStackAssert;
import net.errorcraft.testify.internal.assertion.base.DoublesAssertImpl;
import net.errorcraft.testify.internal.assertion.base.FloatsAssertImpl;
import net.errorcraft.testify.internal.assertion.base.IntsAssertImpl;
import net.errorcraft.testify.internal.assertion.base.LongsAssertImpl;
import net.errorcraft.testify.internal.assertion.block.BlockStateAssertImpl;
import net.errorcraft.testify.internal.assertion.entity.EntityAssertImpl;
import net.errorcraft.testify.internal.assertion.entity.EntityTypeAssertImpl;
import net.errorcraft.testify.internal.assertion.entity.LivingEntityAssertImpl;
import net.errorcraft.testify.internal.assertion.item.ItemStackAssertImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class Asserter {
    private final TestifyGameTestHelper helper;

    public Asserter(TestifyGameTestHelper helper) {
        this.helper = helper;
    }

    public TestifyGameTestHelper helper() {
        return this.helper;
    }

    @Contract("false, _ -> fail")
    public void isTrue(boolean condition, Supplier<String> message) {
        if (condition) {
            return;
        }

        throw this.helper.assertionException(message);
    }

    @Contract("true, _ -> fail")
    public void isFalse(boolean condition, Supplier<String> message) {
        if (!condition) {
            return;
        }

        throw this.helper.assertionException(message);
    }

    @Contract("null, _ -> fail; !null, _ -> param1")
    public <T> T isNotNull(@Nullable T object, String name) {
        if (object != null) {
            return object;
        }

        throw this.helper.assertionException(
            "test.error.general.expected_not_null",
            name
        );
    }

    @Contract("null, !null, _ -> fail; !null, null, _ -> fail")
    public <T> void areEqual(@Nullable T value, @Nullable T expected, String type) {
        if (Objects.equals(value, expected)) {
            return;
        }

        throw helper.assertionException(
            "test.error.general.expected_type",
            type,
            String.valueOf(value),
            String.valueOf(expected)
        );
    }

    @Contract("null, !null, _ -> fail; !null, null, _ -> fail")
    public <T> void areEqual(@Nullable T value, @Nullable T expected, Supplier<String> message) {
        if (Objects.equals(value, expected)) {
            return;
        }

        throw this.helper.assertionException(message);
    }

    public <T, U extends T> void isInstance(T value, Class<U> expectedClass, String name, Consumer<U> assertion) {
        if (expectedClass.isInstance(value)) {
            assertion.accept(expectedClass.cast(value));
            return;
        }

        throw this.helper.assertionException(
            "test.error.general.expected_type",
            name,
            expectedClass.getSimpleName(),
            value.getClass().getSimpleName()
        );
    }

    public <T, U extends T> void isInstance(T value, Class<U> expectedClass, Supplier<String> message, Consumer<U> assertion) {
        if (expectedClass.isInstance(value)) {
            assertion.accept(expectedClass.cast(value));
            return;
        }

        throw this.helper.assertionException(message);
    }

    public IntsAssert ints(int value) {
        return IntsAssertImpl.create(this, value, "integer");
    }

    public IntsAssert ints(int value, String name) {
        return IntsAssertImpl.create(this, value, name);
    }

    public LongsAssert longs(long value) {
        return LongsAssertImpl.create(this, value, "integer");
    }

    public LongsAssert longs(long value, String name) {
        return LongsAssertImpl.create(this, value, name);
    }

    public FloatsAssert floats(float value) {
        return FloatsAssertImpl.create(this, value, "float");
    }

    public FloatsAssert floats(float value, String name) {
        return FloatsAssertImpl.create(this, value, name);
    }

    public DoublesAssert doubles(double value) {
        return DoublesAssertImpl.create(this, value, "integer");
    }

    public DoublesAssert doubles(double value, String name) {
        return DoublesAssertImpl.create(this, value, name);
    }

    public EntityTypeAssert entityType(ResourceKey<EntityType<?>> type) {
        return EntityTypeAssertImpl.create(this, type);
    }

    public BlockStateAssert blockState(BlockPos pos) {
        BlockState state = this.helper.getBlockState(pos);
        return BlockStateAssertImpl.create(this, pos, state, "block state");
    }

    public BlockStateAssert blockState(BlockPos pos, String name) {
        BlockState state = this.helper.getBlockState(pos);
        return BlockStateAssertImpl.create(this, pos, state, name);
    }

    public <A extends EntityAssert<A, E>, E extends Entity> EntityAssert<A, E> entity(@Nullable E entity) {
        return EntityAssertImpl.create(this, entity);
    }

    public <E extends LivingEntity> LivingEntityAssert<E> livingEntity(E entity) {
        return LivingEntityAssertImpl.create(this, entity);
    }

    public ItemStackAssert itemStack(@Nullable ItemStack stack) {
        return ItemStackAssertImpl.create(this, stack, "item stack");
    }

    public ItemStackAssert itemStack(@Nullable ItemStack stack, String name) {
        return ItemStackAssertImpl.create(this, stack, name);
    }
}
