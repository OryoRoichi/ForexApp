package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Wallet;
import entity.WalletHistory;
import entity.enumiration.Operation;

import java.io.*;
import java.util.Map;

public class IOService {
    private BufferedReader reader;
    private ObjectMapper objectMapper ;

    public IOService() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.objectMapper = new ObjectMapper();
    }

    public String read() throws IOException {return reader.readLine();}
    public void write(String message) {
        System.out.println(message);
    }

    public void writeHistoryLog(WalletHistory history,int id) throws JsonProcessingException {
        File directory = new File("src\\main\\res\\Historylog\\");
        String json = objectMapper.writeValueAsString(history);
        String historyFileName = "wallet" + String.valueOf(id);
        File name = new File(getFileName(directory, historyFileName));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name, true))) {
            writer.write(json+"\n");
        } catch (IOException e) {
            System.out.println(" err: cant wright to JSON");
        }
    }
    private String getFileName(File file, String log) {
        return file.getAbsolutePath() + File.separator + log;
    }

    public void readHistory(Operation operation, int id) throws IOException {
        File file = new File("src\\main\\res\\Historylog\\" + "wallet" + String.valueOf(id));
        BufferedReader reader = new BufferedReader(new FileReader(file));
//        String str;
//        List<History> historyList = new ArrayList<>();
//        History tmp = objectMapper.readValue(reader, History.class);
//        if(tmp.getOperation() == operation){
//            while ((str = reader.readLine()) != null)
//            {
//                historyList = reader.readLine();
//                System.out.println(str);
//            }
//        }
    }
    public void printCurrMap(Wallet wallet) {
        for (Map.Entry<String, Integer> entry : wallet.getCurrMAp().entrySet()) {
            System.out.println(entry);
        }
    }

}
