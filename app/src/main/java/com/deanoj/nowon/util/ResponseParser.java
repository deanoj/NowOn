package com.deanoj.nowon.util;

import com.deanoj.nowon.data.results.Channel;
import com.deanoj.nowon.data.results.EnquiryResults;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by deano on 01/01/15.
 */
public class ResponseParser {

    private static ResponseParser instance = null;

    private EnquiryResults results = new EnquiryResults();

    private JSONObject response;

    private boolean started = false;

    protected ResponseParser() {}

    public static ResponseParser getInstance()
    {
        if (instance == null) {
            instance = new ResponseParser();
        }
        return instance;
    }

    public void parseResponse(JSONObject response)
    {
        this.response = response;

        try {
            parseChannels();
            this.started = true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseChannels() throws JSONException
    {
        JSONArray channelListing = this.response.getJSONArray("Channels");

        for (int i = 0; i < channelListing.length(); i++)
        {
            Channel channel = new Channel(channelListing.getJSONObject(i));
            results.getChannels().add(channel);
        }
    }

    public EnquiryResults getResults() {
        return results;
    }

    public boolean isStarted() {
        return started;
    }
}
