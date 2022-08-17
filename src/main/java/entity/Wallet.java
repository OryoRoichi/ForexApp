package entity;

import data.DataSource;
import entity.enumiration.Operation;
import model.CurrencyPair;
import model.Symbol;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wallet {
    private DataSource dataSource;
    private int currentState;
    private String currency;
    private Map<String, Integer> currMAp;//Map<Currency,currentState>
    private List<History> historyList;
    private List<Symbol> list;

    public Wallet() {
        this.dataSource = new DataSource();
        this.currMAp = new HashMap<>();
        this.historyList = new ArrayList<>();
        this.currentState = 100;
        this.currency = "USD";
        currMAp.put(currency, currentState);//упаковываем валюту в мапу
        list = dataSource.getSymbolsList();
    }

    private void writeHistoryLog(String in) throws FileNotFoundException {
        OutputStream out = new BufferedOutputStream(new FileOutputStream(new File("src\\main\\res\\HistoryLog.txt")));
        String res = in;
        try {
            out.write(res.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void readHystory() {
        File file = new File("src\\main\\res\\HistoryLog.txt");
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        byte[] buffer = new byte[1024];
        String res = "";
        while(true){
            try {
                if (!(in.read(buffer) != -1)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            res = res.concat(new String(buffer)).concat("\n");
        }
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        res.trim();
        System.out.println(res);
    }
    private Symbol pairSearcher(String fromCurr, String toCurr, String pairOne, String pairTwo) {
        pairOne = fromCurr + "/" + toCurr;  //Создаём пару
        pairTwo = toCurr + "/" + fromCurr;
        for (Symbol symbol : list) {  //ищем в списке нужную пару символов, которую будем использовать при обмене
            if (symbol.getSymbol().equals(pairOne) || symbol.getSymbol().equals(pairTwo)) {
                return symbol;
            }
        }
        return null;
    }

    public void exchange(String fromCurr, String toCurr, int amount) {
        fromCurr = fromCurr.toUpperCase();     //Возводим строку в верхний регитр
        toCurr = toCurr.toUpperCase();
        String pairOne = "";
        String pairTwo = "";
        int newSum;  // Сумм после конвертации

        Symbol found = pairSearcher(fromCurr, toCurr, pairOne, pairTwo);

        if (found == null) {
            System.out.println("Валюта(ы) не найдена");
            return;
        }

        int sumInWallet = currMAp.get(fromCurr);  //проверяем хватает ли денег в кашельке
        if (sumInWallet < amount) {
            System.out.println("Недостаточно средств");
            return;
        }

        List<CurrencyPair> currencyPairs = dataSource.getPairBySymbol(found);
        if (currencyPairs == null && currencyPairs.size() == 0) {
            System.out.println("Проблема с доступом к Forex");
            return;
        }

        if (found.getSymbol().equals(pairOne)) {
            newSum = (int) (currencyPairs.get(0).getPrice() * amount);
        } else {
            newSum = (int) (amount / currencyPairs.get(0).getPrice());
        }
        currMAp.put(fromCurr, currMAp.get(fromCurr) - amount);
        currMAp.put(toCurr, newSum);
        try {
            writeHistoryLog(new History(Operation.EXCHANGE,
                    fromCurr,currMAp.get(fromCurr)+amount,
                    amount,
                    toCurr,currMAp.get(toCurr)).toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }





    public void add(String toCurr, int amount) {
        toCurr = toCurr.toUpperCase();
        if (currMAp.containsKey(toCurr)) {
            currMAp.put(toCurr, currMAp.get(toCurr) + amount);
            try {
                writeHistoryLog(new History(Operation.ADD,
                        toCurr, currMAp.get(toCurr) - amount,
                        amount,
                        toCurr, currMAp.get(toCurr)).toString());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("NOSuchCurrency");
        }

    }

    public void cashIssue(String toCurr, int amount) {
        toCurr = toCurr.toUpperCase();
        if (currMAp.containsKey(toCurr)) {
            currMAp.put(toCurr, currMAp.get(toCurr) - amount);
            try {
                writeHistoryLog(new History(Operation.ISSUE,
                        toCurr, currMAp.get(toCurr) + amount,
                        amount,
                        toCurr, currMAp.get(toCurr)).toString());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("NOSuchCurrency");
        }
    }

    public void printCurrMap() {
        for (Map.Entry<String, Integer> entry : currMAp.entrySet()) {
            System.out.println(entry);
        }
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "current Map=" + currMAp +
                '}';
    }

}
