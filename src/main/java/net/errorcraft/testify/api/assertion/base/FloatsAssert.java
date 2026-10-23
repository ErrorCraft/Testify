package net.errorcraft.testify.api.assertion.base;

import java.util.function.Consumer;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface FloatsAssert {
    FloatsAssert equals(float expected);
    FloatsAssert equals(float expected, float epsilon);
    FloatsAssert doesNotEqual(float expected);
    FloatsAssert doesNotEqual(float expected, float epsilon);
    FloatsAssert isAtLeast(float expectedMinimum);
    FloatsAssert isAtMost(float expectedMaximum);
    FloatsAssert isGreaterThan(float expectedExclusiveMinimum);
    FloatsAssert isLessThan(float expectedExclusiveMaximum);
    FloatsAssert isPositive();
    FloatsAssert isNegative();
    FloatsAssert isNonNegative();
    FloatsAssert isNonPositive();
    FloatsAssert congruent(float modulus, Consumer<FloatsAssert> congruent);
}
