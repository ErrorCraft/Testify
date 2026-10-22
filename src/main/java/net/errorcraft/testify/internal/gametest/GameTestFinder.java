package net.errorcraft.testify.internal.gametest;

import net.errorcraft.testify.api.Asserter;
import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.internal.gametest.method.GameTestMethod;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.gametest.framework.GameTestHelper;
import org.jspecify.annotations.Nullable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class GameTestFinder {
    private static final String ENTRYPOINT_KEY = "testify-gametest";

    private final FabricLoader loader;
    @Nullable
    private List<GameTestMethod> methodCache = null;

    public GameTestFinder(FabricLoader loader) {
        this.loader = loader;
    }

    public List<GameTestMethod> findMethods() {
        if (this.methodCache != null) {
            return this.methodCache;
        }

        this.methodCache = this.loader.getEntrypointContainers(ENTRYPOINT_KEY, Object.class)
            .stream()
            .flatMap(GameTestFinder::findMethods)
            .toList();
        return this.methodCache;
    }

    private static Stream<GameTestMethod> findMethods(EntrypointContainer<Object> container) {
        return Arrays.stream(container.getEntrypoint().getClass().getDeclaredMethods())
            .filter(method -> method.isAnnotationPresent(GameTest.class))
            .map(method -> createGameTestMethod(method, container));
    }

    private static GameTestMethod createGameTestMethod(Method method, EntrypointContainer<Object> container) {
        List<String> problems = new ArrayList<>();
        validateMethodSignature(method, problems::add, GameTestHelper.class, Asserter.class);
        if (!problems.isEmpty()) {
            String methodName = method.getDeclaringClass().getName() + "::" + method.getName();
            throw new UnsupportedOperationException(
                "GameTest method (" + methodName + ") has problems: " + String.join("; ", problems)
            );
        }

        return new GameTestMethod(
            method,
            container,
            method.getAnnotation(GameTest.class)
        );
    }

    public static void validateMethodSignature(Method method, Consumer<String> problems, Class<?>... parameterTypes) {
        int modifiers = method.getModifiers();
        if (!Modifier.isPublic(modifiers)) {
            problems.accept("Method must be public");
        }

        if (Modifier.isStatic(modifiers)) {
            problems.accept("Method must be an instance method");
        }

        if (method.getReturnType() != void.class) {
            problems.accept("Method must be a void method");
        }

        int parameterCount = parameterTypes.length;
        if (method.getParameterCount() != parameterCount) {
            problems.accept("Method must have " + parameterCount + " parameters");
            return;
        }

        Class<?>[] actualParameterTypes = method.getParameterTypes();
        for (int i = 0; i < actualParameterTypes.length; i++) {
            Class<?> actualParameterType = actualParameterTypes[i];
            Class<?> expectedParameterType = parameterTypes[i];
            if (!expectedParameterType.isAssignableFrom(actualParameterType)) {
                problems.accept("Parameter " + (i + 1) + " must extend " + expectedParameterType.getName());
            }
        }
    }
}
