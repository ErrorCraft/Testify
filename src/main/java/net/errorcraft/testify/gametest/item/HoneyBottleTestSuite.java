package net.errorcraft.testify.gametest.item;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.api.TestifyGameTestHelper;
import net.minecraft.references.ItemIds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;

public class HoneyBottleTestSuite {
    @GameTest
    public void consumingHoneyBottleRemovesPoisonStatusEffect(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.getFoodData().setFoodLevel(0);
        player.addEffect(new MobEffectInstance(MobEffects.POISON, MobEffectInstance.INFINITE_DURATION));
        ItemStack honeyBottle = helper.createStack(ItemIds.HONEY_BOTTLE);
        player.setItemInHand(InteractionHand.MAIN_HAND, honeyBottle);
        ServerLevel level = helper.getLevel();
        level.addFreshEntity(player);
        helper.startSequence()
            .thenExecute(() -> honeyBottle.use(level, player, InteractionHand.MAIN_HAND))
            .thenExecuteAfter(
                honeyBottle.getUseDuration(player),
                () -> helper.assertThat().livingEntity(player).doesNotHaveEffect(MobEffects.POISON)
            )
            .thenSucceed();
    }
}
