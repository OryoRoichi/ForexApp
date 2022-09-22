package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Wallet;
import entity.WalletHistory;

import java.io.*;
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
        File directory = new File("src\\main\\res\\Historylog\\");
        String json = objectMapper.writeValueAsString(history);
        String historyFileName = "wallet" + id + ".json";
        File name = new File(getFileName(directory, historyFileName));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name, true))) {
            writer.write(json + "\n");
        } catch (IOException e) {
            System.out.println(" err: cant wright to JSON");
        }
    }

    private String getFileName(File file, String log) {
        return file.getAbsolutePath() + File.separator + log;
    }

    public void readHistory(Wallet wallet) throws IOException {
        File file = new File("src\\main\\res\\Historylog\\" + "wallet" + wallet.getId());
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String str;
//        List<History> historyList = new ArrayList<>();
//        History tmp = objectMapper.readValue(reader, History.class);
//        if(tmp.getOperation() == operation){
//            while ((str = reader.readLine()) != null)
//            {
//                historyList = reader.readLine();
//                System.out.println(str);
//            }
//        }
        while ((str = reader.readLine()) != null) {
            System.out.println(str);
        }
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
