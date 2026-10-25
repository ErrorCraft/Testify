package net.errorcraft.testify.api.assertion.base;

import net.minecraft.gametest.framework.GameTestException;

import java.util.function.Consumer;

/// Contains methods for making assertions about ints.
///
/// @see LongsAssert
/// @see FloatsAssert
/// @see DoublesAssert
@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface IntsAssert {
    /// Tests whether the value is equal to `expected`.
    /// @param expected The int the value must be equal to.
    /// @return This [IntsAssert] instance.
    /// @throws GameTestException If the value does not equal `expected`.
    IntsAssert equals(int expected);

    /// Tests whether the value is not equal to `unexpected`.
    /// @param unexpected The int the value must not be equal to.
    /// @return This [IntsAssert] instance.
    /// @throws GameTestException If the value equals `unexpected`.
    IntsAssert doesNotEqual(int unexpected);

    /// Tests whether the value is greater than or equal to `expectedMinimum`.
    /// @param expectedMinimum The int the value must be greater than or equal to.
    /// @return This [IntsAssert] instance.
    /// @throws GameTestException If the value is less than `expectedMinimum`.
    IntsAssert isAtLeast(int expectedMinimum);

    /// Tests whether the value is less than or equal to `expectedMaximum`.
    /// @param expectedMaximum The int the value must be less than or equal to.
    /// @return This [IntsAssert] instance.
    /// @throws GameTestException If the value is greater than `expectedMaximum`.
    IntsAssert isAtMost(int expectedMaximum);

    /// Tests whether the value is greater than `expectedExclusiveMinimum`.
    /// @param expectedExclusiveMinimum The int the value must be greater than.
    /// @return This [IntsAssert] instance.
    /// @throws GameTestException If the value is less than or equal to `expectedExclusiveMinimum`.
    IntsAssert isGreaterThan(int expectedExclusiveMinimum);

    /// Tests whether the value is less than `expectedExclusiveMaximum`.
    /// @param expectedExclusiveMaximum The int the value must be less than.
    /// @return This [IntsAssert] instance.
    /// @throws GameTestException If the value is greater than or equal to `expectedExclusiveMaximum`.
    IntsAssert isLessThan(int expectedExclusiveMaximum);

    /// Tests whether the value is greater than 0.
    /// @return This [IntsAssert] instance.
    /// @throws GameTestException If the value is less than or equal to 0.
    IntsAssert isPositive();

    /// Tests whether the value is less than 0.
    /// @return This [IntsAssert] instance.
    /// @throws GameTestException If the value is greater than or equal to 0.
    IntsAssert isNegative();

    /// Tests whether the value is greater than or equal to 0.
    /// @return This [IntsAssert] instance.
    /// @throws GameTestException If the value is less than 0.
    IntsAssert isNonNegative();

    /// Tests whether the value is less than or equal to 0.
    /// @return This [IntsAssert] instance.
    /// @throws GameTestException If the value is greater than 0.
    IntsAssert isNonPositive();

    /// Tests for the floored remainder of the value when divided by `divisor`.
    /// @param divisor The divisor.
    /// @param remainder A consumer with a new [IntsAssert] to test the floored remainder when dividing the value by `divisor`.
    /// @return This [IntsAssert] instance.
    /// @throws GameTestException If `divisor` is 0.
    IntsAssert remainder(int divisor, Consumer<IntsAssert> remainder);
}
