package com.deanoj.nowon.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.deanoj.nowon.data.dto.Channel;

import java.text.NumberFormat;
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
            view = inflater.inflate(android.R.layout.simple_list_item_2, null);
        }

        Channel channel = getItem(position);

        if (channel != null) {
            TextView displayNameView = (TextView) view
                    .findViewById(android.R.id.text1);

            TextView nowOnView = (TextView) view
                    .findViewById(android.R.id.text2);

            if (displayNameView != null) {
                displayNameView.setText(channel.getDisplayName());
            }
            if (nowOnView != null) {
                nowOnView.setText(channel.getTvListings().get(0).getTitle());
            }
        }

        return view;
    }

}
