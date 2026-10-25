package net.errorcraft.testify.api;

import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.TestData;
import net.minecraft.world.level.block.Rotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/// An annotation that is used to mark a method as a game test.
/// The method must be a public instance void method and take a single [GameTestHelper] or any of its subclasses.
///
/// These values directly correspond to the fields in [TestData] and most of them get their defaults from [TestData#CODEC].
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GameTest {
    /// An id for a test environment defined in a data pack.
    /// Defaults to `"minecraft:default"`, which is provided by the vanilla data pack.
    /// @return An id for a test environment defined in a data pack.
    String environment() default "minecraft:default";

    /// An id of the dimension where the game test runs.
    /// Defaults to `"minecraft:overworld"`.
    /// @return An id of the dimension where the game test runs.
    String dimension() default "minecraft:overworld";

    /// An id of the structure to use for the game test.
    /// Defaults to `"testify:simple_platform"`, a 5x5x5 structure with a Bedrock floor.
    /// @return An id of the structure to use for the game test.
    String structure() default "testify:simple_platform";

    /// The maximum number of ticks this game test is allowed to run for.
    /// Defaults to 100.
    /// @return The maximum number of ticks this game test is allowed to run for.
    int maxTicks() default 100;

    /// The amount of ticks to wait after placing the structure before starting the game test.
    /// Defaults to 0.
    /// @return The amount of ticks to wait after placing the structure before starting the game test.
    int setupTicks() default 0;

    /// Whether the game test is required to pass for the entire run to pass.
    /// Defaults to `true`.
    /// @return Whether the game test is required to pass for the entire run to pass.
    boolean required() default true;

    /// The rotation of the placed structure.
    /// Defaults to [Rotation#NONE].
    /// @return The rotation of the placed structure.
    Rotation rotation() default Rotation.NONE;

    /// Whether the game test can only be run manually and should not be considered as part of the automated game tests.
    /// Defaults to `false`.
    /// @return Whether the game test can only be run manually and should not be considered as part of the automated game tests.
    boolean manualOnly() default false;

    /// The maximum number of attempts to run on the game test.
    /// Defaults to 1.
    /// @return The maximum number of attempts to run on the game test.
    int maxAttempts() default 1;

    /// The amount of attempts that must succeed for the entire game test to succeed.
    /// Defaults to 1.
    /// @return The amount of attempts that must succeed for the entire game test to succeed.
    int requiredSuccesses() default 1;

    /// Whether the game test needs access to the sky.
    /// Defaults to `false`.
    /// @return Whether the game test needs access to the sky.
    boolean skyAccess() default false;

    /// The amount of empty blocks to place around the structure.
    /// Defaults to 0.
    /// @return The amount of empty blocks to place around the structure.
    int padding() default 0;
}
