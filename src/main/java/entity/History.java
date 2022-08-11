package entity;

import entity.enumiration.Operation;

import java.time.LocalDateTime;

public class History {
    Wallet wallet;
    Operation operation;
    public History(Wallet wallet , Operation operation) {
        this.wallet = new Wallet();
        this.operation = operation;

    }
    @Override
    public String toString() {
        return "History{" +
                "Wallet=" + wallet  +"Operation=" + operation +
                '}';
    }
}