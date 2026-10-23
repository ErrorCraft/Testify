package net.errorcraft.testify.api.assertion.base;

import java.util.function.Consumer;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface IntsAssert {
    IntsAssert equals(int expected);
    IntsAssert doesNotEqual(int expected);
    IntsAssert isAtLeast(int expectedMinimum);
    IntsAssert isAtMost(int expectedMaximum);
    IntsAssert isGreaterThan(int expectedExclusiveMinimum);
    IntsAssert isLessThan(int expectedExclusiveMaximum);
    IntsAssert isPositive();
    IntsAssert isNegative();
    IntsAssert isNonNegative();
    IntsAssert isNonPositive();
    IntsAssert congruent(int modulus, Consumer<IntsAssert> congruent);
}
