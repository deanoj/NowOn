package com.deanoj.nowon.data.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.deanoj.nowon.R;
import com.deanoj.nowon.data.dto.Channel;
import com.deanoj.nowon.util.DisplayHelper;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by deano on 03/01/15.
 */
public class ChannelAdapter extends ArrayAdapter<Channel> {

    private Context context;

    public ChannelAdapter(Context context, int resource, int textViewResourceId, List<Channel> objects)
    {
        super(context, resource, textViewResourceId, objects);

        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.channel_row, null);
        }

        Channel channel = getItem(position);

        if (channel != null) {
            TextView channelNameView = (TextView) view
                    .findViewById(R.id.channel);

            TextView titleView = (TextView) view
                    .findViewById(R.id.title);

            TextView timingView = (TextView) view
                    .findViewById(R.id.timing);

            if (channelNameView != null) {
                channelNameView.setText(channel.getDisplayName());
            }
            if (titleView != null && channel.getTvListings() != null) {
                String title = channel.getTvListings().get(0).getTitle();
                titleView.setText(Html.fromHtml(title).toString());
            }


            if (timingView != null) {

                timingView.setText(DisplayHelper.getFormattedShowTime(
                                channel.getTvListings().get(0).getStartTime(),
                                channel.getTvListings().get(0).getEndTime())
                );

            }
        }

        return view;
    }



}
