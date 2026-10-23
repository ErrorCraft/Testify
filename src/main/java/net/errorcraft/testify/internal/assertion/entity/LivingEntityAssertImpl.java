package net.errorcraft.testify.internal.assertion.entity;

import net.errorcraft.testify.api.Asserter;
import net.errorcraft.testify.api.assertion.base.FloatsAssert;
import net.errorcraft.testify.api.assertion.entity.LivingEntityAssert;
import net.errorcraft.testify.api.assertion.item.ItemStackAssert;
import net.minecraft.core.Holder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.alchemy.PotionContents;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class LivingEntityAssertImpl<E extends LivingEntity> extends EntityAssertImpl<LivingEntityAssert<E>, E> implements LivingEntityAssert<E> {
    protected LivingEntityAssertImpl(Asserter asserter, E entity) {
        super(asserter, entity);
    }

    public static <E extends LivingEntity> LivingEntityAssertImpl<E> create(Asserter asserter, @Nullable E entity) {
        return new LivingEntityAssertImpl<>(
            asserter,
            asserter.isNotNull(entity, "living entity")
        );
    }

    @Override
    public LivingEntityAssert<E> hasHealth(Consumer<FloatsAssert> health) {
        health.accept(this.asserter.floats(this.entity.getHealth(), "entity health"));
        return this;
    }

    @Override
    public LivingEntityAssert<E> hasStackInHand(InteractionHand hand, Consumer<ItemStackAssert> stackInHand) {
        stackInHand.accept(this.asserter.itemStack(this.entity.getItemInHand(hand), "item stack in hand"));
        return this;
    }

    @Override
    public LivingEntityAssert<E> hasEquippedStack(EquipmentSlot slot, Consumer<ItemStackAssert> equippedStack) {
        equippedStack.accept(this.asserter.itemStack(this.entity.getItemBySlot(slot), "equipped item stack"));
        return this;
    }

    @Override
    public LivingEntityAssert<E> hasEffect(Holder<MobEffect> effect) {
        if (this.entity.hasEffect(effect)) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.entity.expected_effect",
            this.entity.getName(),
            PotionContents.getPotionDescription(effect, 0)
        );
    }

    @Override
    public LivingEntityAssert<E> hasEffect(Holder<MobEffect> effect, int amplifier) {
        MobEffectInstance effectInstance = this.entity.getEffect(effect);
        if (effectInstance != null && effectInstance.getAmplifier() == amplifier) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.entity.expected_effect",
            this.entity.getName(),
            PotionContents.getPotionDescription(effect, amplifier)
        );
    }

    @Override
    public LivingEntityAssert<E> doesNotHaveEffect(Holder<MobEffect> effect) {
        if (!this.entity.hasEffect(effect)) {
            return this;
        }

        throw this.asserter.helper().assertionException(
            "test.error.entity.did_not_expect_effect",
            this.entity.getName(),
            PotionContents.getPotionDescription(effect, 0)
        );
    }
}
