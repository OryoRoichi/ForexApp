package entity;

import services.*;
import util.StringUtils;

import java.io.IOException;
import java.util.Optional;

public class MenuOperation {

    private IOServiceImpl ioService;
    private WalletServiceImpl walletService;
    private UserServiceImpl userService;
    private UserData currentUser;

    public MenuOperation() {
        System.out.println("MenuOperatione smth wrong");
        this.ioService = new IOServiceImpl();
        this.walletService = new WalletServiceImpl();
        this.userService = new UserServiceImpl();
    }

    public void operationChoose() throws IOException {
        ioService.write("Выберите операцию:");
        ioService.write("Наберите 1 для обмена валюты");
        ioService.write("Наберите 2 для пополнения счета");
        ioService.write("Наберите 3 для снятия наличности");
        ioService.write("Наберите 4 для просмотра вашей истории транзакций");

        ioService.write("Введите 'exit' для выхода");
        final Wallet wallet = currentUser.getWallet();
        Integer operation = ioService.readOperation();
        switch (operation) {
            case 0:
                userService.logout(this);
            case 1:
                ioService.write("Введите какую валюту вы хотите поменять (например 'USD', 'EUR' и тд)");
                String fromCurr = ioService.read();
                ioService.write("Введите на какую валюту вы хотели бы поменять (например 'USD', 'EUR' и тд)");
                String toCurr = ioService.read();
                ioService.write("Введите сумму желаемой валюты");
                StringUtils.toIntNullSafe(ioService.read()).ifPresentOrElse(
                        value -> walletService.exchange(fromCurr, toCurr, value, wallet),
                        () -> ioService.write("неверный формат числа"));

                ifExit();
                break;
            case 2:
                ioService.write("Введите какую валюту вы хотите пополнить (например 'USD', 'EUR' и тд)");
                String currency = ioService.read();
                ioService.write("Введите сумму желаемой валюты");
                StringUtils.toIntNullSafe(ioService.read()).ifPresentOrElse(
                        value -> walletService.add(currency, value, wallet),
                        () -> ioService.write("неверный формат числа"));
                ifExit();
                break;
            case 3:
                ioService.write("Введите какую валюту вы хотите снять (например 'USD', 'EUR' и тд)");
                currency = ioService.read();
                ioService.write("Введите сумму желаемой валюты");
                StringUtils.toIntNullSafe(ioService.read())
                        .ifPresentOrElse(
                                value -> walletService.cashIssue(currency, value, wallet),
                                () -> ioService.write("неверный формат числа"));
                ifExit();
                break;
            case 4:
                ioService.write("Ваша история транзакций");
                ioService.readHistory(wallet);
                ifExit();

                break;
        }
    }

    public void enterMessage() {
        ioService.write("Наберите 1 Для того чтобы  зайти на свой кошелек");
        ioService.write("Наберите 2 Для регистрации");
        ioService.write("Введите exit для выхода");
        Integer operation = ioService.readOperation();
        switch (operation) {
            case 0:
                System.exit(0);
            case 1:
                authorization();
                break;
            case 2:
                registration();
                break;
        }
    }

    private void authorization() {
        ioService.write("ENTER Your Login");
        String login = ioService.read();
        ioService.write("ENTER Your Password");
        String password = ioService.read();
        userService.authorization(login, password).ifPresentOrElse(
                obj -> {
                    this.currentUser = obj;
                    try {
                        operationChoose();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    ioService.write("wrong LOgin or password");
                    enterMessage();
                });

    }

    private void registration() {
        ioService.write("To create new account You have to create login and password");
        ioService.write("Your Login");
        String login = ioService.read();
        ioService.write("Your Password");
        String password = ioService.read();
        userService.registration(login, password).ifPresentOrElse(
                value -> {
                    ioService.write("Welcome " + value.getLogin());
                    enterMessage();
                },
                () -> ioService.write("Such user is already exist"));

    }

    public void ifExit() {
        ioService.write("Желаете ли продолжить? y/n");
        try {
            if (ioService.read().equals("y")) {
                operationChoose();
            }
        } catch (IOException e) {
            ioService.writeUnknownError();
            ifExit();
        }
    }


}
