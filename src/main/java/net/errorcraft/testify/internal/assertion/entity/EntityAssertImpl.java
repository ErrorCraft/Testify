package net.errorcraft.testify.internal.assertion.entity;

import net.errorcraft.testify.api.Asserter;
import net.errorcraft.testify.api.assertion.base.FloatsAssert;
import net.errorcraft.testify.api.assertion.entity.EntityAssert;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class EntityAssertImpl<A extends EntityAssert<A, E>, E extends Entity> implements EntityAssert<A, E> {
    protected final Asserter asserter;
    protected final E entity;

    protected EntityAssertImpl(Asserter asserter, E entity) {
        this.asserter = asserter;
        this.entity = entity;
    }

    public static <A extends EntityAssert<A, E>, E extends Entity> EntityAssert<A, E> create(Asserter asserter, @Nullable E entity) {
        return new EntityAssertImpl<>(
            asserter,
            asserter.isNotNull(entity, "entity")
        );
    }

    @SuppressWarnings("unchecked")
    private A self() {
        return (A) this;
    }

    @Override
    public E entity() {
        return this.entity;
    }

    @Override
    public A xRotation(Consumer<FloatsAssert> xRotation) {
        xRotation.accept(this.asserter.floats(Mth.wrapDegrees(this.entity.getXRot()), "x rotation"));
        return this.self();
    }

    @Override
    public A yRotation(Consumer<FloatsAssert> yRotation) {
        yRotation.accept(this.asserter.floats(Mth.wrapDegrees(this.entity.getYRot()), "y rotation"));
        return this.self();
    }
}
