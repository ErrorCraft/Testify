package net.errorcraft.testify.api;

import net.errorcraft.testify.api.assertion.base.DoublesAssert;
import net.errorcraft.testify.api.assertion.base.FloatsAssert;
import net.errorcraft.testify.api.assertion.base.IntsAssert;
import net.errorcraft.testify.api.assertion.base.LongsAssert;
import net.errorcraft.testify.api.assertion.block.BlockStateAssert;
import net.errorcraft.testify.api.assertion.entity.EntityAssert;
import net.errorcraft.testify.api.assertion.entity.EntityTypeAssert;
import net.errorcraft.testify.api.assertion.entity.LivingEntityAssert;
import net.errorcraft.testify.api.assertion.fluid.FluidStateAssert;
import net.errorcraft.testify.api.assertion.item.ItemStackAssert;
import net.errorcraft.testify.internal.assertion.base.DoublesAssertImpl;
import net.errorcraft.testify.internal.assertion.base.FloatsAssertImpl;
import net.errorcraft.testify.internal.assertion.base.IntsAssertImpl;
import net.errorcraft.testify.internal.assertion.base.LongsAssertImpl;
import net.errorcraft.testify.internal.assertion.block.BlockStateAssertImpl;
import net.errorcraft.testify.internal.assertion.entity.EntityAssertImpl;
import net.errorcraft.testify.internal.assertion.entity.EntityTypeAssertImpl;
import net.errorcraft.testify.internal.assertion.entity.LivingEntityAssertImpl;
import net.errorcraft.testify.internal.assertion.fluid.FluidStateAssertImpl;
import net.errorcraft.testify.internal.assertion.item.ItemStackAssertImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestException;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/// A class containing common assertions for use in your GameTests.
/// You may extend this class and use it in your custom [TestifyGameTestHelper] if you need custom assertions.
///
/// @see TestifyGameTestHelper
@SuppressWarnings("unused")
public class Asserter {
    private final TestifyGameTestHelper helper;

    public Asserter(TestifyGameTestHelper helper) {
        this.helper = helper;
    }

    /// The [TestifyGameTestHelper] for this asserter.
    /// @return The [TestifyGameTestHelper] for this asserter.
    public TestifyGameTestHelper helper() {
        return this.helper;
    }

    /// Tests whether `condition` is `true` and fails otherwise.
    /// @param condition The condition to check.
    /// @param message The message to supply when `condition` is `false`.
    /// @throws GameTestException If `condition` is `false`.
    @Contract("false, _ -> fail")
    public void isTrue(boolean condition, Supplier<String> message) {
        if (condition) {
            return;
        }

        throw this.helper.assertionException(message);
    }

    /// Tests whether `condition` is `false` and fails otherwise.
    /// @param condition The condition to check.
    /// @param message The message to supply when `condition` is `true`.
    /// @throws GameTestException If `condition` is `true`.
    @Contract("true, _ -> fail")
    public void isFalse(boolean condition, Supplier<String> message) {
        if (!condition) {
            return;
        }

        throw this.helper.assertionException(message);
    }

    /// Tests whether `object` is not `null` and fails otherwise.
    /// @param object The object to check.
    /// @param name A description of `object` to use in the exception when it is `null`.
    /// @return `object` if it is not `null`.
    /// @param <T> The type of the object.
    /// @throws GameTestException If `object` is `null`.
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

    /// Tests whether `value` and `expected` are considered equal as per [Objects#equals(Object, Object)].
    /// @param value The value to test.
    /// @param expected The expected value that `value` must be equal to.
    /// @param type A description of the object types to use in the exception when they are not equal.
    /// @param <T> The type of the objects.
    /// @throws GameTestException If `value` and `expected` are not equal.
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

