package net.errorcraft.testify.test;

import net.errorcraft.testify.api.Asserter;
import net.errorcraft.testify.api.GameTest;
import net.minecraft.core.component.DataComponents;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.references.ItemIds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;

public class SimpleItemStackTestSuite {
    @GameTest(structure = "fabric-gametest-api-v1:empty")
    public void eatingFoodItemAddsNutrition(GameTestHelper helper, Asserter asserter) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.getFoodData().setFoodLevel(0);
        ItemStack stack = helper.testify$createStack(ItemIds.APPLE);
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        helper.getLevel().addFreshEntity(player);
        FoodProperties food = helper.testify$getDataComponent(stack, DataComponents.FOOD);
        stack.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
        helper.startSequence()
            .thenExecuteAfter(
                stack.getUseDuration(player),
                () -> asserter.ints(player.getFoodData().getFoodLevel(), "nutrition")
                    .equals(food.nutrition())
            ).thenSucceed();
    }

    @GameTest(structure = "fabric-gametest-api-v1:empty")
    public void usingEquippableItemEquipsIt(GameTestHelper helper, Asserter asserter) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        ItemStack stack = helper.testify$createStack(ItemIds.IRON_CHESTPLATE);
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        stack.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
        helper.succeedIf(() -> asserter.itemStack(player.getItemBySlot(EquipmentSlot.CHEST))
            .is(ItemIds.IRON_CHESTPLATE));
    }
}
