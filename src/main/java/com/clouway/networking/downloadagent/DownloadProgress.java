package com.clouway.networking.downloadagent;

/**
 * @author David Kostov (david.kostov.cw@gmail.com)
 */
public class DownloadProgress implements DownloadProgressListener {
    public void onProgressUpdated(Integer progressPercent) {
        System.out.println("Current progress: " + progressPercent + "%");
    }
}
