package net.errorcraft.testify.api.assertion.entity;

import net.errorcraft.testify.api.assertion.base.FloatsAssert;
import net.errorcraft.testify.api.assertion.item.ItemStackAssert;
import net.minecraft.core.Holder;
import net.minecraft.gametest.framework.GameTestException;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Consumer;

/// Contains methods for making assertions about living entities.
@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface LivingEntityAssert<E extends LivingEntity> extends EntityAssert<LivingEntityAssert<E>, E> {
    /// Tests the entity's health.
    /// @param health A consumer with a new [FloatsAssert] to test the entity's health.
    /// @return This [LivingEntityAssert] instance.
    LivingEntityAssert<E> hasHealth(Consumer<FloatsAssert> health);

    /// Tests the item stack the entity is holding in `hand`.
    /// @param hand The hand.
    /// @param stackInHand A consumer with a new [ItemStackAssert] to test the item stack in the entity's hand.
    /// @return This [LivingEntityAssert] instance.
    LivingEntityAssert<E> hasStackInHand(InteractionHand hand, Consumer<ItemStackAssert> stackInHand);

    /// Tests the item stack the entity has equipped in `slot`.
    /// @param slot The equipment slot.
    /// @param equippedStack A consumer with a new [ItemStackAssert] to test the item stack in the entity's slot.
    /// @return This [LivingEntityAssert] instance.
    LivingEntityAssert<E> hasEquippedStack(EquipmentSlot slot, Consumer<ItemStackAssert> equippedStack);

    /// Tests whether the entity has the specified mob effect.
    /// @param effect The mob effect.
    /// @return This [LivingEntityAssert] instance.
    /// @throws GameTestException If the entity does not have the specified mob effect.
    LivingEntityAssert<E> hasEffect(Holder<MobEffect> effect);

    /// Tests whether the entity has the specified mob effect with the specified amplifier.
    /// @param effect The mob effect.
    /// @param amplifier The mob effect's amplifier
    /// @return This [LivingEntityAssert] instance.
    /// @throws GameTestException If the entity does not have the specified mob effect with the specified amplifier.
    LivingEntityAssert<E> hasEffect(Holder<MobEffect> effect, int amplifier);

    /// Tests whether the entity does not have the specified mob effect.
    /// @param effect The mob effect.
    /// @return This [LivingEntityAssert] instance.
    /// @throws GameTestException If the entity has the specified mob effect.
    LivingEntityAssert<E> doesNotHaveEffect(Holder<MobEffect> effect);
}
