package net.errorcraft.testify.internal.mixin.gametest.framework;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameTestHelper.class)
public abstract class GameTestHelperExtender {
    @Shadow
    public abstract BlockPos absolutePos(BlockPos relativePos);

    @ModifyReturnValue(
        method = "makeMockPlayer",
        at = @At("RETURN")
    )
    private Player setDefaultPositionSoMockPlayerIsAlwaysLoaded(Player original) {
        original.setPos(Vec3.atBottomCenterOf(this.absolutePos(BlockPos.ZERO)));
        return original;
    }
}
