package com.deanoj.nowon;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.deanoj.nowon.data.ResponseParser;
import com.deanoj.nowon.data.adapter.TvListingAdapter;
import com.deanoj.nowon.data.dto.Channel;


public class ChannelActivity extends ActionBarActivity {

    public static final String CHANNEL_POSITION = "position";

    private ListView listView;

    private TvListingAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        int position = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = (int) extras.get(CHANNEL_POSITION);
        }

        ResponseParser parser = ResponseParser.getInstance();
        Channel channel = parser.getResults().getChannels().get(position);

        setTitle(channel.getDisplayName());

        listView = (ListView) findViewById(R.id.listViewTvListings);
        adapter = new TvListingAdapter(this,
                android.R.layout.simple_list_item_2, android.R.id.text1,
                channel.getTvListings());

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_channel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
