package net.errorcraft.testify.internal.assertion.base;

import net.errorcraft.testify.api.assertion.base.IntsAssert;
import net.minecraft.gametest.framework.GameTestHelper;

public class IntsAssertImpl implements IntsAssert {
    private final GameTestHelper helper;
    private final int value;
    private final String name;

    private IntsAssertImpl(GameTestHelper helper, int value, String name) {
        this.helper = helper;
        this.value = value;
        this.name = name;
    }

    public static IntsAssert create(GameTestHelper helper, int value, String name) {
        return new IntsAssertImpl(helper, value, name);
    }

    @Override
    public IntsAssert equals(int expected) {
        if (this.value != expected) {
            throw this.helper.assertionException("test.error.value_not_equal", this.name, expected, this.value);
        }

        return this;
    }
}
