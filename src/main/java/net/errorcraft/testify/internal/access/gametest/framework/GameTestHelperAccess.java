package net.errorcraft.testify.internal.access.gametest.framework;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;

public interface GameTestHelperAccess {
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
}
