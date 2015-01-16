package com.deanoj.nowon.data.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.deanoj.nowon.service.NowService;
import com.deanoj.nowon.util.ChannelEnum;
import com.deanoj.nowon.R;

import java.util.List;

/**
 * Created by deano on 09/01/15.
 */
public class ChannelPickerAdapter extends ArrayAdapter<NowService.ChannelItem> {

    private Context context;

    private static class ViewHolder {
        CheckedTextView title;
    }

    public ChannelPickerAdapter(Context context, int resource, List<NowService.ChannelItem> objects) {
        super(context, resource, objects);

        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        NowService.ChannelItem item = getItem(position);

        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(android.R.layout.simple_list_item_checked, parent, false);
            viewHolder.title = (CheckedTextView) view.findViewById(android.R.id.text1);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.title.setText(item.getChannel().getTitle());
        ((ListView) parent).setItemChecked(position, item.isChosen());
        viewHolder.title.setTextColor(Color.BLACK);

        return view;
    }
}