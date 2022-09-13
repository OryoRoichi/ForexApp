package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOService implements IOServiceInterface {
    private BufferedReader reader;

    public IOService() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }
    public String read() throws IOException {
        return reader.readLine();
    }
    public void write(String message) {
        System.out.println(message);
    }

    public void writeUnknownError() {
        write("Неизвестная ошибка. Попробуйте еще раз");
    }

    public String toUpperCase(String str) {
        return str.toUpperCase();
    }

}
