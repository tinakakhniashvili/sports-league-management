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
        try {
            out.write("[INFO] " + msg);
            out.newLine();
        } finally {
            try { out.flush(); } catch (IOException ignored) {}
        }
    }

    public void error(String msg) throws IOException {
        try {
            out.write("[ERROR] " + msg);
            out.newLine();
        } finally {
            try { out.flush(); } catch (IOException ignored) {}
        }
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
