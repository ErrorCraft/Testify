package net.errorcraft.testify.internal;

import org.jspecify.annotations.Nullable;

public class GameTestSystemProperties {
    public static final boolean ENABLED = System.getProperty("testify.gametest") != null;

    @Nullable
    public static final String STRUCTURE_DIRECTORY = System.getProperty("testify.gametest.structure.directory");

    private GameTestSystemProperties() {}
}
