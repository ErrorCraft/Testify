package net.errorcraft.testify.internal.gametest.method;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.api.TestifyGameTestHelper;
import net.errorcraft.testify.internal.Util;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.gametest.framework.GameTestHelper;
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
            Constructor<?> asserterConstructor = this.method.getParameterTypes()[1]
                .getConstructor(TestifyGameTestHelper.class);
            TestifyGameTestHelper realHelper = new TestifyGameTestHelper(helper);
            this.method.invoke(
                this.instance.getEntrypoint(),
                realHelper,
                asserterConstructor.newInstance(realHelper)
            );
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof RuntimeException realException) {
                throw realException;
            }

            throw new RuntimeException(e);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
