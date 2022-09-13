package entity;
import services.*;

import java.io.IOException;

public class MenuOperation {

    private IOService ioService;
    private Wallet wallet;

    public void run() throws IOException {
        ioService.write("Hello");

        operationChoose();
    }

    private void operationChoose() throws IOException {
        ioService.write("Выберите операцию:");
        ioService.write("Наберите 1 для обмена валюты");
        ioService.write("Наберите 2 для пополнения счета");
        ioService.write("Наберите 3 для снятия наличности");
        ioService.write("Наберите 4 для просмотра истории транзакций по ID");

        ioService.write("Введите 'exit' для выхода");
        Integer operation = readOperation();
        switch (operation) {
            case 1:
                ioService.write("Введите какую валюту вы хотите поменять (например 'USD', 'EUR' и тд)");
                String fromCurr = ioService.read();
                ioService.write("Введите на какую валюту вы хотели бы поменять (например 'USD', 'EUR' и тд)");
                String toCurr = ioService.read();
                ioService.write("Введите сумму желаемой валюты");
                int amount = Integer.parseInt(ioService.read());
                wallet.exchange(fromCurr, fromCurr, amount);
                ifExit();

                break;
            case 2:
                ioService.write("Введите какую валюту вы хотите пополнить (например 'USD', 'EUR' и тд)");
                String currency = ioService.read();
                ioService.write("Введите сумму желаемой валюты");
                int sum = Integer.parseInt(ioService.read());
                wallet.add(currency, sum);
                ifExit();
                break;
            case 3:
                ioService.write("Введите какую валюту вы хотите снять (например 'USD', 'EUR' и тд)");
                 currency = ioService.read();
                ioService.write("Введите сумму желаемой валюты");
                 sum = Integer.parseInt(ioService.read());
                wallet.cashIssue(currency, sum);
                ifExit();
                break;
            case 4:
                ioService.write("Введите свой ID");
                int id = Integer.parseInt(ioService.read());
                wallet.getHistoryById(id);
                ifExit();

                break;
            case 5:


                break;
            default:
        }
    }

    private int readOperation() {
        String operation;
        try {
            if (!(operation = ioService.read()).equals("exit")) {
                Integer operationNumber = Integer.parseInt(operation);
                return operationNumber;
            }
        } catch (IOException e) {
            ioService.writeUnknownError();
            readOperation();
        }
        return 0;
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void ifExit() {
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
