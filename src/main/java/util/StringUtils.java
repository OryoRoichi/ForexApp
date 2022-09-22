package util;

import java.util.Optional;

public class StringUtils {

    public static String concatToPair(String one, String two) {
        return one.toLowerCase().concat("/").concat(two.toUpperCase());
    }

    public static Optional<Integer> toIntegerNullSafe(String value) {
        try {
            Integer result = Integer.parseInt(value);
            return Optional.of(result);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
