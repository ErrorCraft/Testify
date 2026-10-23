package net.errorcraft.testify.gametest.item;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.api.TestifyGameTestHelper;
import net.minecraft.references.ItemIds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;

public class BeetrootSoupTestSuite {
    @GameTest
    public void consumingBeetrootSoupLeavesBowlAfterConsuming(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.getFoodData().setFoodLevel(0);
        ItemStack beetrootSoup = helper.createStack(ItemIds.BEETROOT_SOUP);
        player.setItemInHand(InteractionHand.MAIN_HAND, beetrootSoup);
        ServerLevel level = helper.getLevel();
        level.addFreshEntity(player);
        helper.startSequence()
            .thenExecute(() -> beetrootSoup.use(level, player, InteractionHand.MAIN_HAND))
            .thenExecuteAfter(
                beetrootSoup.getUseDuration(player),
                () -> helper.assertThat().isTrue(
                    player.getInventory().contains(stack -> stack.is(ItemIds.BOWL)),
                    () -> "Expected Player to have a Bowl in their inventory"
                )
            )
            .thenSucceed();
    }
}
