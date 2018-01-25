package com.clouway.networking.downloadagent;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

/**
 * @author David Kostov (david.kostov.cw@gmail.com)
 */
public class Main {
    public static void main(String[] args) {
        DownloadAgent dlAgent = new DownloadAgent(new DownloadProgress());
        String urlString, savePath;

        Scanner scanner = new Scanner(System.in);
        urlString = scanner.nextLine();
        savePath = scanner.nextLine();

        InputStream in = null;
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(savePath))) {
            in = new BufferedInputStream(new URL(urlString).openConnection().getInputStream());
            dlAgent.downloadFile(in, out);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
