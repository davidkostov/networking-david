package com.clouway.networking.serverclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author David Kostov (david.kostov.cw@gmail.com)
 */
public class CustomClient {
    private Log log;
    private Socket socket;

    public CustomClient(Log log) {
        this.log = log;
    }

    public void connectTo(String hostName, Integer port) throws Exception {
        socket = new Socket(hostName, port);
        log.displayMessage("Client has booted up!\n");
    }

    public void terminate() {
        try {
            socket.close();
            log.displayMessage("Client disconnected!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void communicate() {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()))
        ) {
            String message = in.readLine();
            log.displayMessage(String.format("Client *echoes back* ~ %s\n", message));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public Log getLog() {
        return log;
    }
}
