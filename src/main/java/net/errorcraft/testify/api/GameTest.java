package net.errorcraft.testify.api;

import net.minecraft.world.level.block.Rotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GameTest {
    String environment() default "minecraft:default";
    String dimension() default "minecraft:overworld";
    String structure() default "testify:simple_platform";
    int maxTicks() default 100;
    int setupTicks() default 0;
    boolean required() default true;
    Rotation rotation() default Rotation.NONE;
    boolean manualOnly() default false;
    int maxAttempts() default 1;
    int requiredSuccesses() default 1;
    boolean skyAccess() default false;
    int padding() default 0;
}
