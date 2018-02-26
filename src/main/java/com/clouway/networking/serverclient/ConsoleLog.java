package com.clouway.networking.serverclient;

/**
 * @author David Kostov (david.kostov.cw@gmail.com)
 */
public class ConsoleLog extends Log {
    public void displayMessage(String message){
        super.append(message);
        System.out.println(message);
    }
}
