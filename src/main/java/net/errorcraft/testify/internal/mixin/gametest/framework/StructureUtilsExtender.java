package net.errorcraft.testify.internal.mixin.gametest.framework;

import net.errorcraft.testify.internal.GameTestSystemProperties;
import net.minecraft.gametest.framework.StructureUtils;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.nio.file.Path;
import java.nio.file.Paths;

@Mixin(StructureUtils.class)
public class StructureUtilsExtender {
    @Shadow
    @Nullable
    public static Path testStructuresTargetDir;

    @Shadow
    @Nullable
    public static Path testStructuresSourceDir;

    static {
        if (GameTestSystemProperties.ENABLED && GameTestSystemProperties.STRUCTURE_DIRECTORY != null) {
            Path directory = Paths.get(GameTestSystemProperties.STRUCTURE_DIRECTORY);
            testStructuresTargetDir = directory;
            testStructuresSourceDir = directory;
        }
    }
}
