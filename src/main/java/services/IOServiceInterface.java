package services;

import java.io.IOException;

public interface IOServiceInterface {



    public interface IOService {
        String read() throws IOException;
        void write(String message);
        void writeUnknownError();
    }
}
