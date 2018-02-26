package com.clouway.networking.serverclient;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static com.clouway.networking.CalendarUtil.february;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author David Kostov (david.kostov.cw@gmail.com)
 */
public class Task1ServerTest {

    private Log log = new ConsoleLog();
    private Integer port = 7777;
    private CustomServer server = new CustomServer(log, () -> february(2018, 12), port);

    @Test
    public void simpleConnectionTest() throws IOException {
        server.startAsync().awaitRunning();

        Socket socket = new Socket("localhost", port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("Client *echoes back* ~ " + in.readLine() + "\n");
        in.close();
        socket.close();

        server.stopAsync().awaitTerminated();
    }

    @Test
    public void dedicatedClientCommunication() throws Exception {
        server.startAsync().awaitRunning();

        CustomClient client = new CustomClient(log);
        client.connectTo("localhost", port);
        client.communicate();

        assertThat(client.getLog().getLastMessage(),
                equalTo(String.format("Client *echoes back* ~ %s", server.getMsgToSendToClient())));

        client.terminate();
        server.stopAsync().awaitTerminated();
    }
}
