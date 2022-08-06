package entity;

import java.util.HashSet;
import java.util.Set;

public class User {

    Set<Wallet> walletSet;

    public User(Set<Wallet> walletSet) {
        this.walletSet = new HashSet<>();
    }
}
