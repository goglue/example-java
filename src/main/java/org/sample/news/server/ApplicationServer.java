package org.sample.news.server;

import java.io.IOException;

/**
 * Created by moath on 27.06.17.
 */
public interface ApplicationServer {
    public void start() throws IOException;

    public void stop();

    public void gracefullyStop();

    public void waitForSignal() throws InterruptedException;
}
