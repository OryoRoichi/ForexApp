package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import entity.Wallet;
import entity.WalletHistory;
import model.history.History;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
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

    public void writeHistoryLog(WalletHistory history, int id) throws JsonProcessingException {
        File directory = new File("src\\main\\resources\\Historylog\\");
        String historyFileName = "wallet" + id + ".json";
        File name = new File(getFileName(directory, historyFileName));
        String json = objectMapper.writeValueAsString(history);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name, true))) {
            writer.write(json);
        } catch (IOException e) {
            System.out.println(" err: cant wright to JSON");
        }
    }

    private String getFileName(File file, String log) {
        return file.getAbsolutePath() + File.separator + log;
    }

    public void readHistory(Wallet wallet) throws IOException {
        File file = new File("src\\main\\resources\\Historylog\\" + "wallet" + wallet.getId() + ".json");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<WalletHistory> list = new ArrayList<>();
        String tmpStr;
        while (reader.readLine() != null) {
            tmpStr = reader.readLine();
        }

        list = objectMapper.readValue((Reader) reader, (JavaType) list);
        list.stream().forEach((elem)-> System.out.println(elem));


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
