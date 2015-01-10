package com.deanoj.nowon;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.deanoj.nowon.data.adapter.ChannelPickerAdapter;
import com.deanoj.nowon.util.ChannelEnum;

import java.util.Arrays;


public class ChannelPickerActivity extends ActionBarActivity {

    private static final String TAG = "ChannelPickerActivity";

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_picker);

        listView = (ListView) findViewById(R.id.channel_picker_list_view);


        final ChannelPickerAdapter adapter = new ChannelPickerAdapter(this,
                R.layout.item_channel_picker, android.R.id.text1,
                Arrays.asList(ChannelEnum.values()));

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_channel_picker, menu);
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
