package net.errorcraft.testify.util.key;

import net.errorcraft.testify.util.RegistryUtil;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ArmorStand;

public class EntityTypeKeys {
    public static final ResourceKey<EntityType<ArmorStand>> ARMOR_STAND = of("armor_stand");

    private EntityTypeKeys() {}

    @SuppressWarnings("unchecked")
    private static <E extends Entity> ResourceKey<EntityType<E>> of(final String name) {
        return RegistryUtil.vanillaKey((ResourceKey<Registry<EntityType<E>>>)(Object) Registries.ENTITY_TYPE, name);
    }
}
