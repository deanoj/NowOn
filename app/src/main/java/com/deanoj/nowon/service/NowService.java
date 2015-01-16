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

import com.deanoj.nowon.data.dto.Channel;
import com.deanoj.nowon.util.ChannelEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by deano on 13/01/15.
 */
public class NowService extends Service {

    private final IBinder mBinder = new LocalBinder();

    private static final String TAG = "NowService";

    private static final String GITHUB_URL = "https://raw.githubusercontent.com/deanoj/deanoj.github.io/master/assets/tv.txt";

    private static final String TEST_URL = "http://192.168.20.20/default/tv.txt";

    private final ArrayList<ChannelItem> channels = new ArrayList<>();

    private SharedPreferences sharedPreferences;

    public class LocalBinder extends Binder {
        public NowService getService() {
            return NowService.this;
        }
    }

    public class ChannelItem {
        private ChannelEnum channel;
        private boolean chosen = false;

        public ChannelItem(ChannelEnum channel, boolean chosen) {
            this.channel = channel;
            this.chosen = chosen;
        }
        public ChannelEnum getChannel() {
            return channel;
        }
        public void setChannel(ChannelEnum channel) {
            this.channel = channel;
        }
        public boolean isChosen() {
            return this.chosen;
        }
        public void setChosen(boolean chosen) {
            this.chosen = chosen;
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

        sharedPreferences = getApplicationContext().getSharedPreferences(
                "channelSelection", Context.MODE_PRIVATE
        );

        for (ChannelEnum channel : ChannelEnum.values()) {
            channels.add(new ChannelItem(channel, isChannelSelected(channel)));
        }
    }

    public void onDestroy() {

    }

    public ArrayList<ChannelItem> getChannels() {
        return channels;
    }

    private boolean isChannelSelected(ChannelEnum channel) {

        return sharedPreferences.getBoolean(String.valueOf(channel.getId()), false);
    }

    public void setChannelSelection(int position, boolean chosen) {
        ChannelItem item = channels.get(position);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        item.setChosen(chosen);
        editor.putBoolean(item.getChannel().getIdAsString(), chosen);
        editor.apply();
    }

    public String getUrl()
    {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("www.radiotimes.com")
                .appendPath("rt-service")
                .appendPath("schedule")
                .appendPath("get")
                .appendQueryParameter("startDate", "12-01-2015 22:00:00")
                .appendQueryParameter("hours", "3")
                .appendQueryParameter("totalWidthUnits", "720")
                .appendQueryParameter("channels",getSelectedChannels());

        return builder.build().toString();
    }

    private String getSelectedChannels() {
        List<Long> selected = new ArrayList<Long>();

        for (ChannelItem channel : channels) {
            selected.add(channel.getChannel().getId());
        }
        return TextUtils.join(",", selected.toArray());
    }

}
