package entity;

import entity.enumiration.Operation;

import java.time.LocalDateTime;

public class History {
    Operation operation;
    private String fromCurr;
    private int sum;
    private String toCurr;
    private int oldAmount;
    private int newAmount;


    public History(Operation operation,
                   String fromCurr,int oldAmount,
                   int sum,
                   String toCurr,int newAmount) {
        this.operation = operation;
        this.fromCurr = fromCurr;
        this.sum = sum;
        this.toCurr = toCurr;
        this.oldAmount = oldAmount;
        this.newAmount = newAmount;
    }

    @Override
    public String toString() {
        switch (operation) {
            case EXCHANGE:
                return "History{" +
                        "operation=" + operation +
                        ", fromCurr='" + fromCurr + '\'' +
                        ", oldAmount=" + oldAmount + " - " + sum +
                        " -> toCurr='" + toCurr + '\'' +
                        ", newAmount=" + newAmount +
                        '}';
            case ADD:
                return "History{" +
                        "operation=" + operation +
                        ", fromCurr='" + fromCurr + '\'' +
                        ", oldAmount=" + oldAmount + " + " + sum +
                        " -> toCurr='" + toCurr + '\'' +
                        ", newAmount=" + newAmount +
                        '}';
            case ISSUE:
                return "History{" +
                        "operation=" + operation +
                        ", fromCurr='" + fromCurr + '\'' +
                        ", oldAmount=" + oldAmount + " - " + sum +
                        " -> toCurr='" + toCurr + '\'' +
                        ", newAmount=" + newAmount +
                        '}';
            default:
                return "History";
        }
    }
}