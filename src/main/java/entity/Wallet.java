package entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.DataSource;
import entity.enumiration.Operation;
import model.CurrencyPair;
import model.Symbol;
import services.IOService;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wallet {

    private int currentState;
    private String currency;
    private Map<String, Integer> currMAp;//Map<Currency,currentState>
    private int id;

    public Wallet() {
        this.id = hashCode();
        this.currMAp = new HashMap<>();
        this.currentState = 100;
        this.currency = "USD";
        currMAp.put(currency, currentState);//упаковываем валюту в мапу

    }

    public String getCurrency() {
        return currency;
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
