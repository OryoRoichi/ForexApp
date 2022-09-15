package entity;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Wallet wallet;

    private String login;
    private String password;

    public User() {
        this.wallet = new Wallet();
    }
}
