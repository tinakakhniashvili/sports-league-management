package services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class BookingLog implements AutoCloseable {

    private final BufferedWriter out;

    public BookingLog(String path) throws IOException {
        this.out = new BufferedWriter(new FileWriter(path, true));
    }


    public void info(String msg) throws IOException {
        out.write("[INFO] " + msg);
        out.newLine();
        out.flush();
    }

    public void error(String msg) throws IOException {
        out.write("[ERROR] " + msg);
        out.newLine();
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }

}
