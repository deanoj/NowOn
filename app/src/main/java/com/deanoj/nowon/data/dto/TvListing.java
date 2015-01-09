package com.deanoj.nowon.data.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by deano on 02/01/15.
 */
public class TvListing {

    private String title;

    private String description;

    private Date startTime;

    private Date endTime;

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


}
