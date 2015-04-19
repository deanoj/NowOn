package com.deanoj.nowon.util;

import android.util.Log;

import com.deanoj.nowon.data.results.Channel;
import com.deanoj.nowon.data.results.EnquiryResults;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by deano on 01/01/15.
 */
public class ResponseParser {

    public static final String TAG = "ResponseParser";

    private static ResponseParser instance = null;

    private EnquiryResults results = new EnquiryResults();

    private JSONObject response;

    private boolean started = false;

    protected ResponseParser() {}

    private final Calendar time = Calendar.getInstance();

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

    public void setStartHour(int hour) {
        time.set(Calendar.HOUR_OF_DAY, hour);
        Log.d(TAG, "Calendar set by hour to: " + time.toString());
    }

    public void setStartDate(Calendar calendar) {
        time.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
        time.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        time.set(Calendar.YEAR, calendar.get(Calendar.YEAR));

    }

    public void setStartDate(int year, int month, int day) {
        time.set(Calendar.DAY_OF_MONTH, day);
        time.set(Calendar.MONTH, month);
        time.set(Calendar.YEAR, year);

    }

    public Calendar getTime() {
        return this.time;
    }

    public String[] getUserChannels() {

        return null;
    }


}
