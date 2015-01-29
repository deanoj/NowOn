package com.deanoj.nowon.util;

import com.deanoj.nowon.data.ChannelNumber;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by deano on 16/01/15.
 */
public class RequestHelper {

    public static String getChannelQueryParameter(List<ChannelNumber> channels) {
        StringBuilder sb = new StringBuilder();
        for (ChannelNumber channel : channels) {
            if (sb.length() != 0) {
                sb.append(",");
            }
            sb.append(channel.getId());
        }
        return sb.toString();
    }

    public static String getDateStringHour(Calendar calendar)
    {
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return df.format(calendar.getTime());
    }
}
