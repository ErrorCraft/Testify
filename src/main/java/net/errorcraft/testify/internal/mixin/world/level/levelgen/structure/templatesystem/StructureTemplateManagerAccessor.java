package net.errorcraft.testify.internal.mixin.world.level.levelgen.structure.templatesystem;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StructureTemplateManager.class)
public interface StructureTemplateManagerAccessor {
    @Accessor("STRUCTURE_TEXT_FILE_EXTENSION")
    static String structureTextFileExtension() {
        throw new AssertionError();
    }
}
