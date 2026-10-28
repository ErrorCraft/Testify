package net.errorcraft.testify.gametest.item;

import net.errorcraft.testify.api.GameTest;
import net.errorcraft.testify.api.TestifyGameTestHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.references.ItemIds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityTypeIds;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.minecart.Minecart;
import net.minecraft.world.level.GameType;

public class MinecartTestSuite {
    private static final BlockPos RAIL_POSITION = new BlockPos(1, 1, 1);
    private static final BlockPos PLACED_ENTITY_POSITION = RAIL_POSITION;
    private static final double PLACED_ENTITY_VERTICAL_OFFSET = 0.0625d;
    private static final double PLACED_ENTITY_VERTICAL_OFFSET_ASCENDING = PLACED_ENTITY_VERTICAL_OFFSET + 0.5d;

    @GameTest(structure = "testify:item.minecart.platform")
    public void usingMinecartWithoutRailsDoesNotPlaceMinecart(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.MINECART));
        helper.succeedIf(() -> {
            helper.interactWithBlock(RAIL_POSITION, player, Direction.UP);
            helper.assertThat()
                .entityType(EntityTypeIds.MINECART)
                .doesNotExist();
        });
    }

    @GameTest(structure = "testify:item.minecart.platform.rails.north_south")
    public void usingMinecartOnNorthSouthRailsPlacesMinecartAtCorrectPosition(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.MINECART));
        helper.succeedIf(() -> {
            helper.interactWithBlock(RAIL_POSITION, player, Direction.UP);
            helper.assertThat()
                .entityType(EntityTypeIds.MINECART)
                .existsAt(
                    PLACED_ENTITY_POSITION,
                    Minecart.class,
                    minecart -> minecart
                        .hasYPosition(y -> y
                            .remainder(1.0d, yWithinBlock -> yWithinBlock
                                .equals(PLACED_ENTITY_VERTICAL_OFFSET)))
                );
        });
    }

    @GameTest(structure = "testify:item.minecart.platform.rails.east_west")
    public void usingMinecartOnEastWestRailsPlacesMinecartAtCorrectPosition(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.MINECART));
        helper.succeedIf(() -> {
            helper.interactWithBlock(RAIL_POSITION, player, Direction.UP);
            helper.assertThat()
                .entityType(EntityTypeIds.MINECART)
                .existsAt(
                    PLACED_ENTITY_POSITION,
                    Minecart.class,
                    minecart -> minecart
                        .hasYPosition(y -> y
                            .remainder(1.0d, yWithinBlock -> yWithinBlock
                                .equals(PLACED_ENTITY_VERTICAL_OFFSET)))
                );
        });
    }

    @GameTest(structure = "testify:item.minecart.platform.rails.ascending_east")
    public void usingMinecartOnAscendingEastRailsPlacesMinecartAtCorrectPosition(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.MINECART));
        helper.succeedIf(() -> {
            helper.interactWithBlock(RAIL_POSITION, player, Direction.UP);
            helper.assertThat()
                .entityType(EntityTypeIds.MINECART)
                .existsAt(
                    PLACED_ENTITY_POSITION,
                    Minecart.class,
                    minecart -> minecart
                        .hasYPosition(y -> y
                            .remainder(1.0d, yWithinBlock -> yWithinBlock
                                .equals(PLACED_ENTITY_VERTICAL_OFFSET_ASCENDING)))
                );
        });
    }

    @GameTest(structure = "testify:item.minecart.platform.rails.ascending_west")
    public void usingMinecartOnAscendingWestRailsPlacesMinecartAtCorrectPosition(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.MINECART));
        helper.succeedIf(() -> {
            helper.interactWithBlock(RAIL_POSITION, player, Direction.UP);
            helper.assertThat()
                .entityType(EntityTypeIds.MINECART)
                .existsAt(
                    PLACED_ENTITY_POSITION,
                    Minecart.class,
                    minecart -> minecart
                        .hasYPosition(y -> y
                            .remainder(1.0d, yWithinBlock -> yWithinBlock
                                .equals(PLACED_ENTITY_VERTICAL_OFFSET_ASCENDING)))
                );
        });
    }

    @GameTest(structure = "testify:item.minecart.platform.rails.ascending_north")
    public void usingMinecartOnAscendingNorthRailsPlacesMinecartAtCorrectPosition(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.MINECART));
        helper.succeedIf(() -> {
            helper.interactWithBlock(RAIL_POSITION, player, Direction.UP);
            helper.assertThat()
                .entityType(EntityTypeIds.MINECART)
                .existsAt(
                    PLACED_ENTITY_POSITION,
                    Minecart.class,
                    minecart -> minecart
                        .hasYPosition(y -> y
                            .remainder(1.0d, yWithinBlock -> yWithinBlock
                                .equals(PLACED_ENTITY_VERTICAL_OFFSET_ASCENDING)))
                );
        });
    }

    @GameTest(structure = "testify:item.minecart.platform.rails.ascending_south")
    public void usingMinecartOnAscendingSouthRailsPlacesMinecartAtCorrectPosition(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.MINECART));
        helper.succeedIf(() -> {
            helper.interactWithBlock(RAIL_POSITION, player, Direction.UP);
            helper.assertThat()
                .entityType(EntityTypeIds.MINECART)
                .existsAt(
                    PLACED_ENTITY_POSITION,
                    Minecart.class,
                    minecart -> minecart
                        .hasYPosition(y -> y
                            .remainder(1.0d, yWithinBlock -> yWithinBlock
                                .equals(PLACED_ENTITY_VERTICAL_OFFSET_ASCENDING)))
                );
        });
    }

    @GameTest(structure = "testify:item.minecart.platform.rails.south_east")
    public void usingMinecartOnSouthEastRailsPlacesMinecartAtCorrectPosition(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.MINECART));
        helper.succeedIf(() -> {
            helper.interactWithBlock(RAIL_POSITION, player, Direction.UP);
            helper.assertThat()
                .entityType(EntityTypeIds.MINECART)
                .existsAt(
                    PLACED_ENTITY_POSITION,
                    Minecart.class,
                    minecart -> minecart
                        .hasYPosition(y -> y
                            .remainder(1.0d, yWithinBlock -> yWithinBlock
                                .equals(PLACED_ENTITY_VERTICAL_OFFSET)))
                );
        });
    }

    @GameTest(structure = "testify:item.minecart.platform.rails.south_west")
    public void usingMinecartOnSouthWestRailsPlacesMinecartAtCorrectPosition(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.MINECART));
        helper.succeedIf(() -> {
            helper.interactWithBlock(RAIL_POSITION, player, Direction.UP);
            helper.assertThat()
                .entityType(EntityTypeIds.MINECART)
                .existsAt(
                    PLACED_ENTITY_POSITION,
                    Minecart.class,
                    minecart -> minecart
                        .hasYPosition(y -> y
                            .remainder(1.0d, yWithinBlock -> yWithinBlock
                                .equals(PLACED_ENTITY_VERTICAL_OFFSET)))
                );
        });
    }

    @GameTest(structure = "testify:item.minecart.platform.rails.north_west")
    public void usingMinecartOnNorthWestRailsPlacesMinecartAtCorrectPosition(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.MINECART));
        helper.succeedIf(() -> {
            helper.interactWithBlock(RAIL_POSITION, player, Direction.UP);
            helper.assertThat()
                .entityType(EntityTypeIds.MINECART)
                .existsAt(
                    PLACED_ENTITY_POSITION,
                    Minecart.class,
                    minecart -> minecart
                        .hasYPosition(y -> y
                            .remainder(1.0d, yWithinBlock -> yWithinBlock
                                .equals(PLACED_ENTITY_VERTICAL_OFFSET)))
                );
        });
    }

    @GameTest(structure = "testify:item.minecart.platform.rails.north_east")
    public void usingMinecartOnNorthEastRailsPlacesMinecartAtCorrectPosition(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, helper.createStack(ItemIds.MINECART));
        helper.succeedIf(() -> {
            helper.interactWithBlock(RAIL_POSITION, player, Direction.UP);
            helper.assertThat()
                .entityType(EntityTypeIds.MINECART)
                .existsAt(
                    PLACED_ENTITY_POSITION,
                    Minecart.class,
                    minecart -> minecart
                        .hasYPosition(y -> y
                            .remainder(1.0d, yWithinBlock -> yWithinBlock
                                .equals(PLACED_ENTITY_VERTICAL_OFFSET)))
                );
        });
    }

    @GameTest(structure = "testify:item.minecart.platform.rails.north_south")
    public void placingMinecartSetsCustomNameFromItemStack(TestifyGameTestHelper helper) {
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        Component customMinecartName = Component.literal("abc");
        player.setItemInHand(
            InteractionHand.MAIN_HAND,
            helper.createStack(
                ItemIds.MINECART,
                1,
                DataComponentPatch.builder()
                    .set(DataComponents.CUSTOM_NAME, customMinecartName)
                    .build()
            )
        );
        helper.succeedIf(() -> {
            helper.interactWithBlock(RAIL_POSITION, player, Direction.UP);
            helper.assertThat()
                .entityType(EntityTypeIds.MINECART)
                .existsAt(
                    PLACED_ENTITY_POSITION,
                    Minecart.class,
                    minecart -> helper.assertThat()
                        .areEqual(
                            minecart.entity().getCustomName(),
                            customMinecartName,
                            "custom Minecart name"
                        )
                );
        });
    }
}