    /// Tests whether `value` and `expected` are considered equal as per [Objects#equals(Object, Object)].
    /// @param value The value to test.
    /// @param expected The expected value that `value` must be equal to.
    /// @param message The message to supply when `value` and `expected` are not equal.
    /// @param <T> The type of the objects.
    /// @throws GameTestException If `value` and `expected` are not equal.
    @Contract("null, !null, _ -> fail; !null, null, _ -> fail")
    public <T> void areEqual(@Nullable T value, @Nullable T expected, Supplier<String> message) {
        if (Objects.equals(value, expected)) {
            return;
        }

        throw this.helper.assertionException(message);
    }

    /// Tests whether `value` is an instance of [U].
    /// @param value The value to test.
    /// @param expectedClass The class of the expected type [U].
    /// @param name A description of `value` to use in the exception when it is not an instance of [U].
    /// @param assertion The assertion to apply with `value` as [U].
    /// @param <T> The type of `value`.
    /// @param <U> The type of the expected value.
    /// @throws GameTestException If `value` is `null` or not an instance of [U].
    /// @apiNote The assertion fails if `value` is `null`.
    @Contract("null, _, _, _ -> fail")
    public <T, U extends T> void isInstance(@Nullable T value, Class<U> expectedClass, String name, Consumer<U> assertion) {
        if (expectedClass.isInstance(value)) {
            assertion.accept(expectedClass.cast(value));
            return;
        }

        throw this.helper.assertionException(
            "test.error.general.expected_type",
            name,
            expectedClass.getSimpleName(),
            value == null ? "null" : value.getClass().getSimpleName()
        );
    }

    /// Tests whether `value` is an instance of [U].
    /// @param value The value to test.
    /// @param expectedClass The class of the expected type [U].
    /// @param message The message to supply when `value` is not an instance of [U].
    /// @param assertion The assertion to apply with `value` as [U].
    /// @param <T> The type of `value`.
    /// @param <U> The type of the expected value.
    /// @throws GameTestException If `value` is `null` or not an instance of [U].
    /// @apiNote The assertion fails if `value` is `null`.
    @Contract("null, _, _, _ -> fail")
    public <T, U extends T> void isInstance(@Nullable T value, Class<U> expectedClass, Supplier<String> message, Consumer<U> assertion) {
        if (expectedClass.isInstance(value)) {
            assertion.accept(expectedClass.cast(value));
            return;
        }

        throw this.helper.assertionException(message);
    }

    /// Creates a new [IntsAssert] to test `value` against.
    /// @param value The value to test against.
    /// @return An [IntsAssert].
    public IntsAssert ints(int value) {
        return IntsAssertImpl.create(this, value, "integer");
    }

    /// Creates a new [IntsAssert] to test `value` against.
    /// @param value The value to test against.
    /// @param name A description of `value` to use in exceptions.
    /// @return An [IntsAssert].
    public IntsAssert ints(int value, String name) {
        return IntsAssertImpl.create(this, value, name);
    }

    /// Creates a new [LongsAssert] to test `value` against.
    /// @param value The value to test against.
    /// @return A [LongsAssert].
    public LongsAssert longs(long value) {
        return LongsAssertImpl.create(this, value, "integer");
    }

    /// Creates a new [LongsAssert] to test `value` against.
    /// @param value The value to test against.
    /// @param name A description of `value` to use in exceptions.
    /// @return A [LongsAssert].
    public LongsAssert longs(long value, String name) {
        return LongsAssertImpl.create(this, value, name);
    }

    /// Creates a new [FloatsAssert] to test `value` against.
    /// @param value The value to test against.
    /// @return A [FloatsAssert].
    public FloatsAssert floats(float value) {
        return FloatsAssertImpl.create(this, value, "float");
    }

    /// Creates a new [FloatsAssert] to test `value` against.
    /// @param value The value to test against.
    /// @param name A description of `value` to use in exceptions.
    /// @return A [FloatsAssert].
    public FloatsAssert floats(float value, String name) {
        return FloatsAssertImpl.create(this, value, name);
    }

    /// Creates a new [DoublesAssert] to test `value` against.
    /// @param value The value to test against.
    /// @return A [DoublesAssert].
    public DoublesAssert doubles(double value) {
        return DoublesAssertImpl.create(this, value, "integer");
    }

