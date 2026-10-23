package net.errorcraft.testify.internal.assertion.base;

import net.errorcraft.testify.api.Asserter;
import net.errorcraft.testify.api.assertion.base.FloatsAssert;
import net.minecraft.util.Mth;

import java.util.function.Consumer;

public class FloatsAssertImpl implements FloatsAssert {
    private final Asserter asserter;
    private final float value;
    private final String name;

    private FloatsAssertImpl(Asserter asserter, float value, String name) {
        this.asserter = asserter;
        this.value = value;
        this.name = name;
    }

    public static FloatsAssert create(Asserter helper, float value, String name) {
        return new FloatsAssertImpl(helper, value, name);
    }

    @Override
    public FloatsAssert equals(float expected) {
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
    public FloatsAssert equals(float expected, float epsilon) {
        if (epsilon <= 0.0f) {
            throw this.asserter.helper().assertionException(
                "test.error.numeric.invalid_epsilon",
                epsilon
            );
        }

        float absoluteDifference = Math.abs(this.value - expected);
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
    public FloatsAssert doesNotEqual(float expected) {
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
    public FloatsAssert doesNotEqual(float expected, float epsilon) {
        if (epsilon <= 0.0f) {
            throw this.asserter.helper().assertionException(
                "test.error.numeric.invalid_epsilon",
                epsilon
            );
        }

        float absoluteDifference = Math.abs(this.value - expected);
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
    public FloatsAssert isAtLeast(float expectedMinimum) {
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
    public FloatsAssert isAtMost(float expectedMaximum) {
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
    public FloatsAssert isGreaterThan(float expectedExclusiveMinimum) {
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
    public FloatsAssert isLessThan(float expectedExclusiveMaximum) {
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
    public FloatsAssert isPositive() {
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
    public FloatsAssert isNegative() {
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
    public FloatsAssert isNonNegative() {
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
    public FloatsAssert isNonPositive() {
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
    public FloatsAssert congruent(float modulus, Consumer<FloatsAssert> congruent) {
        if (modulus == 0) {
            throw this.asserter.helper().assertionException("test.error.numeric.invalid_modulus");
        }

        congruent.accept(
            this.asserter.floats(
                Mth.positiveModulo(this.value, modulus),
                this.name + " (congruent to " + modulus + ")"
            )
        );
        return this;
    }
}
