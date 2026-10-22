package net.errorcraft.testify.internal.mixin.gametest.framework;

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
        String structureDirectory = System.getProperty("testify.gametest.structures.output-dir");
        if (structureDirectory != null) {
            Path structurePath = Paths.get(structureDirectory);
            testStructuresTargetDir = structurePath;
            testStructuresSourceDir = structurePath;
        }
    }
}
