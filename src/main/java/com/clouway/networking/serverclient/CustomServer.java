package com.clouway.networking.serverclient;

import com.google.common.util.concurrent.AbstractExecutionThreadService;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author David Kostov (david.kostov.cw@gmail.com)
 */
public class CustomServer extends AbstractExecutionThreadService {
    private final int port;
    private final String msgOnSuccessfulConnect;
    private String msgToSendToClient;

    private Log log;
    private ServerSocket socket;
    private Socket clientSocket;
    private Clock clock;

    public CustomServer(Log log, Clock clock, Integer port) {
        this.log = log;
        this.clock = clock;
        this.port = port;
        msgOnSuccessfulConnect = "Server ~ New client connected!\n";
        msgToSendToClient = String.format("Hello! - %s\n", clock.now());
    }

    @Override
    protected void startUp() throws Exception {
        socket = new ServerSocket(port);
        log.displayMessage("Server has booted up!\n");
    }

    @Override
    protected void triggerShutdown() {
        try {
            socket.close();
            log.displayMessage("Server has shut down!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void run() {
        while (isRunning()) {
            PrintWriter out = null;
            try {
                clientSocket = socket.accept();
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                log.displayMessage(msgOnSuccessfulConnect);
                msgToSendToClient = String.format("Hello! - %s\n", clock.now());
                log.displayMessage("Server ~ " + msgToSendToClient);
                out.print(msgToSendToClient);
            } catch (IOException e) {
                break;
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
    }

    public String getMsgToSendToClient() {
        return msgToSendToClient;
    }
}
