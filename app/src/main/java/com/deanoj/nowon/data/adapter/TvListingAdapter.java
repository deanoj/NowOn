package com.deanoj.nowon.data.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.deanoj.nowon.R;
import com.deanoj.nowon.data.results.TvListing;
import com.deanoj.nowon.util.DisplayHelper;

import java.util.List;

/**
 * Created by deano on 03/01/15.
 */
public class TvListingAdapter extends ArrayAdapter<TvListing> {

    private Context context;

    public TvListingAdapter(Context context, int resource, int textViewResourceId, List<TvListing> objects)
    {
        super(context, resource, textViewResourceId, objects);

        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listing_row, null);
        }

        TvListing listing = getItem(position);

        if (listing != null) {
            TextView title = (TextView) view
                    .findViewById(R.id.title);

            TextView description = (TextView) view
                    .findViewById(R.id.description);

            TextView timingView = (TextView) view
                    .findViewById(R.id.start_time);

            if (title != null) {
                title.setText(Html.fromHtml(listing.getTitle().toString()));
            }

            if (description != null) {
                if (listing.getDescription() == null || "null".equals(listing.getDescription())) {
                    description.setText("No description available");
                }
                description.setText(Html.fromHtml(listing.getDescription()));
            }

            if (timingView != null) {
                timingView.setText(DisplayHelper.getFormattedShowTime(
                    listing.getStartTime(),
                    listing.getEndTime())
                );
            }

        }

        return view;
    }

}
