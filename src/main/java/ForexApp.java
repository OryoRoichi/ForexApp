import data.DataSource;
import entity.Wallet;
import model.Symbol;

import java.util.List;
/*
user (wallet)
Wallet(Map<Currency,currentState>,List<history>)
History(user,operation,date)
Enum operation
 */
public class ForexApp {

    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        Symbol s = new Symbol();
        s.setSymbol("EUR/USD");
        Wallet wallet = new Wallet();

        wallet.exchange("usd","eur",10);
        wallet.add("afr",100);
        wallet.cashIssue("usd", 50);
        wallet.printCurrMap();

    }
}
