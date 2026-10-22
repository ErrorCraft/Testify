package net.errorcraft.testify.internal.assertion.base;

import net.errorcraft.testify.api.assertion.base.FloatsAssert;
import net.minecraft.gametest.framework.GameTestHelper;

public class FloatsAssertImpl implements FloatsAssert {
    private final GameTestHelper helper;
    private final float value;
    private final String name;

    private FloatsAssertImpl(GameTestHelper helper, float value, String name) {
        this.helper = helper;
        this.value = value;
        this.name = name;
    }

    public static FloatsAssert create(GameTestHelper helper, float value, String name) {
        return new FloatsAssertImpl(helper, value, name);
    }

    @Override
    public FloatsAssert equals(float expected) {
        if (this.value != expected) {
            throw this.helper.assertionException("test.error.value_not_equal", this.name, expected, this.value);
        }

        return this;
    }
}
