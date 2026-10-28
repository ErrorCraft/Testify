package net.errorcraft.testify.internal.assertion.entity;

import net.errorcraft.testify.api.Asserter;
import net.errorcraft.testify.api.assertion.base.DoublesAssert;
import net.errorcraft.testify.api.assertion.base.FloatsAssert;
import net.errorcraft.testify.api.assertion.entity.EntityAssert;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
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
    public A hasXPosition(Consumer<DoublesAssert> xPosition) {
        Vec3 pos = this.asserter.helper().relativeVec(this.entity.position());
        xPosition.accept(this.asserter.doubles(pos.x(), "x position"));
        return this.self();
    }

    @Override
    public A hasYPosition(Consumer<DoublesAssert> yPosition) {
        Vec3 pos = this.asserter.helper().relativeVec(this.entity.position());
        yPosition.accept(this.asserter.doubles(pos.y(), "y position"));
        return this.self();
    }

    @Override
    public A hasZPosition(Consumer<DoublesAssert> zPosition) {
        Vec3 pos = this.asserter.helper().relativeVec(this.entity.position());
        zPosition.accept(this.asserter.doubles(pos.z(), "z position"));
        return this.self();
    }

    @Override
    public A hasXRotation(Consumer<FloatsAssert> xRotation) {
        xRotation.accept(this.asserter.floats(Mth.wrapDegrees(this.entity.getXRot()), "x rotation"));
        return this.self();
    }

    @Override
    public A hasYRotation(Consumer<FloatsAssert> yRotation) {
        yRotation.accept(this.asserter.floats(Mth.wrapDegrees(this.entity.getYRot()), "y rotation"));
        return this.self();
    }
}
