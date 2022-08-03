package model.mapping;

import model.CurrencyPair;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class CurrencyPairMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static CurrencyPair toPair(final Map<String, String> source) {
        CurrencyPair pair = new CurrencyPair();
        pair.setId(Integer.parseInt(source.get("id")));
        pair.setOpen(Float.parseFloat(source.get("o")));
        pair.setHigh(Float.parseFloat(source.get("h")));
        pair.setLow(Float.parseFloat(source.get("l")));
        pair.setPrice(Float.parseFloat(source.get("c")));
        pair.setSymbol(source.get("s"));
        pair.setTime(LocalDateTime.parse(source.get("tm"), formatter));
        return pair;
    }
}
