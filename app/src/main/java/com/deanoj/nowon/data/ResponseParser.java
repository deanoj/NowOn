package com.deanoj.nowon.data;

import com.deanoj.nowon.data.dto.Channel;
import com.deanoj.nowon.data.dto.EnquiryResults;
import com.deanoj.nowon.data.dto.TvListing;
import com.deanoj.nowon.util.DateHelper;

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
            Channel channel = new Channel();
            channel.setDisplayName(channelListing.getJSONObject(i).getString("DisplayName"));

            channel.setTvListings(getTvListings(channelListing.getJSONObject(i).getJSONArray("TvListings")));

            results.getChannels().add(channel);
        }
    }

    private List<TvListing> getTvListings(JSONArray listings) throws JSONException
    {
        List<TvListing> channelTvListing = new ArrayList<TvListing>();

        for (int i = 0; i < listings.length(); i++)
        {
            TvListing tvListing = new TvListing();
            JSONObject listing = listings.getJSONObject(i);
            tvListing.setTitle(listing.getString("Title"));
            tvListing.setDescription(listing.getString("Description"));
            try {
                tvListing.setStartTime(DateHelper.parseDateString(listing.getString("StartTimeMF")));
                tvListing.setEndTime(DateHelper.parseDateString(listing.getString("EndTimeMF")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            channelTvListing.add(tvListing);
        }
        return channelTvListing;
    }

    public EnquiryResults getResults() {
        return results;
    }
}
