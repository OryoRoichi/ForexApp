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

        IOService ioService = new IOService();
        System.out.println("1 валюта");

        String v1 = ioService.read();
        System.out.println("1 валюта");

        String v2 = ioService.read();

        String sum = ioService.read();
        wallet.exchange(v1, v2, Integer.parseInt(sum));
        System.out.println(wallet.toString());

        wallet.printHistory();

    }
}