package net.errorcraft.testify.internal.mixin.resources;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Lifecycle;
import net.errorcraft.testify.internal.Bootstrapper;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestInstance;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.resources.RegistryLoadTask;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceManagerRegistryLoadTask;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Mixin(ResourceManagerRegistryLoadTask.class)
public abstract class ResourceManagerRegistryLoadTaskExtender<T> extends RegistryLoadTask<T> {
    protected ResourceManagerRegistryLoadTaskExtender(RegistryDataLoader.RegistryData<T> data, Lifecycle lifecycle, Map<ResourceKey<?>, Exception> loadingErrors) {
        super(data, lifecycle, loadingErrors);
    }

    @ModifyExpressionValue(
        method = "load",
        at = @At(
            value = "INVOKE",
            target = "Ljava/util/concurrent/CompletableFuture;thenCompose(Ljava/util/function/Function;)Ljava/util/concurrent/CompletableFuture;"
        )
    )
    private CompletableFuture<Map<Identifier, PendingRegistration<GameTestInstance>>> registerGameTests(CompletableFuture<Map<Identifier, PendingRegistration<GameTestInstance>>> original, RegistryOps.RegistryInfoLookup context) {
        ResourceKey<? extends Registry<?>> registry = this.data.key();
        if (registry != Registries.TEST_INSTANCE) {
            return original;
        }

        return original.thenApply(registrations -> {
            HashMap<Identifier, PendingRegistration<GameTestInstance>> newRegistrations = new HashMap<>(registrations);
            Bootstrapper.bootstrapGameTestInstances(
                context,
                (id, testInstance) -> newRegistrations.put(
                    id,
                    new PendingRegistration<>(
                        ResourceKey.create(Registries.TEST_INSTANCE, id),
                        Either.left(testInstance),
                        RegistrationInfo.BUILT_IN
                    )
                )
            );
            return newRegistrations;
        });
    }
}
