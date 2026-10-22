package net.errorcraft.testify.internal.gametest.method;

import net.errorcraft.testify.api.Asserter;
import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.internal.Util;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.Identifier;

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
            this.method.invoke(
                this.instance.getEntrypoint(),
                helper,
                new Asserter(helper)
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
