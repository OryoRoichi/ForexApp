package entity;
import services.*;

import java.io.IOException;

public class MenuOperation {

    private IOServiceImpl ioService;
    private WalletServiceImpl walletService;
    private  UserServiceImpl userService;

    public MenuOperation() {
        System.out.println("MenuOperatione smth wrong");
        this.ioService = new IOServiceImpl();
        this.walletService = new WalletServiceImpl();
        this.userService = new UserServiceImpl();
    }

    public void operationChoose(Wallet wallet) throws IOException {
        ioService.write("Выберите операцию:");
        ioService.write("Наберите 1 для обмена валюты");
        ioService.write("Наберите 2 для пополнения счета");
        ioService.write("Наберите 3 для снятия наличности");
        ioService.write("Наберите 4 для просмотра вашей истории транзакций");

        ioService.write("Введите 'exit' для выхода");
        Integer operation = ioService.readOperation();
        switch (operation) {
            case 0:
                wallet= null;
                userService.logout(this);
            case 1:
                ioService.write("Введите какую валюту вы хотите поменять (например 'USD', 'EUR' и тд)");
                String fromCurr = ioService.read();
                ioService.write("Введите на какую валюту вы хотели бы поменять (например 'USD', 'EUR' и тд)");
                String toCurr = ioService.read();
                ioService.write("Введите сумму желаемой валюты");
                int amount = Integer.parseInt(ioService.read());
                walletService.exchange(fromCurr, toCurr, amount, wallet);
                ifExit(wallet);
                break;
            case 2:
                ioService.write("Введите какую валюту вы хотите пополнить (например 'USD', 'EUR' и тд)");
                String currency = ioService.read();
                ioService.write("Введите сумму желаемой валюты");
                int sum = Integer.parseInt(ioService.read());
                walletService.add(currency, sum, wallet);
                ifExit(wallet);
                break;
            case 3:
                ioService.write("Введите какую валюту вы хотите снять (например 'USD', 'EUR' и тд)");
                 currency = ioService.read();
                ioService.write("Введите сумму желаемой валюты");
                 sum = Integer.parseInt(ioService.read());
                walletService.cashIssue(currency, sum, wallet);
                ifExit(wallet);
                break;
            case 4:
                ioService.write("Ваша история транзакций");
                ioService.readHistory(wallet);
                ifExit(wallet);

                break;
        }
    }

    public void enterMessage() {
        ioService.write("Наберите 1 Для того чтобы  зайти на свой кошелек");
        ioService.write("Наберите 2 Для регистрации");
        ioService.write("Введите 0 для выхода");
        Integer operation = ioService.readOperation();
        switch (operation) {
            case 0:
                return;
            case 1:
                authorization();
                break;
            case 2:
                registration();
                break;
        }
    }
    private  void authorization(){
        ioService.write("ENTER Your Login");
        String login = ioService.read();
        ioService.write("ENTER Your Password");
        String password = ioService.read();
        UserData user= userService.authorization(login,password);
        if(user !=null ){
            try {
                operationChoose(user.getWallet());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            ioService.write("wrong login or password");
            enterMessage();
        }
    }
    private void registration(){
        ioService.write("To create new account You have to create login and password");
        ioService.write("Your Login");
        String login = ioService.read();
        ioService.write("Your Password");
        String password = ioService.read();
        if(userService.registration(login,password)== null){
            ioService.write("Such user is already exist");
        }else{
            ioService.write("You Successfully create new account");
            enterMessage();
        }
    }




    public void ifExit(Wallet wallet) {
        ioService.write("Желаете ли продолжить? y/n");
        try {
            if (ioService.read().equals("y")) {
                operationChoose(wallet);
            }
        } catch (IOException e) {
            ioService.writeUnknownError();
            ifExit(wallet);
        }
    }


}
