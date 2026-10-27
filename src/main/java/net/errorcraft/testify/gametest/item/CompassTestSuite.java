package net.errorcraft.testify.gametest.item;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.api.TestifyGameTestHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.references.ItemIds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;

import java.util.Optional;

public class CompassTestSuite {
    private static final BlockPos LODESTONE_POSITION = new BlockPos(1, 1, 1);

    @GameTest(structure = "testify:item.compass.platform.lodestone")
    public void usingCompassOnLodestoneSetsTrackedDataFromBlock(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.COMPASS));
        helper.succeedIf(() -> {
            helper.interactWithBlock(LODESTONE_POSITION, player, Direction.UP);
            helper.assertThat()
                .livingEntity(player)
                .hasStackInHand(InteractionHand.MAIN_HAND, mainHandStack -> mainHandStack
                    .hasComponent(DataComponents.LODESTONE_TRACKER, lodestoneTracker -> {
                        helper.assertThat()
                            .isTrue(
                                lodestoneTracker.tracked(),
                                () -> "Expected Lodestone Compass to be tracked"
                            );
                        Optional<GlobalPos> target = lodestoneTracker.target();
                        helper.assertThat()
                            .isTrue(
                                target.isPresent(),
                                () -> "Expected Lodestone Compass to have a target"
                            );
                        helper.assertThat()
                            .areEqual(
                                target.get().dimension(),
                                helper.getLevel().dimension(),
                                "Lodestone dimension"
                            );
                        helper.assertThat()
                            .areEqual(
                                target.get().pos(),
                                helper.absolutePos(LODESTONE_POSITION),
                                "Lodestone position"
                            );
                    }));
        });
    }

    @GameTest(structure = "testify:item.compass.platform.lodestone")
    public void destroyingLodestoneRemovesTrackedDataFromLodestoneCompass(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.COMPASS));
        helper.getLevel().addFreshEntity(player);
        helper.startSequence()
            .thenExecute(() -> {
                helper.interactWithBlock(LODESTONE_POSITION, player, Direction.UP);
                helper.destroyBlock(LODESTONE_POSITION);
            })
            .thenExecuteAfter(1, () -> helper.assertThat()
                .livingEntity(player)
                .hasStackInHand(InteractionHand.MAIN_HAND, mainHandStack -> mainHandStack
                    .hasComponent(DataComponents.LODESTONE_TRACKER, lodestoneTracker -> {
                        helper.assertThat()
                            .isTrue(
                                lodestoneTracker.tracked(),
                                () -> "Expected Lodestone Compass to be tracked"
                            );
                        helper.assertThat()
                            .isTrue(
                                lodestoneTracker.target().isEmpty(),
                                () -> "Expected Lodestone Compass not to have a target"
                            );
                    }))
            )
            .thenSucceed();
    }
}
