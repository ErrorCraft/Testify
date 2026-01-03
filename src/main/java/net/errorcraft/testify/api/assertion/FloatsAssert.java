package net.errorcraft.testify.api.assertion;

import net.minecraft.gametest.framework.GameTestHelper;

import java.util.Objects;

public class FloatsAssert {
    private final GameTestHelper helper;
    private final float value;
    private final String name;

    FloatsAssert(GameTestHelper helper, float value) {
        this(helper, value, "float");
    }

    FloatsAssert(GameTestHelper helper, float value, String name) {
        this.helper = Objects.requireNonNull(helper);
        this.value = value;
        this.name = Objects.requireNonNull(name);
    }

    public FloatsAssert equals(float expected) {
        return this.equals(expected, this.name);
    }

    public FloatsAssert equals(float expected, String name) {
        if (this.value != expected) {
            throw this.helper.assertionException("test.error.value_not_equal", name, expected, this.value);
        }

        return this;
    }
}
