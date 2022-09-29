package util;

import entity.enumiration.Operation;

import java.util.Optional;

public class StringUtils {

    public static Optional<Integer> toIntNullSafe(String in){
        try{
            Integer res = Integer.parseInt(in);
            return Optional.of(res);
        } catch (NumberFormatException e){
            return Optional.empty();
        }

    }
}
