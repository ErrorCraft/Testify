package net.errorcraft.testify.util.key;

import net.errorcraft.testify.util.RegistryUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class ItemKeys {
    public static final ResourceKey<Item> APPLE = of("apple");
    public static final ResourceKey<Item> IRON_CHESTPLATE = of("iron_chestplate");

    private ItemKeys() {}

    private static ResourceKey<Item> of(final String name) {
        return RegistryUtil.vanillaKey(Registries.ITEM, name);
    }
}
