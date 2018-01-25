package com.clouway.networking.downloadagent;

/**
 * @author David Kostov (david.kostov.cw@gmail.com)
 */
public interface DownloadProgressListener {
    void onProgressUpdated(Integer progressInterval);
}