    /// Creates a new [DoublesAssert] to test `value` against.
    /// @param value The value to test against.
    /// @param name A description of `value` to use in exceptions.
    /// @return A [DoublesAssert].
    public DoublesAssert doubles(double value, String name) {
        return DoublesAssertImpl.create(this, value, name);
    }

    /// Creates a new [EntityTypeAssert] to test `type` against.
    /// @param type The id of the [EntityType].
    /// @return An [EntityTypeAssert].
    public EntityTypeAssert entityType(ResourceKey<EntityType<?>> type) {
        return EntityTypeAssertImpl.create(this, type);
    }

    /// Creates a new [BlockStateAssert] to test the [BlockState] at `pos` against.
    /// @param pos The position of the [BlockState] to test against.
    /// @return A [BlockStateAssert].
    public BlockStateAssert blockState(BlockPos pos) {
        BlockState state = this.helper.getBlockState(pos);
        return BlockStateAssertImpl.create(this, pos, state, "block state");
    }

    /// Creates a new [BlockStateAssert] to test the [BlockState] at `pos` against.
    /// @param pos The position of the [BlockState] to test against.
    /// @param name A description of the [BlockState] at `pos` to use in exceptions.
    /// @return A [BlockStateAssert].
    public BlockStateAssert blockState(BlockPos pos, String name) {
        BlockState state = this.helper.getBlockState(pos);
        return BlockStateAssertImpl.create(this, pos, state, name);
    }

    /// Creates a new [FluidStateAssert] to test the [FluidState] at `pos` against.
    /// @param pos The position of the [FluidState] to test against.
    /// @return A [FluidStateAssert].
    public FluidStateAssert fluidState(BlockPos pos) {
        FluidState state = this.helper.getFluidState(pos);
        return FluidStateAssertImpl.create(this, pos, state, "fluid state");
    }

    /// Creates a new [FluidStateAssert] to test the [FluidState] at `pos` against.
    /// @param pos The position of the [FluidState] to test against.
    /// @param name A description of the [FluidState] at `pos` to use in exceptions.
    /// @return A [FluidStateAssert].
    public FluidStateAssert fluidState(BlockPos pos, String name) {
        FluidState state = this.helper.getFluidState(pos);
        return FluidStateAssertImpl.create(this, pos, state, name);
    }

    /// Creates a new [EntityAssert] to test `entity` against.
    /// @param entity The entity to test against.
    /// @return An [EntityAssert].
    /// @param <A> The type of the asserter.
    /// @param <E> The type of the entity.
    /// @apiNote Fails if `entity` is `null`.
    public <A extends EntityAssert<A, E>, E extends Entity> EntityAssert<A, E> entity(@Nullable E entity) {
        return EntityAssertImpl.create(this, entity);
    }

    /// Creates a new [LivingEntityAssert] to test `entity` against.
    /// @param entity The entity to test against.
    /// @return A [LivingEntityAssert].
    /// @param <E> The type of the entity.
    /// @apiNote Fails if `entity` is `null`.
    public <E extends LivingEntity> LivingEntityAssert<E> livingEntity(@Nullable E entity) {
        return LivingEntityAssertImpl.create(this, entity);
    }

    /// Creates a new [ItemStackAssert] to test `stack` against.
    /// @param stack The item stack to test against.
    /// @return An [ItemStackAssert].
    /// @apiNote Fails if `stack` is `null`.
    public ItemStackAssert itemStack(@Nullable ItemStack stack) {
        return ItemStackAssertImpl.create(this, stack, "item stack");
    }

    /// Creates a new [ItemStackAssert] to test `stack` against.
    /// @param stack The item stack to test against.
    /// @param name A description of `stack` to use in exceptions.
    /// @return An [ItemStackAssert].
    /// @apiNote Fails if `stack` is `null`.
    public ItemStackAssert itemStack(@Nullable ItemStack stack, String name) {
        return ItemStackAssertImpl.create(this, stack, name);
    }
}
