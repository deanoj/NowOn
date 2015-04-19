package com.deanoj.nowon.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.deanoj.nowon.R;
import com.deanoj.nowon.data.ChannelNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by deano on 31/01/15.
 */
public class RequestBuilder {

    private static final String TAG = "RequestBuilder";

    private static RequestBuilder instance;

    private Context context;

    private SharedPreferences preferences;

    private RequestBuilder(Context context) {
        this.context = context;
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);

    }
    public static RequestBuilder getInstance(Context context) {
        if (instance == null) {
            instance = new RequestBuilder(context);
        }
        return instance;
    }

    public String[] getUserChannelsArray() {
        Set<String> set = preferences.getStringSet(
                context.getString(R.string.id_user_channel_list),
                new HashSet<String>()
        );

        for (String channel : set) {
            Log.d(TAG, "channel: " + channel + ":" + ChannelNumber.get(channel));
        }

        ArrayList<String> channels = new ArrayList<String>();
        channels.addAll(set);

        Collections.sort(channels, channelComparator);


        return channels.toArray(new String[channels.size()]);
    }

    public String getUserChannelQueryString() {

        StringBuilder sb = new StringBuilder();
        for (String channelId : getUserChannelsArray()) {
            if (sb.length() != 0) {
                sb.append(",");
            }
            sb.append(channelId);
        }
        return sb.toString();
    }

    private Comparator<String> channelComparator = new Comparator<String>() {
        @Override
        public int compare(String lhs, String rhs) {
            return ChannelNumber.get(lhs).ordinal() - ChannelNumber.get(rhs).ordinal();
        }
    };
}
