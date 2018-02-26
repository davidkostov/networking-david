package com.clouway.networking.serverclient;

/**
 * @author David Kostov (david.kostov.cw@gmail.com)
 */
public class ClientApp {
    public static void main(String[] args) {
        final int port = 7777;
        ConsoleLog cLog = new ConsoleLog();
        CustomClient client = new CustomClient(cLog);
        try {
            client.connectTo("localhost", port);
            client.communicate();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        finally {
            client.terminate();
        }
    }
}
