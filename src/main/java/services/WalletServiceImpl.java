package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import data.DataSource;
import entity.Wallet;
import entity.WalletHistory;
import entity.enumiration.Operation;
import model.CurrencyPair;
import model.Symbol;
import util.StringUtils;

import java.util.List;

public class WalletServiceImpl  implements WalletService{

    private DataSource dataSource;
    private List<Symbol> list;
    private IOServiceImpl ioService;


    public WalletServiceImpl() {
        this.dataSource = new DataSource();
        this.list = dataSource.getSymbolsList();
        this.ioService = new IOServiceImpl();
    }

    private Symbol pairSearcher(String fromCurr, String toCurr) {
        String pairOne = StringUtils.concatToPair(fromCurr, toCurr);  //Создаём пару
        String pairTwo = StringUtils.concatToPair(toCurr, fromCurr);
        for (Symbol symbol : list) {  //ищем в списке нужную пару символов, которую будем использовать при обмене
            if (symbol.getSymbol().equals(pairOne) || symbol.getSymbol().equals(pairTwo)) {
                return symbol;
            }
        }
        return null;
    }

    public void exchange(String fromCurr, String toCurr, int amount, Wallet wallet) {
        int newSum;// Сумм после конвертации

        Symbol found = pairSearcher(fromCurr, toCurr);

        if (found == null) {
            System.out.println("Валюта(ы) не найдена");
            return;
        }

        int sumInWallet = wallet.getCurrencyValue(fromCurr);  //проверяем хватает ли денег в кашельке
        if (sumInWallet < amount) {
            System.out.println("Недостаточно средств");
            return;
        }

        List<CurrencyPair> currencyPairs = dataSource.getPairBySymbol(found);
        if (currencyPairs == null) {
            System.out.println("Проблема с доступом к Forex");
            return;
        }

//        if (found.getSymbol().equals(found)) {
//            newSum = (int) (currencyPairs.get(0).getPrice() * amount);
//        } else {
//            newSum = (int) (amount / currencyPairs.get(0).getPrice());
//        }

        newSum = (int) (amount / currencyPairs.get(0).getPrice());

        wallet.updateSum(fromCurr, wallet.getCurrencyValue(fromCurr) - amount);
        wallet.updateSum(toCurr, newSum);
        WalletHistory exchange = new WalletHistory.Builder()
                .operation(Operation.EXCHANGE)
                .fromCurr(fromCurr)
                .oldAmount(wallet.getCurrencyValue(fromCurr) + amount)
                .toCurr(toCurr)
                .newAmount(wallet.getCurrencyValue(toCurr))
                .sum(amount)
                .build();
        try {
            ioService.writeHistoryLog(exchange, wallet.getId());
        } catch (JsonProcessingException e) {
            System.out.println("ошибка json exeption");
        }
    }

    public void add(String toCurr, int amount,Wallet wallet) {
        toCurr = toCurr.toUpperCase();
        if (wallet.isCurrencyPresent(toCurr)) {
            wallet.updateSum(toCurr, wallet.getCurrencyValue(toCurr) + amount);
            WalletHistory add = new WalletHistory.Builder()
                    .operation(Operation.ADD)
                    .fromCurr(toCurr)
                    .oldAmount(wallet.getCurrencyValue(toCurr) - amount)
                    .toCurr(toCurr)
                    .newAmount(wallet.getCurrencyValue(toCurr))
                    .sum(amount)
                    .build();
            try {
                ioService.writeHistoryLog(add, wallet.getId());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                System.out.println("ошибка json exeption");
            }

        } else {
            System.out.println("NOSuchCurrency");
        }

    }

    public void cashIssue(String toCurr, int amount, Wallet wallet) {
        toCurr = toCurr.toUpperCase();
        if (wallet.isCurrencyPresent(toCurr)) {
            wallet.updateSum(toCurr, wallet.getCurrencyValue(toCurr) - amount);
            WalletHistory issue = new WalletHistory.Builder()
                    .operation(Operation.ISSUE)
                    .fromCurr(toCurr)
                    .oldAmount(wallet.getCurrencyValue(toCurr) + amount)
                    .toCurr(toCurr)
                    .newAmount(wallet.getCurrencyValue(toCurr))
                    .sum(amount)
                    .build();
            try {
                ioService.writeHistoryLog(issue, wallet.getId());
            } catch (JsonProcessingException e) {
                System.out.println("ошибка json exeption");
            }
        } else {
            System.out.println("NOSuchCurrency");
        }
    }
}
