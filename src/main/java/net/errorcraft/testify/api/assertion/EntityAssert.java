package net.errorcraft.testify.api.assertion;

import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.Entity;

import java.util.Objects;

public class EntityAssert<E extends Entity> {
    private final GameTestHelper helper;
    private final E entity;

    EntityAssert(GameTestHelper helper, E entity) {
        this.helper = Objects.requireNonNull(helper);
        this.entity = Objects.requireNonNull(entity);
    }

    public E entity() {
        return this.entity;
    }
}
