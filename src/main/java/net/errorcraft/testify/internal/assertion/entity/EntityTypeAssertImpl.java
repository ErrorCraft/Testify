package net.errorcraft.testify.internal.assertion.entity;

import net.errorcraft.testify.api.Asserter;
import net.errorcraft.testify.api.assertion.entity.EntityAssert;
import net.errorcraft.testify.api.assertion.entity.EntityTypeAssert;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class EntityTypeAssertImpl implements EntityTypeAssert {
    private final Asserter asserter;
    private final EntityType<?> type;

    private EntityTypeAssertImpl(Asserter asserter, ResourceKey<EntityType<?>> type) {
        this.asserter = asserter;
        this.type = asserter.helper()
            .getHolder(type)
            .map(Holder.Reference::value)
            .orElseThrow();
    }

    public static EntityTypeAssert create(Asserter asserter, ResourceKey<EntityType<?>> type) {
        return new EntityTypeAssertImpl(
            asserter,
            asserter.isNotNull(type, "entity type")
        );
    }

    @Override
    public EntityTypeAssert exists() {
        this.asserter.helper().assertEntityPresent(this.type);
        return this;
    }

    @Override
    public EntityTypeAssert existsAt(BlockPos pos) {
        this.asserter.helper().assertEntityPresent(this.type, pos);
        return this;
    }

    @Override
    public <A extends EntityAssert<A, E>, E extends Entity> EntityTypeAssert existsAt(BlockPos pos, Class<E> entityClass, EntityConsumer<A, E> entity) {
        this.asserter.isInstance(
            this.asserter.helper().findOneEntityAt(this.type, pos),
            entityClass,
            "entity",
            actualEntity -> entity.accept(this.asserter.entity(actualEntity))
        );
        return this;
    }

    @Override
    public EntityTypeAssert doesNotExist() {
        this.asserter.helper().assertEntityNotPresent(this.type);
        return this;
    }
}
