package entity;

import java.util.ArrayList;
import java.util.List;

public class UserData {

    private long id;
    private Wallet wallet;
    private String login;
    private String password;
    private UserListData listData;
    private long walletID;


    public UserData() {

    }

    public UserData(String login, String password) {
        this.wallet = new Wallet();
        this.login = login;
        this.password = password;
        this.walletID = wallet.getId();
        this.id = hashCode();

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
    public long getId() {
        return id;
    }
    public long getWalletID() {
        return walletID;
    }
}
