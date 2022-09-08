import data.DataSource;
import entity.Wallet;
import model.Symbol;
import model.history.History;
import model.history.Period;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ForexApp {

    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        Symbol s = new Symbol();
        s.setSymbol("EUR/USD");
        Wallet wallet = new Wallet(1011);
//      Collection<History> test =  dataSource.getHistory(s,1, Period.DAY);
//
//        for(History elem : test){
//            System.out.println(elem);
//        }
        wallet.exchange("usd","eur",10);
        wallet.add("afr",100);
        wallet.cashIssue("usd", 50);
        wallet.add("usd", 0);
        wallet.getHistoryById(1011);

        wallet.printCurrMap();

    }
}
