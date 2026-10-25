package net.errorcraft.testify.gametest;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.api.TestifyGameTestHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.references.ItemIds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;

public class SimpleItemStackTestSuite {
    @GameTest
    public void eatingFoodItemAddsNutrition(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.getFoodData().setFoodLevel(0);
        ItemStack apple = helper.createStack(ItemIds.APPLE);
        player.setItemInHand(InteractionHand.MAIN_HAND, apple);
        int appleNutrition = helper.getDataComponent(apple, DataComponents.FOOD).nutrition();
        helper.getLevel().addFreshEntity(player);
        helper.startSequence()
            .thenExecute(() -> apple.use(helper.getLevel(), player, InteractionHand.MAIN_HAND))
            .thenExecuteAfter(
                apple.getUseDuration(player),
                () -> helper.assertThat()
                    .ints(player.getFoodData().getFoodLevel(), "nutrition")
                    .equals(appleNutrition)
            ).thenSucceed();
    }

    @GameTest
    public void usingEquippableItemEquipsIt(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        ItemStack ironChestplate = helper.createStack(ItemIds.IRON_CHESTPLATE);
        player.setItemInHand(InteractionHand.MAIN_HAND, ironChestplate);
        helper.succeedIf(() -> {
            ironChestplate.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
            helper.assertThat()
                .livingEntity(player)
                .hasEquippedStack(EquipmentSlot.CHEST, chestItem -> chestItem
                    .is(ItemIds.IRON_CHESTPLATE));
        });
    }
}
