package net.errorcraft.testify.gametest.item;

import net.errorcraft.testify.api.Asserter;
import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.api.TestifyGameTestHelper;
import net.minecraft.references.ItemIds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;

public class GoatHornTestSuite {
    @GameTest
    public void usingGoatHornImmediatelySetsCooldown(TestifyGameTestHelper helper, Asserter asserter) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        ItemStack goatHorn = helper.createStack(ItemIds.GOAT_HORN);
        player.setItemInHand(InteractionHand.MAIN_HAND, goatHorn);
        helper.succeedIf(() -> {
            goatHorn.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
            asserter.isTrue(
                player.getCooldowns().isOnCooldown(goatHorn),
                () -> "Expected Goat Horn to set a cooldown immediately"
            );
        });
    }
}
