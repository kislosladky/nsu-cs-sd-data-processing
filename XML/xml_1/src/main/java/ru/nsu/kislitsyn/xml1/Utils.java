package ru.nsu.kislitsyn.xml1;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Utils {
    public static boolean isUUID(String str) {
        if (str == null) {
            return false;
        }
        try {
            UUID uuid = UUID.fromString(str);
            return uuid.toString().equals(str);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static List<String> splitByWhitespaces(String input) {
        return Arrays.stream(input.split(" "))
                .filter(x -> !x.isEmpty())
                .toList();
    }
}
