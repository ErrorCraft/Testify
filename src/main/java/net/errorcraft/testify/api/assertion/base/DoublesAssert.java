package net.errorcraft.testify.api.assertion.base;

import java.util.function.Consumer;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface DoublesAssert {
    DoublesAssert equals(double expected);
    DoublesAssert equals(double expected, double epsilon);
    DoublesAssert doesNotEqual(double expected);
    DoublesAssert doesNotEqual(double expected, double epsilon);
    DoublesAssert isAtLeast(double expectedMinimum);
    DoublesAssert isAtMost(double expectedMaximum);
    DoublesAssert isGreaterThan(double expectedExclusiveMinimum);
    DoublesAssert isLessThan(double expectedExclusiveMaximum);
    DoublesAssert isPositive();
    DoublesAssert isNegative();
    DoublesAssert isNonNegative();
    DoublesAssert isNonPositive();
    DoublesAssert congruent(double modulus, Consumer<DoublesAssert> congruent);
}
