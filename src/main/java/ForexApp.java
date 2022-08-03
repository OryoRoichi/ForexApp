import connection.Connection;

public class ForexApp {

    public static void main(String[] args) {
        Connection connection = new Connection();
        System.out.println(connection.getPair(2));
    }
}
