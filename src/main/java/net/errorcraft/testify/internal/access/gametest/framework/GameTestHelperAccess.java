package net.errorcraft.testify.internal.access.gametest.framework;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;

import java.util.List;
import java.util.Optional;

public interface GameTestHelperAccess {
    default <E extends Entity> List<E> testify$findEntitiesAt(EntityType<E> type, BlockPos pos) {
        return null;
    }
    default <E extends Entity> E testify$findOneEntityAt(EntityType<E> type, BlockPos pos) {
        return null;
    }
    default Player testify$makeMockPlayer(GameType gameMode, BlockPos pos) {
        return null;
    }
    default ItemStack testify$createStack(ResourceKey<Item> id) {
        return ItemStack.EMPTY;
    }
    default ItemStack testify$createStack(ResourceKey<Item> id, int count) {
        return ItemStack.EMPTY;
    }
    default ItemStack testify$createStack(ResourceKey<Item> id, int count, DataComponentPatch components) {
        return ItemStack.EMPTY;
    }
    default <T> T testify$getDataComponent(ItemStack stack, DataComponentType<T> type) {
        return null;
    }
    default <T> Optional<Holder.Reference<T>> testify$getHolder(ResourceKey<T> id) {
        return Optional.empty();
    }
    default void testify$useBlock(BlockPos pos, Player player, Direction direction) {}
}
