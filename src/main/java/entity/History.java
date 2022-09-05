package entity;

import entity.enumiration.Operation;

public class History {
    Operation operation;
    private String fromCurr;
    private int sum;
    private String toCurr;
    private int oldAmount;
    private int newAmount;

    public History(){}

    public History(Operation operation,
                   String fromCurr, int oldAmount,
                   int sum,
                   String toCurr, int newAmount) {
        this.operation = operation;
        this.fromCurr = fromCurr;
        this.sum = sum;
        this.toCurr = toCurr;
        this.oldAmount = oldAmount;
        this.newAmount = newAmount;
    }


    public static class Builder {

        Operation operation;
        private String fromCurr;
        private int sum;
        private String toCurr;
        private int oldAmount;
        private int newAmount;

        public Builder operation(Operation operation) {
            this.operation = operation;
            return this;
        }

        public Builder sum(int sum) {
            this.sum = sum;
            return this;
        }

        public Builder fromCurr(String fromCurr) {
            this.fromCurr = fromCurr;
            return this;
        }
        public Builder toCurr(String toCurr) {
            this.toCurr = toCurr;
            return this;
        }

        public Builder oldAmount(int oldAmount) {
            this.oldAmount = oldAmount;
            return this;
        }

        public Builder newAmount(int newAmount) {
            this.newAmount = newAmount;
            return this;
        }
        public History build() {
            return new History(this.operation , this.fromCurr ,this.oldAmount, this.sum ,this.toCurr  ,this.newAmount);
        }
    }
}