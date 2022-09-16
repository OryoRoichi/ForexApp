package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import data.DataSource;
import entity.Wallet;
import entity.WalletHistory;
import entity.enumiration.Operation;
import model.CurrencyPair;
import model.Symbol;

import java.util.List;

public class WalletService {

    private DataSource dataSource;
    private List<Symbol> list;
    private IOService ioService;


    public WalletService() {
        this.dataSource = new DataSource();
        this.list = dataSource.getSymbolsList();
        this.ioService = new IOService();
    }

    private Symbol pairSearcher(String fromCurr, String toCurr) {
        String pairOne = fromCurr + "/" + toCurr;  //Создаём пару
        String pairTwo = toCurr + "/" + fromCurr;
        for (Symbol symbol : list) {  //ищем в списке нужную пару символов, которую будем использовать при обмене
            if (symbol.getSymbol().equals(pairOne) || symbol.getSymbol().equals(pairTwo)) {
                return symbol;
            }
        }
        return null;
    }

    public void exchange(String fromCurr, String toCurr, int amount, Wallet wallet) {
        int newSum;// Сумм после конвертации
        fromCurr = fromCurr.toUpperCase();//Возводим строку в верхний регитр
        toCurr = toCurr.toUpperCase();

        Symbol found = pairSearcher(fromCurr, toCurr);

        if (found == null) {
            System.out.println("Валюта(ы) не найдена");
            return;
        }

        int sumInWallet = wallet.getCurrMAp().get(fromCurr);  //проверяем хватает ли денег в кашельке
        if (sumInWallet < amount) {
            System.out.println("Недостаточно средств");
            return;
        }

        List<CurrencyPair> currencyPairs = dataSource.getPairBySymbol(found);
        if (currencyPairs == null) {
            System.out.println("Проблема с доступом к Forex");
            return;
        }

        if (found.getSymbol().equals(found)) {
            newSum = (int) (currencyPairs.get(0).getPrice() * amount);
        } else {
            newSum = (int) (amount / currencyPairs.get(0).getPrice());
        }
        wallet.getCurrMAp().put(fromCurr, wallet.getCurrMAp().get(fromCurr) - amount);
        wallet.getCurrMAp().put(toCurr, newSum);
        WalletHistory exchange = new WalletHistory.Builder()
                .operation(Operation.EXCHANGE)
                .fromCurr(fromCurr)
                .oldAmount(wallet.getCurrMAp().get(fromCurr) + amount)
                .toCurr(toCurr)
                .newAmount(wallet.getCurrMAp().get(toCurr))
                .sum(amount)
                .build();
        System.out.println("Недостаточно средств");
        try {
            ioService.writeHistoryLog(exchange, wallet.getId());
        } catch (JsonProcessingException e) {
            System.out.println("ошибка json exeption");
        }
    }

    public void add(String toCurr, int amount,Wallet wallet) {
        toCurr = toCurr.toUpperCase();
        if (wallet.getCurrMAp().containsKey(toCurr)) {
            wallet.getCurrMAp().put(toCurr, wallet.getCurrMAp().get(toCurr) + amount);
            WalletHistory add = new WalletHistory.Builder()
                    .operation(Operation.ADD)
                    .fromCurr(toCurr)
                    .oldAmount(wallet.getCurrMAp().get(toCurr) - amount)
                    .toCurr(toCurr)
                    .newAmount(wallet.getCurrMAp().get(toCurr))
                    .sum(amount)
                    .build();
            System.out.println("Недостаточно средств");
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
        if (wallet.getCurrMAp().containsKey(toCurr)) {
            wallet.getCurrMAp().put(toCurr, wallet.getCurrMAp().get(toCurr) - amount);
            WalletHistory issue = new WalletHistory.Builder()
                    .operation(Operation.ISSUE)
                    .fromCurr(toCurr)
                    .oldAmount(wallet.getCurrMAp().get(toCurr) + amount)
                    .toCurr(toCurr)
                    .newAmount(wallet.getCurrMAp().get(toCurr))
                    .sum(amount)
                    .build();
            System.out.println("Недостаточно средств");
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
