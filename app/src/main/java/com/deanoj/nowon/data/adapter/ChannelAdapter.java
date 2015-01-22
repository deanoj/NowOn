package com.deanoj.nowon.data.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.deanoj.nowon.R;
import com.deanoj.nowon.data.results.Channel;
import com.deanoj.nowon.util.DisplayHelper;

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
                //SpannableString styledTitle = new SpannableString(Html.fromHtml(title).toString());
                //styledTitle.setSpan(new ForegroundColorSpan(Color.RED), 0, 5, 0);
                //styledTitle.setSpan(new RelativeSizeSpan(1.5f), 3, title.length() > 10 ? 10 : title.length(), 0);
                titleView.setText(title);
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
