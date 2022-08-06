import data.DataSource;
import model.Symbol;

import java.util.List;

public class ForexApp {

    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        Symbol s = new Symbol();
        s.setSymbol("EUR/USD");
        //System.out.println(dataSource.getPairById(16));
        //System.out.println(dataSource.getSymbolsList());
        List<Symbol> list = dataSource.getSymbolsList();
        for(int i = 0 ; i < list.size();i++ ){
            if(list.get(i).equals(s)){
                System.out.println(list.get(i));
            }
        }
    }
}
