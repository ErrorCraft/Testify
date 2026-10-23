package net.errorcraft.testify.internal.gametest;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.api.TestifyGameTestHelper;
import net.errorcraft.testify.internal.gametest.method.GameTestMethod;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.GameTestInfo;
import org.jspecify.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class GameTestFinder {
    private static final String ENTRYPOINT_KEY = "testify-gametest";
    private static final Class<?> GAME_TEST_HELPER_CLASS = GameTestHelper.class;
    private static final Set<Class<?>> VALIDATED_GAME_TEST_HELPER_CLASSES = new HashSet<>();
    private static final Class<?> GAME_TEST_INFO_CLASS = GameTestInfo.class;

    static {
        // Do not validate the existing helpers
        VALIDATED_GAME_TEST_HELPER_CLASSES.add(GAME_TEST_HELPER_CLASS);
        VALIDATED_GAME_TEST_HELPER_CLASSES.add(TestifyGameTestHelper.class);
    }

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
        validateMethodSignature(method, problems::add);
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

    private static void validateMethodSignature(Method method, Consumer<String> problems) {
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

        if (method.getParameterCount() != 1) {
            problems.accept("Method must have one parameter");
            return;
        }

        Class<?> helperClass = method.getParameterTypes()[0];
        if (!GAME_TEST_HELPER_CLASS.isAssignableFrom(helperClass)) {
            problems.accept("Method parameter must extend " + GAME_TEST_HELPER_CLASS.getName());
        } else {
            validateGameTestHelperConstructor(helperClass, problems);
        }
    }

    private static void validateGameTestHelperConstructor(Class<?> helperClass, Consumer<String> problems) {
        if (VALIDATED_GAME_TEST_HELPER_CLASSES.contains(helperClass)) {
            return;
        }

        try {
            Constructor<?> constructor = helperClass.getConstructor(GAME_TEST_INFO_CLASS);
            if (!Modifier.isPublic(constructor.getModifiers())) {
                problems.accept(helperClass.getName() + " constructor must be public");
            }

            VALIDATED_GAME_TEST_HELPER_CLASSES.add(helperClass);
        } catch (NoSuchMethodException e) {
            problems.accept(helperClass.getName() + " must have a constructor taking a single " + GAME_TEST_INFO_CLASS.getName());
        }
    }
}
