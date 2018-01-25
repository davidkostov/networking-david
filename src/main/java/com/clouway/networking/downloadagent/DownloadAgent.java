package com.clouway.networking.downloadagent;

import java.io.*;

/**
 * @author David Kostov (david.kostov.cw@gmail.com)
 */
public class DownloadAgent {

    private final Double progressDisplayInterval = 7500.0;
    private final DownloadProgressListener progressListener;

    public DownloadAgent(DownloadProgressListener listener) {
        progressListener = listener;
    }

    public void downloadFile(InputStream in, OutputStream out) throws IOException {
        Integer fileSize = in.available();
        Integer progressPercent = 0;
        int counter = 0;
        int transferData;
        transferData = in.read();
        while (transferData != -1) {
            if (++counter >= progressDisplayInterval && counter % progressDisplayInterval == 0) {
                progressPercent = calculateProgress(progressPercent, progressDisplayInterval, fileSize);
                progressListener.onProgressUpdated(progressPercent);
            }
            out.write(transferData);
            transferData = in.read();
        }
        progressPercent = calculateProgress(progressPercent, (double) counter, fileSize);
        progressListener.onProgressUpdated(progressPercent);
    }

    private Integer calculateProgress(Integer progress, Double amountToCalculateInPercent, Integer totalSize) {
        progress += (int) ((amountToCalculateInPercent / totalSize) * 100); //relative due to relative file size
        if (progress > 100) progress = 100;
        return progress;
    }
}
