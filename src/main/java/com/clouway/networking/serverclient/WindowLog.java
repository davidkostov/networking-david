package com.clouway.networking.serverclient;

import javax.swing.*;
import java.awt.*;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

/**
 * @author David Kostov (david.kostov.cw@gmail.com)
 */
public class WindowLog extends Log {
    private JTextArea log;
    private JFrame frame;

    public WindowLog() {
        frame = new JFrame("Server/Client - WindowLog");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        log = new JTextArea(5, 35);
        log.setMargin(new Insets(5, 5, 5, 5));
        log.setEditable(false);

        JScrollPane logScrollPane = new JScrollPane(log);
        logScrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        frame.setContentPane(panel);
        panel.add(logScrollPane, BorderLayout.CENTER);

        frame.setSize(420, 300);
        frame.setVisible(true);
    }

    public void displayMessage(String message) {
        super.append(message);
        log.append(message);
    }
}
