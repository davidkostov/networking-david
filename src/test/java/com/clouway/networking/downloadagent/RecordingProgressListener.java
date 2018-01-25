package com.clouway.networking.downloadagent;

import com.google.common.collect.Lists;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * @author David Kostov (david.kostov.cw@gmail.com)
 */
public class RecordingProgressListener implements DownloadProgressListener {
    private List<Integer> updates = Lists.newArrayList();

    public void onProgressUpdated(Integer progress) {
        updates.add(progress);
    }

    public void assertHasReceivedUpdates(List<Integer> updates) {
        assertThat(this.updates, is(equalTo(updates)));
    }
}
