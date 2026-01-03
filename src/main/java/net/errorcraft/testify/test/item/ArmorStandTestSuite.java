package net.errorcraft.testify.test.item;

import net.errorcraft.testify.api.assertion.Assert;
import net.errorcraft.testify.util.key.EntityTypeKeys;
import net.errorcraft.testify.util.key.ItemKeys;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;

public class ArmorStandTestSuite {
    private static final BlockPos GROUND_USE_POSITION = new BlockPos(1, 1, 0);
    private static final BlockPos PLACED_ENTITY_POSITION = GROUND_USE_POSITION.offset(0, 1, 0);
    private static final BlockPos HIGH_USE_POSITION = GROUND_USE_POSITION.offset(0, 3, 0);
    private static final float USER_ANGLE = 45.0f;
    private static final float SPAWNED_ENTITY_ANGLE = USER_ANGLE - 180.0f;

    @GameTest(structure = "testify:item.armor_stand.platform")
    public void usingArmorStandOnGroundPlacesArmorStand(GameTestHelper helper) {
        ItemStack stack = helper.testify$createStack(ItemKeys.ARMOR_STAND);
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        helper.getLevel().addFreshEntity(player);
        helper.testify$useBlock(GROUND_USE_POSITION, player, Direction.UP);
        helper.succeedIf(() -> Assert.entityType(helper, EntityTypeKeys.ARMOR_STAND).existsAt(PLACED_ENTITY_POSITION));
    }

    @GameTest(structure = "testify:item.armor_stand.platform.high")
    public void usingArmorStandOnCeilingDoesNotPlaceArmorStand(GameTestHelper helper) {
        ItemStack stack = helper.testify$createStack(ItemKeys.ARMOR_STAND);
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        helper.getLevel().addFreshEntity(player);
        helper.testify$useBlock(HIGH_USE_POSITION, player, Direction.DOWN);
        helper.succeedIf(() -> Assert.entityType(helper, EntityTypeKeys.ARMOR_STAND).doesNotExist());
    }

    @GameTest(structure = "testify:item.armor_stand.platform.not_enough_room")
    public void usingArmorStandOnGroundWithNotEnoughRoomDoesNotPlaceArmorStand(GameTestHelper helper) {
        ItemStack stack = helper.testify$createStack(ItemKeys.ARMOR_STAND);
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        helper.getLevel().addFreshEntity(player);
        helper.testify$useBlock(GROUND_USE_POSITION, player, Direction.UP);
        helper.succeedIf(() -> Assert.entityType(helper, EntityTypeKeys.ARMOR_STAND).doesNotExist());
    }

    @GameTest(structure = "testify:item.armor_stand.platform")
    public void usingArmorStandOnGroundWhileRotatedPlacesArmorStandRotated(GameTestHelper helper) {
        ItemStack stack = helper.testify$createStack(ItemKeys.ARMOR_STAND);
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        player.setYRot(USER_ANGLE);
        helper.getLevel().addFreshEntity(player);
        helper.testify$useBlock(GROUND_USE_POSITION, player, Direction.UP);
        helper.succeedIf(() -> Assert.entityType(helper, EntityTypeKeys.ARMOR_STAND).existsAt(PLACED_ENTITY_POSITION, armorStand -> {
            Assert.floats(helper, Mth.wrapDegrees(armorStand.entity().getYRot()), "entity yaw").equals(SPAWNED_ENTITY_ANGLE);
        }));
    }
}
