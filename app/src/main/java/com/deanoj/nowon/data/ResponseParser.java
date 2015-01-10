package com.deanoj.nowon.data;

import com.deanoj.nowon.data.dto.Channel;
import com.deanoj.nowon.data.dto.EnquiryResults;
import com.deanoj.nowon.data.dto.TvListing;
import com.deanoj.nowon.util.DisplayHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by deano on 01/01/15.
 */
public class ResponseParser {

    private static ResponseParser instance = null;

    private EnquiryResults results;

    private JSONObject response;

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
        this.results = new EnquiryResults();

        try {
            parseChannels();
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
}
