package net.errorcraft.testify.api.assertion;

import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.item.ItemStack;

public class Assert {
    private Assert() {}

    public static IntsAssert ints(GameTestHelper helper, int value) {
        return new IntsAssert(helper, value);
    }

    public static IntsAssert ints(GameTestHelper helper, int value, String name) {
        return new IntsAssert(helper, value, name);
    }

    public static ItemStackAssert itemStack(GameTestHelper helper, ItemStack stack) {
        return new ItemStackAssert(helper, stack);
    }
}
