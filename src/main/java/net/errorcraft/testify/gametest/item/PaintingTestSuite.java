package net.errorcraft.testify.gametest.item;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.api.TestifyGameTestHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.references.ItemIds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityTypeIds;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;

public class PaintingTestSuite {
    private static final BlockPos BLOCK_POSITION = new BlockPos(1, 1, 1);
    private static final Direction CORRECT_PLACE_DIRECTION = Direction.NORTH;
    private static final BlockPos PAINTING_POSITION = BLOCK_POSITION.relative(CORRECT_PLACE_DIRECTION);

    @GameTest(structure = "testify:item.painting.platform")
    public void usingPaintingOnVerticalSidePlacesPainting(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.PAINTING));
        helper.succeedIf(() -> {
            helper.interactWithBlock(BLOCK_POSITION, player, CORRECT_PLACE_DIRECTION);
            helper.assertThat()
                .entityType(EntityTypeIds.PAINTING)
                .existsAt(PAINTING_POSITION);
        });
    }

    @GameTest(structure = "testify:item.painting.platform")
    public void usingPaintingOnTopSideDoesNotPlacePainting(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.PAINTING));
        helper.succeedIf(() -> {
            helper.interactWithBlock(BLOCK_POSITION, player, Direction.UP);
            helper.assertThat()
                .entityType(EntityTypeIds.PAINTING)
                .doesNotExist();
        });
    }

    @GameTest(structure = "testify:item.painting.platform")
    public void usingPaintingOnBottomSideDoesNotPlacePainting(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.PAINTING));
        helper.succeedIf(() -> {
            helper.interactWithBlock(BLOCK_POSITION, player, Direction.DOWN);
            helper.assertThat()
                .entityType(EntityTypeIds.PAINTING)
                .doesNotExist();
        });
    }
}
