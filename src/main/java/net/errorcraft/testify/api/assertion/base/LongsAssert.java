package net.errorcraft.testify.api.assertion.base;

import java.util.function.Consumer;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface LongsAssert {
    LongsAssert equals(long expected);
    LongsAssert doesNotEqual(long expected);
    LongsAssert isAtLeast(long expectedMinimum);
    LongsAssert isAtMost(long expectedMaximum);
    LongsAssert isGreaterThan(long expectedExclusiveMinimum);
    LongsAssert isLessThan(long expectedExclusiveMaximum);
    LongsAssert isPositive();
    LongsAssert isNegative();
    LongsAssert isNonNegative();
    LongsAssert isNonPositive();
    LongsAssert congruent(long modulus, Consumer<LongsAssert> congruent);
}
