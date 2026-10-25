package net.errorcraft.testify.api.assertion.base;

import net.minecraft.gametest.framework.GameTestException;

import java.util.function.Consumer;

/// Contains methods for making assertions about floats.
///
/// @see IntsAssert
/// @see LongsAssert
/// @see DoublesAssert
@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface FloatsAssert {
    /// Tests whether the value is equal to `expected`.
    /// @param expected The float the value must be equal to.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value does not equal `expected`.
    /// @see FloatsAssert#equals(float, float)
    FloatsAssert equals(float expected);

    /// Tests whether the value is equal to `expected` within the range of the given `epsilon`.
    /// @param expected The float the value must be equal to.
    /// @param epsilon How much the value is allowed to deviate from `expected`.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If `epsilon` is 0 or if the value is not within the range `epsilon` of `expected`.
    /// @see FloatsAssert#equals(float)
    FloatsAssert equals(float expected, float epsilon);

    /// Tests whether the value is not equal to `unexpected`.
    /// @param unexpected The float the value must not be equal to.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value equals `unexpected`.
    /// @see FloatsAssert#doesNotEqual(float, float)
    FloatsAssert doesNotEqual(float unexpected);

    /// Tests whether the value is not equal to `unexpected` within the range of the given `epsilon`.
    /// @param unexpected The float the value must not be equal to.
    /// @param epsilon How much the value must deviate from `unexpected`.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If `epsilon` is 0 or if the value is within the range `epsilon` of `unexpected`.
    /// @see FloatsAssert#doesNotEqual(float)
    FloatsAssert doesNotEqual(float unexpected, float epsilon);

    /// Tests whether the value is greater than or equal to `expectedMinimum`.
    /// @param expectedMinimum The float the value must be greater than or equal to.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value is less than `expectedMinimum`.
    FloatsAssert isAtLeast(float expectedMinimum);

    /// Tests whether the value is less than or equal to `expectedMaximum`.
    /// @param expectedMaximum The float the value must be less than or equal to.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value is greater than `expectedMaximum`.
    FloatsAssert isAtMost(float expectedMaximum);

    /// Tests whether the value is greater than `expectedExclusiveMinimum`.
    /// @param expectedExclusiveMinimum The float the value must be greater than.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value is less than or equal to `expectedExclusiveMinimum`.
    FloatsAssert isGreaterThan(float expectedExclusiveMinimum);

    /// Tests whether the value is less than `expectedExclusiveMaximum`.
    /// @param expectedExclusiveMaximum The float the value must be less than.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value is greater than or equal to `expectedExclusiveMaximum`.
    FloatsAssert isLessThan(float expectedExclusiveMaximum);

    /// Tests whether the value is greater than 0.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value is less than or equal to 0.
    FloatsAssert isPositive();

    /// Tests whether the value is less than 0.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value is greater than or equal to 0.
    FloatsAssert isNegative();

    /// Tests whether the value is greater than or equal to 0.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value is less than 0.
    FloatsAssert isNonNegative();

    /// Tests whether the value is less than or equal to 0.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value is greater than 0.
    FloatsAssert isNonPositive();

    /// Tests for the floored remainder of the value when divided by `divisor`.
    /// @param divisor The divisor.
    /// @param remainder A consumer with a new [FloatsAssert] to test the floored remainder when dividing the value by `divisor`.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If `divisor` is 0.
    FloatsAssert remainder(float divisor, Consumer<FloatsAssert> remainder);
}
