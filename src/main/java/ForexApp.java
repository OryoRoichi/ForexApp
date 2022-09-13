import data.DataSource;
import entity.Wallet;
import model.Symbol;
import model.history.History;
import model.history.Period;
import services.IOService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ForexApp {




    public static void main(String[] args) {
        IOService ioService = new IOService() ;
        String s = "abc";
        System.out.println(ioService.toUpperCase(s));
    }
}
       /* DataSource dataSource = new DataSource();
            }

        wallet.exchange("usd","eur",10);
        wallet.add("afr",100);
        wallet.cashIssue("usd", 50);
        wallet.add("usd", 0);
        wallet.getHistoryById(1011);

        wallet.printCurrMap();//

    }
} */
