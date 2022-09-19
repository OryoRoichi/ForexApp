package manager;

import entity.MenuOperation;
import services.IOServiceImpl;


public class WalletManager {
    private IOServiceImpl ioService;
    private MenuOperation menuOperation;


    public WalletManager() {
        this.menuOperation = new MenuOperation();
        this.ioService = new IOServiceImpl();

    }
    public void run()  {
        ioService.write("Hello");
        menuOperation.enterMessage();
    }

}
