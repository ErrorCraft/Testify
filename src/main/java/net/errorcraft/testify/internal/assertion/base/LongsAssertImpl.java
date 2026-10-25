package net.errorcraft.testify.internal.assertion.base;

import net.errorcraft.testify.api.Asserter;
import net.errorcraft.testify.api.assertion.base.LongsAssert;

import java.util.function.Consumer;

public class LongsAssertImpl implements LongsAssert {
    private final Asserter asserter;
    private final long value;
    private final String name;

    private LongsAssertImpl(Asserter asserter, long value, String name) {
        this.asserter = asserter;
        this.value = value;
        this.name = name;
    }

    public static LongsAssert create(Asserter helper, long value, String name) {
        return new LongsAssertImpl(helper, value, name);
    }

    @Override
    public LongsAssert equals(long expected) {
        if (this.value == expected) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.numeric.expected_equal",
            this.name,
            expected,
            this.value
        );
    }

    @Override
    public LongsAssert doesNotEqual(long unexpected) {
        if (this.value != unexpected) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.numeric.expected_not_equal",
            this.name,
            unexpected,
            this.value
        );
    }

    @Override
    public LongsAssert isAtLeast(long expectedMinimum) {
        if (this.value >= expectedMinimum) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.numeric.expected_at_least",
            this.name,
            expectedMinimum,
            this.value
        );
    }

    @Override
    public LongsAssert isAtMost(long expectedMaximum) {
        if (this.value <= expectedMaximum) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.numeric.expected_at_most",
            this.name,
            expectedMaximum,
            this.value
        );
    }

    @Override
    public LongsAssert isGreaterThan(long expectedExclusiveMinimum) {
        if (this.value > expectedExclusiveMinimum) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.numeric.expected_greater_than",
            this.name,
            expectedExclusiveMinimum,
            this.value
        );
    }

    @Override
    public LongsAssert isLessThan(long expectedExclusiveMaximum) {
        if (this.value < expectedExclusiveMaximum) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.numeric.expected_less_than",
            this.name,
            expectedExclusiveMaximum,
            this.value
        );
    }

    @Override
    public LongsAssert isPositive() {
        if (this.value > 0) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.numeric.expected_positive",
            this.name,
            this.value
        );
    }

    @Override
    public LongsAssert isNegative() {
        if (this.value < 0) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.numeric.expected_negative",
            this.name,
            this.value
        );
    }

    @Override
    public LongsAssert isNonNegative() {
        if (this.value >= 0) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.numeric.expected_non_negative",
            this.name,
            this.value
        );
    }

    @Override
    public LongsAssert isNonPositive() {
        if (this.value <= 0) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.numeric.expected_non_positive",
            this.name,
            this.value
        );
    }

    @Override
    public LongsAssert remainder(long divisor, Consumer<LongsAssert> remainder) {
        if (divisor == 0) {
            throw this.asserter.helper().assertionException("test.error.numeric.invalid_divisor");
        }

        remainder.accept(
            this.asserter.longs(
                Math.floorMod(this.value, divisor),
                this.name + " (remainder of " + this.value + " / " + divisor + ")"
            )
        );
        return this;
    }
}
