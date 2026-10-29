package net.errorcraft.testify.gametest.item;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.api.TestifyGameTestHelper;
import net.errorcraft.testify.api.assertion.item.ItemStackAssert;
import net.minecraft.references.ItemIds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;

public class TotemOfUndyingTestSuite {
    @GameTest
    public void holdingTotemOfUndyingSavesHolderFromDeath(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.TOTEM_OF_UNDYING));
        helper.succeedIf(() -> {
            player.hurtServer(helper.getLevel(), helper.getLevel().damageSources().fall(), Float.MAX_VALUE);
            helper.assertThat()
                .livingEntity(player)
                .hasHealth(health -> health.equals(1.0f))
                .hasEffect(MobEffects.REGENERATION, 1)
                .hasEffect(MobEffects.ABSORPTION, 1)
                .hasEffect(MobEffects.FIRE_RESISTANCE, 0)
                .hasStackInHand(InteractionHand.MAIN_HAND, ItemStackAssert::isEmpty);
        });
    }
}
