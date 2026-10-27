package net.errorcraft.testify.gametest.item;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.api.TestifyGameTestHelper;
import net.minecraft.references.ItemIds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityTypeIds;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameType;

public class BowTestSuite {
    @GameTest
    public void usingBowWithMultishotSpawnsMultipleArrows(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        ItemStack bow = helper.createStack(ItemIds.BOW);
        bow.enchant(helper.getHolderOrThrow(Enchantments.MULTISHOT), 1);
        player.setItemInHand(InteractionHand.MAIN_HAND, bow);
        player.addItem(helper.createStack(ItemIds.ARROW));
        helper.getLevel().addFreshEntity(player);
        helper.startSequence()
            .thenExecute(() -> bow.use(helper.getLevel(), player, InteractionHand.MAIN_HAND))
            .thenExecuteAfter(20, () -> {
                player.releaseUsingItem();
                helper.assertThat()
                    .entityType(EntityTypeIds.ARROW)
                    .exists(3);
            })
            .thenSucceed();
    }
}
