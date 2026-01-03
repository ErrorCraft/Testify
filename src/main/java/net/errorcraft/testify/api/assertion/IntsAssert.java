package net.errorcraft.testify.api.assertion;

import net.minecraft.gametest.framework.GameTestHelper;

import java.util.Objects;

@SuppressWarnings("UnusedReturnValue")
public class IntsAssert {
    private final GameTestHelper helper;
    private final int value;
    private final String name;

    IntsAssert(GameTestHelper helper, int value) {
        this(helper, value, "integer");
    }

    IntsAssert(GameTestHelper helper, int value, String name) {
        this.helper = Objects.requireNonNull(helper);
        this.value = value;
        this.name = Objects.requireNonNull(name);
    }

    public IntsAssert equals(int expected) {
        return this.equals(expected, this.name);
    }

    public IntsAssert equals(int expected, String name) {
        if (this.value != expected) {
            throw this.helper.assertionException("test.error.value_not_equal", name, expected, this.value);
        }

        return this;
    }
}
