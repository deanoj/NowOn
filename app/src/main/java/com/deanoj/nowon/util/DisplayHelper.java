package com.deanoj.nowon.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by deano on 03/01/15.
 */
public class DisplayHelper {

    public static String toDateString(Date date)
    {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return df.format(date);
    }

    public static Date parseDateString(String dateString) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = df.parse(dateString);
        return date;
    }

    public static String getFormattedShowTime(Date startTime, Date endTime) {
        DateFormat df = new SimpleDateFormat("h:mma");
        StringBuilder sb = new StringBuilder();
        sb.append(df.format(startTime));
        sb.append(" - ");
        sb.append(df.format(endTime));
        return sb.toString();
    }
}
