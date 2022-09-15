import data.DataSource;
import entity.Wallet;
import manager.WalletManager;
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
        Wallet wallet = new Wallet();

        WalletManager manager = new WalletManager(wallet);

        manager.exchangeFromTo("usd","eur",10);
        manager.addCash("afr",100);
        manager.cashIssue("usd", 50);

        //manager.getHistory();
        wallet.getCurrMAp();
    }
}
