package com.deanoj.nowon.data.results;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deano on 03/01/15.
 */
public class EnquiryResults {

    private Legend legend = new Legend();

    private List<Channel> channels = new ArrayList<Channel>();

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }
}
