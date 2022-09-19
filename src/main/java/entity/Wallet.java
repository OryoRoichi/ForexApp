package entity;

import java.util.HashMap;
import java.util.Map;

public class Wallet {

    private int currentState;
    private String currency;
    private Map<String, Integer> currMAp;//Map<Currency,currentState>
    private final int id;

    public Wallet() {
        this.id = hashCode();
        this.currMAp = new HashMap<>();
        this.currentState = 0;
        this.currency = "USD";
        currMAp.put(currency, currentState);//упаковываем валюту в мапу
    }

    public Map<String, Integer> getCurrMAp() {
        return currMAp;
    }

    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Wallet{" +
                "current Map=" + currMAp +
                '}';
    }

}
