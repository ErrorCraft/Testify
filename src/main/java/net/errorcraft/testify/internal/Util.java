package net.errorcraft.testify.internal;

import java.util.Locale;

public class Util {
    private Util() {}

    public static String camelCaseToSnakeCase(String input) {
        return input.replaceAll("([a-z])([A-Z])", "$1_$2")
            .toLowerCase(Locale.ROOT);
    }
}
