package net.errorcraft.testify.internal.assertion.entity;

import net.errorcraft.testify.api.Asserter;
import net.errorcraft.testify.api.assertion.base.FloatsAssert;
import net.errorcraft.testify.api.assertion.entity.EntityAssert;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class EntityAssertImpl<E extends Entity> implements EntityAssert<E> {
    private final Asserter asserter;
    private final E entity;

    private EntityAssertImpl(Asserter asserter, E entity) {
        this.asserter = asserter;
        this.entity = entity;
    }

    public static <E extends Entity> EntityAssert<E> create(Asserter asserter, @Nullable E entity) {
        return new EntityAssertImpl<>(
            asserter,
            asserter.isNotNull(entity, "entity")
        );
    }

    @Override
    public E entity() {
        return this.entity;
    }

    @Override
    public EntityAssert<E> xRotation(Consumer<FloatsAssert> xRotation) {
        xRotation.accept(this.asserter.floats(Mth.wrapDegrees(this.entity.getXRot()), "x rotation"));
        return this;
    }

    @Override
    public EntityAssert<E> yRotation(Consumer<FloatsAssert> yRotation) {
        yRotation.accept(this.asserter.floats(Mth.wrapDegrees(this.entity.getYRot()), "y rotation"));
        return this;
    }
}
