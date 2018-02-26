package com.clouway.networking.serverclient;

import java.util.Scanner;

/**
 * @author David Kostov (david.kostov.cw@gmail.com)
 */
public class ServerApp {
    public static void main(String[] args) {
        final int port = 7777;
        WindowLog wDisplay = new WindowLog();
        CustomServer server = new CustomServer(wDisplay, new TestClock(), port);
        server.startAsync().awaitRunning();
        Scanner scanner = new Scanner(System.in);
        while (!scanner.next().equals("stop")) {
        }
        server.stopAsync().awaitTerminated();
    }
}
