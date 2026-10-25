package net.errorcraft.testify.api.assertion.base;

import net.minecraft.gametest.framework.GameTestException;

import java.util.function.Consumer;

/// Contains methods for making assertions about doubles.
///
/// @see IntsAssert
/// @see LongsAssert
/// @see FloatsAssert
@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface DoublesAssert {
    /// Tests whether the value is equal to `expected`.
    /// @param expected The float the value must be equal to.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value does not equal `expected`.
    /// @see FloatsAssert#equals(float, float)
    DoublesAssert equals(double expected);

    /// Tests whether the value is equal to `expected` within the range of the given `epsilon`.
    /// @param expected The float the value must be equal to.
    /// @param epsilon How much the value is allowed to deviate from `expected`.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If `epsilon` is 0 or if the value is not within the range `epsilon` of `expected`.
    /// @see FloatsAssert#equals(float)
    DoublesAssert equals(double expected, double epsilon);

    /// Tests whether the value is not equal to `unexpected`.
    /// @param unexpected The float the value must not be equal to.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value equals `unexpected`.
    /// @see FloatsAssert#doesNotEqual(float, float)
    DoublesAssert doesNotEqual(double unexpected);

    /// Tests whether the value is not equal to `unexpected` within the range of the given `epsilon`.
    /// @param unexpected The float the value must not be equal to.
    /// @param epsilon How much the value must deviate from `unexpected`.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If `epsilon` is 0 or if the value is within the range `epsilon` of `unexpected`.
    /// @see FloatsAssert#doesNotEqual(float)
    DoublesAssert doesNotEqual(double unexpected, double epsilon);

    /// Tests whether the value is greater than or equal to `expectedMinimum`.
    /// @param expectedMinimum The float the value must be greater than or equal to.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value is less than `expectedMinimum`.
    DoublesAssert isAtLeast(double expectedMinimum);

    /// Tests whether the value is less than or equal to `expectedMaximum`.
    /// @param expectedMaximum The float the value must be less than or equal to.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value is greater than `expectedMaximum`.
    DoublesAssert isAtMost(double expectedMaximum);

    /// Tests whether the value is greater than `expectedExclusiveMinimum`.
    /// @param expectedExclusiveMinimum The float the value must be greater than.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value is less than or equal to `expectedExclusiveMinimum`.
    DoublesAssert isGreaterThan(double expectedExclusiveMinimum);

    /// Tests whether the value is less than `expectedExclusiveMaximum`.
    /// @param expectedExclusiveMaximum The float the value must be less than.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value is greater than or equal to `expectedExclusiveMaximum`.
    DoublesAssert isLessThan(double expectedExclusiveMaximum);

    /// Tests whether the value is greater than 0.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value is less than or equal to 0.
    DoublesAssert isPositive();

    /// Tests whether the value is less than 0.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value is greater than or equal to 0.
    DoublesAssert isNegative();

    /// Tests whether the value is greater than or equal to 0.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value is less than 0.
    DoublesAssert isNonNegative();

    /// Tests whether the value is less than or equal to 0.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If the value is greater than 0.
    DoublesAssert isNonPositive();

    /// Tests for the floored remainder of the value when divided by `divisor`.
    /// @param divisor The divisor.
    /// @param remainder A consumer with a new [FloatsAssert] to test the floored remainder when dividing the value by `divisor`.
    /// @return This [FloatsAssert] instance.
    /// @throws GameTestException If `divisor` is 0.
    DoublesAssert remainder(double divisor, Consumer<DoublesAssert> remainder);
}
