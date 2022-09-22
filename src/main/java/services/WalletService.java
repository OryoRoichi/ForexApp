package services;

import entity.Wallet;

public interface WalletService {
    void exchange(String fromCurr, String toCurr, int amount, Wallet wallet);
    void add(String toCurr, int amount,Wallet wallet);
    void cashIssue(String toCurr, int amount, Wallet wallet);
}
