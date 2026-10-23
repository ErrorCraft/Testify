package net.errorcraft.testify.internal;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.BuiltInRegistries;

public class Testify implements ModInitializer {
    @Override
    public void onInitialize() {
        if (!GameTestSystemProperties.ENABLED) {
            return;
        }

        Bootstrapper.bootstrapGameTestMethods(BuiltInRegistries.TEST_FUNCTION);
    }
}
