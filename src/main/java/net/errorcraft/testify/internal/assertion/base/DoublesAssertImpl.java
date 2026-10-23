package net.errorcraft.testify.internal.assertion.base;

import net.errorcraft.testify.api.Asserter;
import net.errorcraft.testify.api.assertion.base.DoublesAssert;
import net.minecraft.util.Mth;

import java.util.function.Consumer;

public class DoublesAssertImpl implements DoublesAssert {
    private final Asserter asserter;
    private final double value;
    private final String name;

    private DoublesAssertImpl(Asserter asserter, double value, String name) {
        this.asserter = asserter;
        this.value = value;
        this.name = name;
    }

    public static DoublesAssert create(Asserter helper, double value, String name) {
        return new DoublesAssertImpl(helper, value, name);
    }

    @Override
    public DoublesAssert equals(double expected) {
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
    public DoublesAssert equals(double expected, double epsilon) {
        if (epsilon <= 0.0f) {
            throw this.asserter.helper().assertionException(
                "test.error.numeric.invalid_epsilon",
                epsilon
            );
        }

        double absoluteDifference = Math.abs(this.value - expected);
        if (absoluteDifference < epsilon) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.numeric.expected_equal.epsilon",
            this.name,
            expected,
            epsilon,
            this.value
        );
    }

    @Override
    public DoublesAssert doesNotEqual(double expected) {
        if (this.value != expected) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.numeric.expected_not_equal",
            this.name,
            expected
        );
    }

    @Override
    public DoublesAssert doesNotEqual(double expected, double epsilon) {
        if (epsilon <= 0.0f) {
            throw this.asserter.helper().assertionException(
                "test.error.numeric.invalid_epsilon",
                epsilon
            );
        }

        double absoluteDifference = Math.abs(this.value - expected);
        if (absoluteDifference >= epsilon) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.numeric.expected_not_equal.epsilon",
            this.name,
            expected,
            epsilon
        );
    }

    @Override
    public DoublesAssert isAtLeast(double expectedMinimum) {
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
    public DoublesAssert isAtMost(double expectedMaximum) {
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
    public DoublesAssert isGreaterThan(double expectedExclusiveMinimum) {
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
    public DoublesAssert isLessThan(double expectedExclusiveMaximum) {
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
    public DoublesAssert isPositive() {
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
    public DoublesAssert isNegative() {
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
    public DoublesAssert isNonNegative() {
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
    public DoublesAssert isNonPositive() {
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
    public DoublesAssert congruent(double modulus, Consumer<DoublesAssert> congruent) {
        if (modulus == 0) {
            throw this.asserter.helper().assertionException("test.error.numeric.invalid_modulus");
        }

        congruent.accept(
            this.asserter.doubles(
                Mth.positiveModulo(this.value, modulus),
                this.name + " (congruent to " + modulus + ")"
            )
        );
        return this;
    }
}
