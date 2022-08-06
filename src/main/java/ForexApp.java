import data.DataSource;
import model.Symbol;

public class ForexApp {

    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        Symbol s = new Symbol();
        s.setSymbol("EUR/USD");
        System.out.println(dataSource.getSymbolsList());
    }
}
