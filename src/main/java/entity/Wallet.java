package entity;

import data.DataSource;
import model.CurrencyPair;
import model.Symbol;
import services.IOService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Wallet {
    IOService ioService = new IOService();
    DataSource dataSource = new DataSource();

    List<String> hystory;

    int currentState ;

    String currency;

    public Wallet(List<String> hystory, int currentState, String currency) {
        this.hystory = new ArrayList<>();
        this.currentState = currentState;
        this.currency = currency;
    }

    public void exchange(String Currency) throws IOException {
        String str = ioService.read();

        List <Symbol> list = dataSource.getPairBySymbol();
        for (int i = 0; i < list.size(); i++) {
            if
        }


    }
    public void add(){

    }
    public void cashIssue(){

    }
    public void getHystory(){

    }

}
