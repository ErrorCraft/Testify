package net.errorcraft.testify.api.assertion.entity;

import net.errorcraft.testify.api.assertion.base.FloatsAssert;
import net.errorcraft.testify.api.assertion.item.ItemStackAssert;
import net.minecraft.core.Holder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Consumer;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface LivingEntityAssert<E extends LivingEntity> extends EntityAssert<LivingEntityAssert<E>, E> {
    LivingEntityAssert<E> hasHealth(Consumer<FloatsAssert> health);
    LivingEntityAssert<E> hasStackInHand(InteractionHand hand, Consumer<ItemStackAssert> stackInHand);
    LivingEntityAssert<E> hasEquippedStack(EquipmentSlot slot, Consumer<ItemStackAssert> equippedStack);
    LivingEntityAssert<E> hasEffect(Holder<MobEffect> effect);
    LivingEntityAssert<E> hasEffect(Holder<MobEffect> effect, int amplifier);
    LivingEntityAssert<E> doesNotHaveEffect(Holder<MobEffect> effect);
}
