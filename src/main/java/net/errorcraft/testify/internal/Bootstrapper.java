package net.errorcraft.testify.internal;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.internal.gametest.GameTestFinder;
import net.errorcraft.testify.internal.gametest.method.GameTestMethod;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.FunctionGameTestInstance;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.GameTestInstance;
import net.minecraft.gametest.framework.TestData;
import net.minecraft.gametest.framework.TestEnvironmentDefinition;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Bootstrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrapper.class);
    private static final GameTestFinder GAME_TEST_FINDER = new GameTestFinder(FabricLoader.getInstance());

    private Bootstrapper() {}

    public static void bootstrapGameTestMethods(Registry<Consumer<GameTestHelper>> registry) {
        for (GameTestMethod method : GAME_TEST_FINDER.findMethods()) {
            Identifier id = method.id();
            LOGGER.info("Registering GameTest method with id {}", id);
            Registry.register(registry, id, method::execute);
        }
    }

    public static void bootstrapGameTestInstances(RegistryOps.RegistryInfoLookup context, BiConsumer<Identifier, GameTestInstance> bootstrapper) {
        HolderGetter<TestEnvironmentDefinition<?>> testEnvironments = context.lookup(Registries.TEST_ENVIRONMENT)
            .orElseThrow();
        for (GameTestMethod method : GAME_TEST_FINDER.findMethods()) {
            GameTest gameTest = method.gameTest();
            Identifier id = method.id();
            bootstrapper.accept(
                id,
                new FunctionGameTestInstance(
                    ResourceKey.create(Registries.TEST_FUNCTION, id),
                    new TestData<>(
                        testEnvironments.getOrThrow(
                            ResourceKey.create(
                                Registries.TEST_ENVIRONMENT,
                                Identifier.parse(gameTest.environment())
                            )
                        ),
                        ResourceKey.create(
                            Registries.DIMENSION,
                            Identifier.parse(gameTest.dimension())
                        ),
                        Identifier.parse(gameTest.structure()),
                        gameTest.maxTicks(),
                        gameTest.setupTicks(),
                        gameTest.required(),
                        gameTest.rotation(),
                        gameTest.manualOnly(),
                        gameTest.maxAttempts(),
                        gameTest.requiredSuccesses(),
                        gameTest.skyAccess(),
                        gameTest.padding()
                    )
                )
            );
        }
    }
}
