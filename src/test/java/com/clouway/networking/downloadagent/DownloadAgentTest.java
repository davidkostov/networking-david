package com.clouway.networking.downloadagent;

import com.google.common.collect.Lists;
import com.google.common.io.ByteStreams;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author David Kostov (david.kostov.cw@gmail.com)
 */
public class DownloadAgentTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void downloadingIsConsistent() throws IOException {
        DownloadAgent downloadAgent = new DownloadAgent(new DownloadProgress());

        File outputFile = tempFolder.newFile();
        byte[] expectedContent = ByteStreams.toByteArray(DownloadAgentTest.class.getResourceAsStream("test.jpg"));

        downloadAgent.downloadFile(new ByteArrayInputStream(expectedContent), new FileOutputStream(outputFile));

        byte[] downloadedContent = ByteStreams.toByteArray(new FileInputStream(outputFile));

        assertArrayEquals(expectedContent, downloadedContent);
    }

    @Test
    public void progressIsUpdatedDuringDownloading() throws IOException {
        RecordingProgressListener progressListener = new RecordingProgressListener();
        DownloadAgent downloadAgent = new DownloadAgent(progressListener);

        downloadAgent.downloadFile(
                DownloadAgentTest.class.getResourceAsStream("test.jpg"),
                new FileOutputStream(tempFolder.newFile())
        );

        progressListener.assertHasReceivedUpdates(Lists.newArrayList(12, 24, 36, 48, 60, 72, 84, 100));
    }
}
