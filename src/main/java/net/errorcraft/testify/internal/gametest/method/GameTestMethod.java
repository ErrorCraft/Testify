package net.errorcraft.testify.internal.gametest.method;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.internal.Util;
import net.errorcraft.testify.internal.mixin.gametest.framework.GameTestHelperAccessor;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.GameTestInfo;
import net.minecraft.resources.Identifier;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public record GameTestMethod(Method method, EntrypointContainer<Object> instance, GameTest gameTest) {
    public Identifier id() {
        String methodName = Util.camelCaseToSnakeCase(this.method.getDeclaringClass().getSimpleName() + '/' + this.method.getName())
            .replace('.', '_');
        return Identifier.fromNamespaceAndPath(
            this.instance.getProvider().getMetadata().getId(),
            methodName
        );
    }

    public void execute(GameTestHelper helper) {
        try {
            Object trueHelper = getTrueGameTestHelper(helper, this.method.getParameterTypes()[0]);
            this.method.invoke(this.instance.getEntrypoint(), trueHelper);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof RuntimeException realException) {
                throw realException;
            }

            throw new RuntimeException(e);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object getTrueGameTestHelper(GameTestHelper existing, Class<?> expected) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Do not recreate the helper if the type already matches
        if (expected == GameTestHelper.class) {
            return existing;
        }

        Constructor<?> constructor = expected.getConstructor(GameTestInfo.class);
        return constructor.newInstance(
            ((GameTestHelperAccessor) existing).testify$testInfo()
        );
    }
}
