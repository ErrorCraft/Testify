package net.errorcraft.testify.gametest;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.api.TestifyGameTestHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.references.ItemIds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;

public class SimpleItemStackTestSuite {
    @GameTest
    public void eatingFoodItemAddsNutrition(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.getFoodData().setFoodLevel(0);
        ItemStack stack = helper.createStack(ItemIds.APPLE);
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        helper.getLevel().addFreshEntity(player);
        FoodProperties food = helper.getDataComponent(stack, DataComponents.FOOD);
        stack.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
        helper.startSequence()
            .thenExecuteAfter(
                stack.getUseDuration(player),
                () -> helper.assertThat()
                    .ints(player.getFoodData().getFoodLevel(), "nutrition")
                    .equals(food.nutrition())
            ).thenSucceed();
    }

    @GameTest
    public void usingEquippableItemEquipsIt(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        ItemStack stack = helper.createStack(ItemIds.IRON_CHESTPLATE);
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        stack.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
        helper.succeedIf(() -> helper.assertThat()
            .itemStack(player.getItemBySlot(EquipmentSlot.CHEST))
            .is(ItemIds.IRON_CHESTPLATE));
    }
}
