package model.history;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDateTime;

public class History {

    @JsonAlias("o")
    private float open;

    @JsonAlias("c")
    private float close;

    @JsonAlias("h")
    private float high;

    @JsonAlias("l")
    private float low;

    @JsonAlias("tm")
    private LocalDateTime time;

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "History{" +
                "open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", time=" + time +
                '}';
    }
}
