package net.errorcraft.testify.internal.mixin.world.level.levelgen.structure.templatesystem;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.DataFixer;
import net.errorcraft.testify.internal.GameTestSystemProperties;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.loader.ResourceManagerTemplateSource;
import net.minecraft.world.level.levelgen.structure.templatesystem.loader.TemplateSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(StructureTemplateManager.class)
public class StructureTemplateManagerExtender {
    @Shadow
    @Final
    public static FileToIdConverter RESOURCE_TEXT_STRUCTURE_LISTER;

    @ModifyExpressionValue(
        method = "<init>",
        at = @At(
            value = "INVOKE",
            target = "Lcom/google/common/collect/ImmutableList;builder()Lcom/google/common/collect/ImmutableList$Builder;"
        )
    )
    private ImmutableList.Builder<TemplateSource> loadStructuresFromTextResources(ImmutableList.Builder<TemplateSource> original, @Local(name = "resourceManager", argsOnly = true) ResourceManager resourceManager, @Local(name = "fixerUpper", argsOnly = true) DataFixer fixerUpper, @Local(name = "blockLookup", argsOnly = true) HolderGetter<Block> blockLookup) {
        if (GameTestSystemProperties.ENABLED) {
            original.add(
                new ResourceManagerTemplateSource(
                    fixerUpper,
                    blockLookup,
                    resourceManager,
                    RESOURCE_TEXT_STRUCTURE_LISTER
                )
            );
        }

        return original;
    }
}
