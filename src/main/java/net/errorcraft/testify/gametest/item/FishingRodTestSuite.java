package net.errorcraft.testify.gametest.item;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.api.TestifyGameTestHelper;
import net.minecraft.references.ItemIds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.GameType;

public class FishingRodTestSuite {
    @GameTest
    public void usingFishingRodCastsFishingRod(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.FISHING_ROD));
        helper.succeedIf(() -> {
            player.getMainHandItem().use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
            helper.assertThat()
                .isNotNull(player.fishing, () -> "Expected Player to have cast a Fishing Rod");
        });
    }

    @GameTest
    public void usingCastFishingRodRetractsFishingRod(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.FISHING_ROD));
        Projectile.spawnProjectile(
            new FishingHook(player, helper.getLevel(), 0, 0),
            helper.getLevel(),
            player.getMainHandItem()
        );
        helper.succeedIf(() -> {
            player.getMainHandItem().use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
            helper.assertThat()
                .isNull(player.fishing, () -> "Expected Player to have retracted a Fishing Rod");
        });
    }
}
