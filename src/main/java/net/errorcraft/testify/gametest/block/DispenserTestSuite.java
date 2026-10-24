package net.errorcraft.testify.gametest.block;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.api.TestifyGameTestHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.world.entity.EntityTypeIds;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;

public class DispenserTestSuite {
    private static final BlockPos DISPENSER_POSITION = new BlockPos(2, 1, 3);
    private static final BlockPos BUTTON_POSITION = DISPENSER_POSITION.offset(0, 1, 0);
    private static final BlockPos OUTPUT_POSITION = DISPENSER_POSITION.offset(0, 0, -1);
    private static final BlockPos ABOVE_OUTPUT_POSITION = OUTPUT_POSITION.offset(0, 1, 0);

    @GameTest(structure = "testify:block.dispenser")
    public void dispensingArrowSpawnsArrow(TestifyGameTestHelper helper) {
        DispenserBlockEntity dispenser = helper.getBlockEntity(DISPENSER_POSITION, DispenserBlockEntity.class);
        dispenser.insertItem(helper.createStack(ItemIds.ARROW));
        helper.startSequence()
            .thenExecute(() -> helper.pressButton(BUTTON_POSITION))
            .thenExecuteAfter(4, () -> {
                helper.assertThat()
                    .entityType(EntityTypeIds.ARROW)
                    .exists();
                helper.assertThat()
                    .itemStack(dispenser.getItem(0))
                    .isEmpty();
            })
            .thenSucceed();
    }

    @GameTest(structure = "testify:block.dispenser.grass_block")
    public void dispensingBoneMealFertilizesBlock(TestifyGameTestHelper helper) {
        DispenserBlockEntity dispenser = helper.getBlockEntity(DISPENSER_POSITION, DispenserBlockEntity.class);
        dispenser.insertItem(helper.createStack(ItemIds.BONE_MEAL));
        helper.startSequence()
            .thenExecute(() -> helper.pressButton(BUTTON_POSITION))
            .thenExecuteAfter(4, () -> {
                helper.assertThat().blockState(ABOVE_OUTPUT_POSITION)
                    .isNot(BlockItemIds.AIR.block());
                helper.assertThat().itemStack(dispenser.getItem(0))
                    .isEmpty();
            })
            .thenSucceed();
    }

    @GameTest(structure = "testify:block.dispenser.bedrock")
    public void dispensingBoneMealOnInvalidBlockKeepsItem(TestifyGameTestHelper helper) {
        DispenserBlockEntity dispenser = helper.getBlockEntity(DISPENSER_POSITION, DispenserBlockEntity.class);
        dispenser.insertItem(helper.createStack(ItemIds.BONE_MEAL));
        helper.startSequence()
            .thenExecute(() -> helper.pressButton(BUTTON_POSITION))
            .thenExecuteAfter(4, () -> {
                helper.assertThat()
                    .blockState(ABOVE_OUTPUT_POSITION)
                    .is(BlockItemIds.AIR.block());
                helper.assertThat()
                    .entityType(EntityTypeIds.ITEM)
                    .doesNotExist();
                helper.assertThat()
                    .itemStack(dispenser.getItem(0))
                    .is(ItemIds.BONE_MEAL);
            })
            .thenSucceed();
    }
}
