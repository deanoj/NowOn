package com.deanoj.nowon.data.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.deanoj.nowon.R;
import com.deanoj.nowon.data.ChannelNumber;
import com.deanoj.nowon.service.NowService;

import java.util.List;

/**
 * Created by deano on 09/01/15.
 */
public class ChannelPickerAdapter extends ArrayAdapter<ChannelNumber> {

    private static final String TAG = "ChannelPickerAdapter";

    private Context context;

    private List<ChannelNumber> objects;

    private static class ViewHolder {
        CheckedTextView title;
    }

    public ChannelPickerAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);

        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        CheckedTextView view = (CheckedTextView) inflater.inflate(
                android.R.layout.simple_list_item_checked, parent, false);

        view.setText((CharSequence) objects.get(position).getTitle());
        view.setTextColor(Color.BLACK);

        return view;
    }
}