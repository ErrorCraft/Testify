package net.errorcraft.testify.internal.mixin.world.level.levelgen.structure.templatesystem;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.nio.file.Path;
import java.nio.file.Paths;

@Mixin(StructureTemplateManager.class)
public class StructureTemplateManagerExtender {
    @Unique
    private static final String OUTPUT_DIR = System.getProperty("testify.gametest.structures.output-dir");

    @ModifyExpressionValue(
        method = "save",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/SharedConstants;DEBUG_SAVE_STRUCTURES_AS_SNBT:Z",
            opcode = Opcodes.GETSTATIC
        )
    )
    private boolean alwaysWriteAsSnbtWhenOutputDirectoryIsProvided(boolean original) {
        return OUTPUT_DIR != null || original;
    }

    @ModifyExpressionValue(
        method = "createAndValidatePathToGeneratedStructure",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplateManager;generatedDir:Ljava/nio/file/Path;",
            opcode = Opcodes.GETFIELD
        )
    )
    private Path useCustomOutputDirectory(Path original) {
        if (OUTPUT_DIR != null) {
            return Paths.get(OUTPUT_DIR);
        }

        return original;
    }

    @ModifyArg(
        method = "createAndValidatePathToGeneratedStructure",
        at = @At(
            value = "INVOKE",
            target = "Ljava/nio/file/Path;resolve(Ljava/lang/String;)Ljava/nio/file/Path;",
            ordinal = 1
        )
    )
    private String useFabricGameTestDirectoryName(String other) {
        if (OUTPUT_DIR != null) {
            return "gametest/structure";
        }

        return other;
    }
}
