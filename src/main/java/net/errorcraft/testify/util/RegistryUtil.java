package net.errorcraft.testify.util;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class RegistryUtil {
    private RegistryUtil() {}

    public static <T> ResourceKey<T> key(ResourceKey<? extends Registry<T>> registryName, String namespace, String name) {
        return ResourceKey.create(registryName, Identifier.fromNamespaceAndPath(namespace, name));
    }

    public static <T> ResourceKey<T> vanillaKey(ResourceKey<? extends Registry<T>> registryName, String name) {
        return ResourceKey.create(registryName, Identifier.withDefaultNamespace(name));
    }
}
