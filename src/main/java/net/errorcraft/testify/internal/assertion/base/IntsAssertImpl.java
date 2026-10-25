package net.errorcraft.testify.internal.assertion.base;

import net.errorcraft.testify.api.Asserter;
import net.errorcraft.testify.api.assertion.base.IntsAssert;

import java.util.function.Consumer;

public class IntsAssertImpl implements IntsAssert {
    private final Asserter asserter;
    private final int value;
    private final String name;

    private IntsAssertImpl(Asserter asserter, int value, String name) {
        this.asserter = asserter;
        this.value = value;
        this.name = name;
    }

    public static IntsAssert create(Asserter helper, int value, String name) {
        return new IntsAssertImpl(helper, value, name);
    }

    @Override
    public IntsAssert equals(int expected) {
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
    public IntsAssert doesNotEqual(int unexpected) {
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
    public IntsAssert isAtLeast(int expectedMinimum) {
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
    public IntsAssert isAtMost(int expectedMaximum) {
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
    public IntsAssert isGreaterThan(int expectedExclusiveMinimum) {
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
    public IntsAssert isLessThan(int expectedExclusiveMaximum) {
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
    public IntsAssert isPositive() {
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
    public IntsAssert isNegative() {
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
    public IntsAssert isNonNegative() {
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
    public IntsAssert isNonPositive() {
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
    public IntsAssert remainder(int divisor, Consumer<IntsAssert> remainder) {
        if (divisor == 0) {
            throw this.asserter.helper().assertionException("test.error.numeric.invalid_divisor");
        }

        remainder.accept(
            this.asserter.ints(
                Math.floorMod(this.value, divisor),
                this.name + " (remainder of " + this.value + " / " + divisor + ")"
            )
        );
        return this;
    }
}
