package net.errorcraft.testify.api.assertion;

import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;

public class Assert {
    private Assert() {}

    public static IntsAssert ints(GameTestHelper helper, int value) {
        return new IntsAssert(helper, value);
    }

    public static IntsAssert ints(GameTestHelper helper, int value, String name) {
        return new IntsAssert(helper, value, name);
    }

    public static FloatsAssert floats(GameTestHelper helper, float value) {
        return new FloatsAssert(helper, value);
    }

    public static FloatsAssert floats(GameTestHelper helper, float value, String name) {
        return new FloatsAssert(helper, value, name);
    }

    public static <E extends Entity> EntityTypeAssert<E> entityType(GameTestHelper helper, ResourceKey<EntityType<E>> type) {
        return new EntityTypeAssert<>(helper, type);
    }

    public static <E extends Entity> EntityAssert<E> entity(GameTestHelper helper, E entity) {
        return new EntityAssert<>(helper, entity);
    }

    public static ItemStackAssert itemStack(GameTestHelper helper, ItemStack stack) {
        return new ItemStackAssert(helper, stack);
    }
}
