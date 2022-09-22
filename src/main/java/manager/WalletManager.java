package manager;

import entity.MenuOperation;
import entity.UserData;
import services.IOServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class WalletManager {
    public List<UserData> listOfUser;
    private IOServiceImpl ioService;
    private MenuOperation menuOperation;


    public WalletManager() {
        this.menuOperation = new MenuOperation();
        this.ioService = new IOServiceImpl();
        this.listOfUser = new ArrayList<>();
    }
    public void run()  {
        ioService.write("Hello");
        menuOperation.enterMessage();
    }

}
