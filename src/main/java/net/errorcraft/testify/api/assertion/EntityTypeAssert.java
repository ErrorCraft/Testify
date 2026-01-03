package net.errorcraft.testify.api.assertion;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.Objects;

@SuppressWarnings("UnusedReturnValue")
public class EntityTypeAssert<E extends Entity> {
    private final GameTestHelper helper;
    private final EntityType<E> type;

    EntityTypeAssert(GameTestHelper helper, ResourceKey<EntityType<E>> type) {
        this.helper = Objects.requireNonNull(helper);
        Objects.requireNonNull(type);
        this.type = helper.testify$getHolder(type)
            .map(Holder.Reference::value)
            .orElseThrow();
    }

    public EntityTypeAssert<E> existsAt(BlockPos pos) {
        this.helper.assertEntityPresent(this.type, pos);
        return this;
    }

    public EntityTypeAssert<E> existsAt(BlockPos pos, EntityConsumer<E> entityAsserter) {
        E entity = this.helper.testify$findOneEntityAt(this.type, pos);
        entityAsserter.accept(Assert.entity(this.helper, entity));
        return this;
    }

    public EntityTypeAssert<E> doesNotExist() {
        this.helper.assertEntityNotPresent(this.type);
        return this;
    }

    @FunctionalInterface
    public interface EntityConsumer<E extends Entity> {
        void accept(EntityAssert<E> entity);
    }
}
