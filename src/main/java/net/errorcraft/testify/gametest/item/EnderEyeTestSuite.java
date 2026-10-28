package net.errorcraft.testify.gametest.item;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.api.TestifyGameTestHelper;
import net.errorcraft.testify.api.assertion.item.ItemStackAssert;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.references.ItemIds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class EnderEyeTestSuite {
    private static final BlockPos END_PORTAL_FRAME_POSITION = new BlockPos(1, 1, 1);

    @GameTest(structure = "testify:item.ender_eye.platform")
    public void usingEnderEyeOnEndPortalFramePlacesEyeInside(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.ENDER_EYE));
        helper.succeedIf(() -> {
            helper.interactWithBlock(END_PORTAL_FRAME_POSITION, player, Direction.UP);
            helper.assertThat()
                .blockState(END_PORTAL_FRAME_POSITION)
                .hasPropertyValue(BlockStateProperties.EYE, true);
        });
    }

    @GameTest(structure = "testify:item.ender_eye.platform.eye")
    public void usingEnderEyeOnEndPortalFrameWithEyeAlreadyPlacedInsideDoesNotConsumeEnderEye(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.ENDER_EYE));
        helper.succeedIf(() -> {
            helper.interactWithBlock(END_PORTAL_FRAME_POSITION, player, Direction.UP);
            helper.assertThat()
                .livingEntity(player)
                .hasStackInHand(InteractionHand.MAIN_HAND, ItemStackAssert::isNotEmpty);
        });
    }
}
