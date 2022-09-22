package entity;

import java.util.ArrayList;
import java.util.List;

public class UserData {


    private Wallet wallet;
    private String login;
    private String password;
    private UserListData listData;


    public UserData() {

    }

    public UserData(String login, String password) {
        this.wallet = new Wallet();
        this.login = login;
        this.password = password;

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
