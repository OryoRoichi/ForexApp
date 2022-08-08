import data.DataSource;
import model.Symbol;

import data.DataSource;
import entity.Wallet;
import model.CurrencyPair;
import model.Symbol;
import services.IOService;

import java.io.IOException;
import java.util.List;

public class ForexApp {


    /*
user (wallet)
Wallet(Map<Currency,currentState>,List<history>)
History(user,operation,date)
Enum operation
     */

    public static void main(String[] args) throws IOException {
        DataSource dataSource = new DataSource();
        //Symbol s = new Symbol();
        //s.setSymbol("EUR/USD");
        //System.out.println(dataSource.getPairById(16));
        // System.out.println(dataSource.getSymbolsList());
        //List<Symbol> list = dataSource.getSymbolsList();
        //for(int i = 0 ; i < list.size();i++ ){
        //if(list.get(i).equals(s)){
        // System.out.println(list.get(i));
        //}
        //}

        /*List<CurrencyPair> currencyPairs = dataSource.getPairBySymbol(list.get(0));
        for (CurrencyPair pair: currencyPairs) {
            System.out.println(pair);
        }*/

        Wallet wallet = new Wallet(10000, "usd");
        System.out.println(wallet.toString());
        wallet.exchange("usd", "eur", 1000);
        System.out.println(wallet.toString());

        wallet.printHistory();

    }
}