package net.errorcraft.testify.api.assertion.base;

import net.minecraft.gametest.framework.GameTestException;

import java.util.function.Consumer;

/// Contains methods for making assertions about longs.
///
/// @see IntsAssert
/// @see FloatsAssert
/// @see DoublesAssert
@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface LongsAssert {
    /// Tests whether the value is equal to `expected`.
    /// @param expected The long the value must be equal to.
    /// @return This [LongsAssert] instance.
    /// @throws GameTestException If the value does not equal `expected`.
    LongsAssert equals(long expected);

    /// Tests whether the value is not equal to `unexpected`.
    /// @param unexpected The long the value must not be equal to.
    /// @return This [LongsAssert] instance.
    /// @throws GameTestException If the value equals `unexpected`.
    LongsAssert doesNotEqual(long unexpected);

    /// Tests whether the value is greater than or equal to `expectedMinimum`.
    /// @param expectedMinimum The long the value must be greater than or equal to.
    /// @return This [LongsAssert] instance.
    /// @throws GameTestException If the value is less than `expectedMinimum`.
    LongsAssert isAtLeast(long expectedMinimum);

    /// Tests whether the value is less than or equal to `expectedMaximum`.
    /// @param expectedMaximum The long the value must be less than or equal to.
    /// @return This [LongsAssert] instance.
    /// @throws GameTestException If the value is greater than `expectedMaximum`.
    LongsAssert isAtMost(long expectedMaximum);

    /// Tests whether the value is greater than `expectedExclusiveMinimum`.
    /// @param expectedExclusiveMinimum The long the value must be greater than.
    /// @return This [LongsAssert] instance.
    /// @throws GameTestException If the value is less than or equal to `expectedExclusiveMinimum`.
    LongsAssert isGreaterThan(long expectedExclusiveMinimum);

    /// Tests whether the value is less than `expectedExclusiveMaximum`.
    /// @param expectedExclusiveMaximum The long the value must be less than.
    /// @return This [LongsAssert] instance.
    /// @throws GameTestException If the value is greater than or equal to `expectedExclusiveMaximum`.
    LongsAssert isLessThan(long expectedExclusiveMaximum);

    /// Tests whether the value is greater than 0.
    /// @return This [LongsAssert] instance.
    /// @throws GameTestException If the value is less than or equal to 0.
    LongsAssert isPositive();

    /// Tests whether the value is less than 0.
    /// @return This [LongsAssert] instance.
    /// @throws GameTestException If the value is greater than or equal to 0.
    LongsAssert isNegative();

    /// Tests whether the value is greater than or equal to 0.
    /// @return This [LongsAssert] instance.
    /// @throws GameTestException If the value is less than 0.
    LongsAssert isNonNegative();

    /// Tests whether the value is less than or equal to 0.
    /// @return This [LongsAssert] instance.
    /// @throws GameTestException If the value is greater than 0.
    LongsAssert isNonPositive();

    /// Tests for the floored remainder of the value when divided by `divisor`.
    /// @param divisor The divisor.
    /// @param remainder A consumer with a new [LongsAssert] to test the floored remainder when dividing the value by `divisor`.
    /// @return This [LongsAssert] instance.
    /// @throws GameTestException If `divisor` is 0.
    LongsAssert remainder(long divisor, Consumer<LongsAssert> remainder);
}
