
        package entity;

import data.DataSource;
import model.CurrencyPair;
import model.Symbol;

import java.util.*;

public class Wallet {

    List<String> hystory;

    Map<String, Integer> valuts = new HashMap<>();
    List<Symbol> list;
    DataSource dataSource;

    public Wallet(int currentState, String currency) {
        this.hystory = new ArrayList<>();
        valuts.put(currency.toUpperCase(), currentState);

        dataSource = new DataSource();
        list = dataSource.getSymbolsList();
    }


    //метод обмен валют параметры
    //srcCurrency = валюта которую хотим поменять
    //targetCurrency = валюта на которую хотим поменять
    //sum сумма, которую хотим поменять
    public void exchange(String srcCurrency, String targetCurrency, int sum){
        srcCurrency = srcCurrency.toUpperCase();     //Возводим строку в верхний регитр
        targetCurrency = targetCurrency.toUpperCase();
        String pair1 = srcCurrency+"/"+targetCurrency;  //Создаём пару
        String pair2 = targetCurrency+"/"+srcCurrency;
        Symbol found = null;
        for (Symbol symbol: list) {                   //ищем в списке нужную пару символов, которую будем использовать при обмене
            if (symbol.getSymbol().equals(pair1) || symbol.getSymbol().equals(pair2)) {
                found = symbol;
                break;
            }
        }
        if (found == null) {
            System.out.println("Валюта(ы) не найдена");
        } else {
            int sumInWallet = valuts.get(srcCurrency);  //проверяем хватает ли денег в кашельке
            if (sumInWallet<sum) {
                System.out.println("Недостаточно средств");
            } else {
                List<CurrencyPair> currencyPairs = dataSource.getPairBySymbol(found);
                if (currencyPairs != null && currencyPairs.size()>0) {
                    int newSum ;  // Сумм после конвертации
                    if (found.getSymbol().equals(pair1)) {
                        newSum = (int) (currencyPairs.get(0).getPrice() * sum);
                    } else {
                        newSum = (int) (sum / currencyPairs.get(0).getPrice());
                    }
                    valuts.put(srcCurrency, valuts.get(srcCurrency)-sum);
                    //Добавляем сумму в кошелек,в map list, если такого ключа(валюты) не было создаём
                    int tSum = valuts.containsKey(targetCurrency) ? valuts.get(targetCurrency) : 0;

                    hystory.add(sum+"-> "+found.getSymbol()+" -> "+newSum);
                } else {
                    System.out.println("что-то не так");
                }

            }
        }

    }
    public void add(){

    }
    public void cashIssue(){

    }
    public void getHystory(){

    }

    public void printHistory() {
        for (String s: hystory) {
            System.out.println(s);
        }
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "valuts=" + valuts +
                '}';
    }
}

