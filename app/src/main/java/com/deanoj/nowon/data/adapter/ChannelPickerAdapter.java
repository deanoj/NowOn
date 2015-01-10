package com.deanoj.nowon.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.deanoj.nowon.util.ChannelEnum;
import com.deanoj.nowon.R;

import java.util.List;

/**
 * Created by deano on 09/01/15.
 */
public class ChannelPickerAdapter extends ArrayAdapter<ChannelEnum> {

    private Context context;

    private static class ViewHolder {
        TextView channelNumber;
        TextView title;
    }

    public ChannelPickerAdapter(Context context, int resource, int textViewResourceId, List<ChannelEnum> objects) {
        super(context, resource, textViewResourceId, objects);

        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ChannelEnum channel = getItem(position);

        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_channel_picker, parent, false);
            viewHolder.channelNumber = (TextView) view.findViewById(R.id.channel_picker_number);
            viewHolder.title = (TextView) view.findViewById(R.id.channel_picker_title);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.title.setText(channel.getTitle());
        viewHolder.channelNumber.setText(String.valueOf(channel.getId()));

        return view;
    }
}