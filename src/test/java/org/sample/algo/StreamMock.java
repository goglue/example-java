package org.sample.algo;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 */
public class StreamMock extends OutputStream {
    private ArrayList<String> buffer = new ArrayList<String>();

    public void write(byte[] bytes) throws IOException {
        this.buffer.add(new String(bytes));
    }

    public String getBuffer() {
        StringBuilder value = new StringBuilder();
        for (String s: this.buffer) {
            value.append(s);
        }

        return value.toString();
    }

    public void write(int bytes) throws IOException {

    }
}
