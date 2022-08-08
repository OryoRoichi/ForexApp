package entity;



import java.util.HashSet;
import java.util.Set;

public class User {

    Wallet wallet;

    public User() {
        this.wallet = new Wallet(0,"USD");
    }
}

