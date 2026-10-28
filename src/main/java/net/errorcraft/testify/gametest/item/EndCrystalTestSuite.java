package net.errorcraft.testify.gametest.item;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.api.TestifyGameTestHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.references.ItemIds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityTypeIds;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;

public class EndCrystalTestSuite {
    private static final BlockPos GROUND_POSITION = new BlockPos(1, 0, 1);
    private static final BlockPos PLACED_ENTITY_POSITION = GROUND_POSITION.offset(0, 1, 0);

    @GameTest(structure = "testify:item.end_crystal.platform")
    public void usingEndCrystalOnGroundPlacesEndCrystal(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.END_CRYSTAL));
        helper.succeedIf(() -> {
            helper.interactWithBlock(GROUND_POSITION, player, Direction.UP);
            helper.assertThat()
                .entityType(EntityTypeIds.END_CRYSTAL)
                .existsAt(
                    PLACED_ENTITY_POSITION,
                    EndCrystal.class,
                    endCrystal -> helper.assertThat()
                        .isFalse(
                            endCrystal.entity().showsBottom(),
                            () -> "Expected End Crystal not to show its bottom"
                        )
                );
        });
    }

    @GameTest(structure = "testify:item.end_crystal.platform.unsupported")
    public void usingEndCrystalOnGroundWithUnsupportedBlockDoesNotPlaceEndCrystal(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.END_CRYSTAL));
        helper.succeedIf(() -> {
            helper.interactWithBlock(GROUND_POSITION, player, Direction.UP);
            helper.assertThat()
                .entityType(EntityTypeIds.END_CRYSTAL)
                .doesNotExist();
        });
    }

    @GameTest(structure = "testify:item.end_crystal.platform.interfering_block")
    public void usingEndCrystalOnGroundWithInterferingBlockDoesNotPlaceEndCrystal(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.END_CRYSTAL));
        helper.succeedIf(() -> {
            helper.interactWithBlock(GROUND_POSITION, player, Direction.UP);
            helper.assertThat()
                .entityType(EntityTypeIds.END_CRYSTAL)
                .doesNotExist();
        });
    }

    @GameTest(structure = "testify:item.end_crystal.platform.interfering_entity")
    public void usingEndCrystalOnGroundWithInterferingEntityDoesNotPlaceEndCrystal(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.END_CRYSTAL));
        helper.succeedIf(() -> {
            helper.interactWithBlock(GROUND_POSITION, player, Direction.UP);
            helper.assertThat()
                .entityType(EntityTypeIds.END_CRYSTAL)
                .doesNotExist();
        });
    }
}
