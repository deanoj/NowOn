package com.deanoj.nowon.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by deano on 03/01/15.
 */
public class DateHelper {

    public static String toDateString(Date date)
    {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return df.format(date);
    }
}
