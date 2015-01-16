package com.deanoj.nowon.data.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.deanoj.nowon.service.NowService;

import java.util.List;

/**
 * Created by deano on 09/01/15.
 */
public class ChannelPickerAdapter extends ArrayAdapter {

    private Context context;

    private static class ViewHolder {
        CheckedTextView title;
    }

    public ChannelPickerAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);

        this.context = context;
    }

}