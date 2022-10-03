package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import entity.UserData;
import entity.Wallet;
import entity.WalletHistory;
import entity.WalletHistoryList;
import model.history.History;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class IOServiceImpl implements IOService {
    private final BufferedReader reader;
    private final ObjectMapper objectMapper;


    public IOServiceImpl() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.objectMapper = new ObjectMapper();
    }

    public String read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public void write(String message) {
        System.out.println(message);
    }

    public void writeHistoryLog(Wallet wallet) throws JsonProcessingException {
        File file = new File("src\\main\\resources\\Historylog\\" + "wallet" + wallet.getId() + ".json");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
             objectMapper.writeValue(writer, wallet.getHistoryList().toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(" err: cant wright to JSON");
        }
    }

    private String getFileName(File file, String log) {
        return file.getAbsolutePath() + File.separator + log;
    }

    public void readHistory(Wallet wallet) throws IOException {
        File file = new File("src\\main\\resources\\Historylog\\" + "wallet" + wallet.getId() + ".json");
        WalletHistoryList historyList;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            historyList = objectMapper.readValue(reader, WalletHistoryList.class);
            historyList.toString();

        }

    }

    public void saveUser(UserData user) throws JsonProcessingException {
        File file = new File("src\\main\\resources\\UserDatabase\\User_" + user.getLogin() + "_" + user.getPassword().hashCode() + ".json");
        String json = objectMapper.writeValueAsString(user);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(json);
        } catch (IOException e) {
            System.out.println(" err: cant wright to JSON");
        }
    }

    public UserData readUser(String login, String password) throws IOException {
        File directory = new File("src\\main\\resources\\UserDatabase\\");

        FileFilter filefilter = file -> {
            if (file.getName().equals("User_" + login + "_" + password.hashCode() + ".json")) {
                return true;
            }
            return false;
        };
        File[] files = directory.listFiles(filefilter);
        BufferedReader reader = new BufferedReader(new FileReader(files[0]));
        UserData user = objectMapper.readValue(reader, UserData.class);
        return user;
    }

    public void writeUnknownError() {
        write("Неизвестная ошибка. Попробуйте еще раз");
    }

    public int readOperation() {
        String operation;
        if (!(operation = read()).equals("exit")) {
            int operationNumber = Integer.parseInt(operation);
            return operationNumber;
        }
        return 0;
    }

}
