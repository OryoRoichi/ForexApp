package model.history;

import connection.ConnectionException;

public enum Period {

    MINUTE("m"),
    HOUR("h"),
    DAY("d"),
    WEEK("w"),
    MONTH("month");

    private final String suffix;

    Period(String suffix) {
        this.suffix = suffix;
    }

    public String toPattern(final Integer period) {
        if (period == null && this != MONTH) {
            throw new ConnectionException("Период не определен");
        }
        return String.format("%d%s", period, this.suffix);
    }
}
