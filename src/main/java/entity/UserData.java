package entity;

import java.util.ArrayList;
import java.util.List;

public class UserData {

    public static List<UserData> listOfUser;
    private Wallet wallet;
    private String login;
    private String password;


    public UserData() {
        listOfUser = new ArrayList<>();
    }

    public UserData(String login, String password) {
        this.wallet = new Wallet();
        this.login = login;
        this.password = password;

        listOfUser.add(this);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
