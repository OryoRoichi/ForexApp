package entity;

import java.util.HashSet;
import java.util.Set;

public class User {
    String name;

    Wallet wallet;

    public User(String name) {
        this.wallet = new Wallet();
        this.name= name;
    }
}
