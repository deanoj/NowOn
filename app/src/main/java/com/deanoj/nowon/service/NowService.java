package com.deanoj.nowon.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.deanoj.nowon.data.ChannelNumber;
import com.deanoj.nowon.data.results.EnquiryResults;
import com.deanoj.nowon.util.RequestHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deano on 13/01/15.
 */
public class NowService extends Service {

    private final IBinder mBinder = new LocalBinder();

    private static final String TAG = "NowService";

    private static final String GITHUB_URL = "https://raw.githubusercontent.com/deanoj/deanoj.github.io/master/assets/tv.txt";

    private static final String TEST_URL = "http://192.168.20.20/default/tv.txt";

    private final EnquiryResults results = new EnquiryResults();

    private SharedPreferences sharedPreferences;

    private final ArrayList<ChannelNumber> userChannels = new ArrayList<>();

    private boolean requiresUpdate = true;

    public class LocalBinder extends Binder {
        public NowService getService() {
            return NowService.this;
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "service started");

        // read selected channels preferences
//        sharedPreferences = getApplicationContext().getSharedPreferences(
//                "channelSelection", Context.MODE_PRIVATE
//        );
        userChannels.add(ChannelNumber.BBC_ONE);
        userChannels.add(ChannelNumber.BBC_TWO);

        Log.d(TAG, getUrl());
    }

    public boolean isRequiresUpdate() {
        return requiresUpdate;
    }

    public void setRequiresUpdate(boolean requiresUpdate) {
        this.requiresUpdate = requiresUpdate;
    }

    public EnquiryResults getResults() {
        return results;
    }

    // construct URL to query API
    public String getUrl()
    {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("www.radiotimes.com")
                .appendPath("rt-service")
                .appendPath("schedule")
                .appendPath("get")
                .appendQueryParameter("startDate", RequestHelper.getDateStringHour())
                .appendQueryParameter("hours", "3")
                .appendQueryParameter("totalWidthUnits", "720")
                .appendQueryParameter("channels", RequestHelper.getChannelQueryParameter(userChannels));

        return builder.build().toString();
    }



    // url - get user selected channels

    // indicator channel selection has changed

    // setter/unsetter for a channel selection

    // getter and setter for results

/*
    public void setChannelSelection(int position, boolean chosen) {
        ChannelItem item = channels.get(position);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        item.setChosen(chosen);
        editor.putBoolean(item.getChannel().getIdAsString(), chosen);
        editor.apply();
    }


*/
}
