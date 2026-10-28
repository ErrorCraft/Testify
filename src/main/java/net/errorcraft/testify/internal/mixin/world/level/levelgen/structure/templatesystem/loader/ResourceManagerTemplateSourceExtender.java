package net.errorcraft.testify.internal.mixin.world.level.levelgen.structure.templatesystem.loader;

import net.errorcraft.testify.internal.mixin.world.level.levelgen.structure.templatesystem.StructureTemplateManagerAccessor;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.world.level.levelgen.structure.templatesystem.loader.ResourceManagerTemplateSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ResourceManagerTemplateSource.class)
public class ResourceManagerTemplateSourceExtender {
    @Shadow
    @Final
    private FileToIdConverter fileToIdConverter;

    @Unique
    private static final String STRUCTURE_TEXT_FILE_EXTENSION = StructureTemplateManagerAccessor.structureTextFileExtension();

    @ModifyArg(
        method = "load",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/levelgen/structure/templatesystem/loader/ResourceManagerTemplateSource;load(Lnet/minecraft/server/packs/resources/IoSupplier;ZLjava/util/function/Consumer;)Ljava/util/Optional;"
        )
    )
    private boolean checkForTextResources(boolean asText) {
        return asText || STRUCTURE_TEXT_FILE_EXTENSION.equals(this.fileToIdConverter.extension());
    }
}
