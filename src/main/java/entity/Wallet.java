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
    private DataSource dataSource;
    private int currentState;
    private String currency;
    private Map<String, Integer> currMAp;//Map<Currency,currentState>
    private List<History> historyList;
    private List<Symbol> list;
    private ObjectMapper objectMapper = new ObjectMapper();
    private int id;

    private IOService ioService;

    public Wallet(int id) {
        this.id = id;
        this.dataSource = new DataSource();
        this.currMAp = new HashMap<>();
        this.historyList = new ArrayList<>();
        this.currentState = 100;
        this.currency = "USD";
        currMAp.put(currency, currentState);//упаковываем валюту в мапу
        list = dataSource.getSymbolsList();
    }

    private void writeHistoryLog(History history) throws JsonProcessingException {
        File directory = new File("src\\main\\res\\Historylog\\");
        String json = objectMapper.writeValueAsString(history);
        String historyFileName = "wallet" + String.valueOf(id);
        File name = new File(getFileName(directory, historyFileName));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name, true))) {
            writer.write(json+"\n");
        } catch (IOException e) {
            System.out.println(" неожиданная ошибка при записи в JSON");
        }
    }

    private String getFileName(File file, String log) {
        return file.getAbsolutePath() + File.separator + log;
    }

    public void getHistoryById(int id){
        try {
            readHistory(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readHistory(int id) throws IOException {
        File file = new File("src\\main\\res\\Historylog\\" + "wallet" + String.valueOf(id));
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String str;
        while ((str = reader.readLine()) != null)
        {
            System.out.println(str);
        }
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
        ioService.toUpperCase(fromCurr);   //Возводим строку в верхний региcтр
        ioService.toUpperCase(toCurr);
        String pairOne = "";
        String pairTwo = "";
        int newSum;  // Сумма после конвертации

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
        if (currencyPairs == null) {
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
        History exchange = new History.Builder()
                .operation(Operation.EXCHANGE)
                .fromCurr(fromCurr)
                .oldAmount(currMAp.get(fromCurr) + amount)
                .toCurr(toCurr)
                .newAmount(currMAp.get(toCurr))
                .sum(amount)
                .build();
        try {
            writeHistoryLog(exchange);
        } catch (JsonProcessingException e) {
            System.out.println("ошибка json exeption");
        }
    }

    public void add(String toCurr, int amount) {
        ioService.toUpperCase(toCurr);
        if (currMAp.containsKey(toCurr)) {
            currMAp.put(toCurr, currMAp.get(toCurr) + amount);
            History add = new History.Builder()
                    .operation(Operation.ADD)
                    .fromCurr(toCurr)
                    .oldAmount(currMAp.get(toCurr) - amount)
                    .toCurr(toCurr)
                    .newAmount(currMAp.get(toCurr))
                    .sum(amount)
                    .build();
            try {
                writeHistoryLog(add);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                System.out.println("ошибка json exception");
            }

        } else {
            System.out.println("NOSuchCurrency");
        }

    }

    public void cashIssue(String toCurr, int amount) {
        ioService.toUpperCase(toCurr);
        if (currMAp.containsKey(toCurr)) {
            currMAp.put(toCurr, currMAp.get(toCurr) - amount);
            History add = new History.Builder()
                    .operation(Operation.ISSUE)
                    .fromCurr(toCurr)
                    .oldAmount(currMAp.get(toCurr) + amount)
                    .toCurr(toCurr)
                    .newAmount(currMAp.get(toCurr))
                    .sum(amount)
                    .build();
            try {
                writeHistoryLog(add);
            } catch (JsonProcessingException e) {
                System.out.println("ошибка json exeption");
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
