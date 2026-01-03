package net.errorcraft.testify.test;

import net.errorcraft.testify.api.assertion.Assert;
import net.errorcraft.testify.util.key.ItemKeys;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.core.component.DataComponents;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;

public class SimpleItemStackTestSuite {
    @GameTest(maxTicks = 32)
    public void eatingFoodItemAddsNutrition(GameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.getFoodData().setFoodLevel(0);
        ItemStack stack = helper.testify$createStack(ItemKeys.APPLE);
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        helper.getLevel().addFreshEntity(player);
        FoodProperties food = helper.testify$getDataComponent(stack, DataComponents.FOOD);
        stack.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
        helper.startSequence().thenExecuteAfter(
            stack.getUseDuration(player),
            () -> Assert.ints(helper, player.getFoodData().getFoodLevel(), "nutrition").equals(food.nutrition())
        ).thenSucceed();
    }

    @GameTest
    public void usingEquippableItemEquipsIt(GameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        ItemStack stack = helper.testify$createStack(ItemKeys.IRON_CHESTPLATE);
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        stack.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
        helper.succeedIf(() -> Assert.itemStack(helper, player.getItemBySlot(EquipmentSlot.CHEST)).is(ItemKeys.IRON_CHESTPLATE));
    }
}
