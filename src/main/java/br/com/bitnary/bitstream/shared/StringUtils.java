package br.com.bitnary.bitstream.shared;

public class StringUtils {

    public static String capitalizeFirst(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static String uppercaseFirst(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static String lowercaseFirst(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        return input.substring(0, 1).toLowerCase() + input.substring(1);
    }
}
