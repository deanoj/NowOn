package com.deanoj.nowon.data.dto;

import com.deanoj.nowon.util.DisplayHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by deano on 02/01/15.
 */
public class TvListing {

    private static final String TITLE = "Title";
    private static final String DESCRIPTION = "Description";
    private static final String START_TIME_MF = "StartTimeMF";
    private static final String END_TIME_MF = "EndTimeMF";

    private String title;

    private String description;

    private Date startTime;

    private Date endTime;

    public TvListing(JSONObject object) {
        try {
            this.title = object.getString(TITLE);
            this.description = object.getString(DESCRIPTION);
            this.startTime = DisplayHelper.parseDateString(object.getString(START_TIME_MF));
            this.endTime = DisplayHelper.parseDateString(object.getString(END_TIME_MF));

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getFormattedEndTime() {
        DateFormat df = new SimpleDateFormat("h:mma");
        return df.format(endTime);
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public static ArrayList<TvListing> fromJson(JSONArray objects) {
        ArrayList<TvListing> listings = new ArrayList<TvListing>();
        for (int i = 0; i < objects.length(); i++) {
            try {
                listings.add(new TvListing(objects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return listings;
    }


}
