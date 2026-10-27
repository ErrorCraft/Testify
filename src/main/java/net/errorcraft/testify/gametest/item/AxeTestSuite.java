package net.errorcraft.testify.gametest.item;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.api.TestifyGameTestHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;

public class AxeTestSuite {
    private static final BlockPos BLOCK_POSITION = new BlockPos(1, 1, 1);

    @GameTest(structure = "testify:item.axe.platform.oak_log")
    public void usingAxeOnLogStripsIt(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(
            InteractionHand.MAIN_HAND,
            helper.createStack(ItemIds.IRON_AXE)
        );
        helper.succeedIf(() -> {
            helper.interactWithBlock(BLOCK_POSITION, player, Direction.UP);
            helper.assertThat()
                .blockState(BLOCK_POSITION)
                .is(BlockItemIds.STRIPPED_OAK_LOG.block());
        });
    }

    @GameTest(structure = "testify:item.axe.platform.oak_wood")
    public void usingAxeOnWoodStripsIt(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(
            InteractionHand.MAIN_HAND,
            helper.createStack(ItemIds.IRON_AXE)
        );
        helper.succeedIf(() -> {
            helper.interactWithBlock(BLOCK_POSITION, player, Direction.UP);
            helper.assertThat()
                .blockState(BLOCK_POSITION)
                .is(BlockItemIds.STRIPPED_OAK_WOOD.block());
        });
    }

    @GameTest(structure = "testify:item.axe.platform.oxidized_copper")
    public void usingAxeOnOxidizedCopperScrapesOneOxidizationLayerOff(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(
            InteractionHand.MAIN_HAND,
            helper.createStack(ItemIds.IRON_AXE)
        );
        helper.succeedIf(() -> {
            helper.interactWithBlock(BLOCK_POSITION, player, Direction.UP);
            helper.assertThat()
                .blockState(BLOCK_POSITION)
                .is(BlockItemIds.COPPER_BLOCK.weathering().weathered().block());
            helper.interactWithBlock(BLOCK_POSITION, player, Direction.UP);
            helper.assertThat()
                .blockState(BLOCK_POSITION)
                .is(BlockItemIds.COPPER_BLOCK.weathering().exposed().block());
            helper.interactWithBlock(BLOCK_POSITION, player, Direction.UP);
            helper.assertThat()
                .blockState(BLOCK_POSITION)
                .is(BlockItemIds.COPPER_BLOCK.weathering().unaffected().block());
        });
    }

    @GameTest(structure = "testify:item.axe.platform.waxed_copper_block")
    public void usingAxeOnWaxedBlockUnwaxesIt(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(
            InteractionHand.MAIN_HAND,
            helper.createStack(ItemIds.IRON_AXE)
        );
        helper.succeedIf(() -> {
            helper.interactWithBlock(BLOCK_POSITION, player, Direction.UP);
            helper.assertThat()
                .blockState(BLOCK_POSITION)
                .is(BlockItemIds.COPPER_BLOCK.weathering().unaffected().block());
        });
    }
}
