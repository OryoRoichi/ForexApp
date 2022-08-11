package entity;

import data.DataSource;
import entity.enumiration.Operation;
import model.CurrencyPair;
import model.Symbol;

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
          this.dataSource=new DataSource();
          this.currMAp = new HashMap<>();
          this.historyList = new ArrayList<>();
          this.currentState = 100;
          this.currency = "USD";
          currMAp.put(currency,currentState);//упаковываем валюту в мапу
          list = dataSource.getSymbolsList();
     }

     public void exchange(String fromCurr, String toCurr, int amount) {
          fromCurr = fromCurr.toUpperCase();     //Возводим строку в верхний регитр
          toCurr = toCurr.toUpperCase();
          String pair1 = fromCurr + "/" + toCurr;  //Создаём пару
          String pair2 = toCurr + "/" + fromCurr;
          Symbol found = null;
          for (Symbol symbol : list) {                   //ищем в списке нужную пару символов, которую будем использовать при обмене
               if (symbol.getSymbol().equals(pair1) || symbol.getSymbol().equals(pair2)) {
                    found = symbol;
                    break;
               }
          }
          if (found == null) {
               System.out.println("Валюта(ы) не найдена");
          } else {
               int sumInWallet = currMAp.get(fromCurr);  //проверяем хватает ли денег в кашельке
               if (sumInWallet < amount) {
                    System.out.println("Недостаточно средств");
               } else {
                    List<CurrencyPair> currencyPairs = dataSource.getPairBySymbol(found);
                    if (currencyPairs != null && currencyPairs.size() > 0) {
                         int newSum;  // Сумм после конвертации
                         if (found.getSymbol().equals(pair1)) {
                              newSum = (int) (currencyPairs.get(0).getPrice() * amount);
                         } else {
                              newSum = (int) (amount / currencyPairs.get(0).getPrice());
                         }
                         currMAp.put(fromCurr, currMAp.get(fromCurr) - amount);
                         currMAp.put(toCurr, newSum);
                         historyList.add(new History(this, Operation.EXCHANGE));
                    } else {
                         System.out.println("что-то не так");
                    }
               }
          }
     }
     public void add(String toCurr, int amount){
          toCurr = toCurr.toUpperCase();
          if(currMAp.containsKey(toCurr)){
               currMAp.put(toCurr, currMAp.get(toCurr) + amount);
               historyList.add(new History(this, Operation.ADD));

          }
          else {
               System.out.println("NOSuchCurrency");
          }

     }
     public void cashIssue(String toCurr, int amount){
          toCurr = toCurr.toUpperCase();
          if(currMAp.containsKey(toCurr)){
               currMAp.put(toCurr, currMAp.get(toCurr) - amount);
               historyList.add(new History(this, Operation.ISSUE));
          }
          else {
               System.out.println("NOSuchCurrency");
          }
     }
     public void getHystory(){
          for (History s : historyList) {
               System.out.println(s);
          }
     }
     public void printCurrMap(){
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
