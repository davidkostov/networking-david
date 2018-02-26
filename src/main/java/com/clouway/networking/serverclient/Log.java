package com.clouway.networking.serverclient;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Kostov (david.kostov.cw@gmail.com)
 */
public abstract class Log {
    private List<String> log;

    public Log() {
        log = new ArrayList<>();
    }

    public void append(String input) {
        log.add(input);
    }

    public String getLastMessage() {
        return log.get(log.size() - 1);
    }

    public void displayMessage(String message) {
    }
}
