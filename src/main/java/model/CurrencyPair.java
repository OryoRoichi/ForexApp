package model;

import java.time.LocalDateTime;

public class CurrencyPair {

    private int id;

    private float open;

    private float high;

    private float low;

    private float price;

    private String symbol;

    private LocalDateTime time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "CurrencyPair{" +
                "id=" + id +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", crice=" + price +
                ", symbol='" + symbol + '\'' +
                ", tm=" + time +
                '}';
    }
}
