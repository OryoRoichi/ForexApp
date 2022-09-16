package manager;

import entity.Wallet;
import entity.WalletHistory;
import entity.enumiration.Operation;
import services.IOService;
import services.WalletService;

import java.io.IOException;

public class WalletManager {
    private  IOService ioService;
    private WalletService walletService;
    private Wallet wallet;
    private WalletHistory walletHistory;

    public WalletManager(Wallet wallet) {
        this.ioService = new IOService();
        this.walletService = new WalletService();
        this.wallet = wallet;
        this.walletHistory = new WalletHistory();
    }

    public void exchangeFromTo(String fromCurr, String toCurr, int amount){
        walletService.exchange(fromCurr, toCurr,  amount, wallet);
    }
    public void addCash(String toCurr, int amount){
        walletService.add(toCurr, amount, wallet);
    }
    public void cashIssue(String toCurr, int amount){
        walletService.add(toCurr, amount, wallet);
    }

    public void getHistory(Operation operation){
        try {
            ioService.readHistory(operation, wallet.getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
