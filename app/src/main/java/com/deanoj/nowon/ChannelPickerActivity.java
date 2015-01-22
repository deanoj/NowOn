package com.deanoj.nowon;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.deanoj.nowon.data.ChannelNumber;

import java.util.ArrayList;
import java.util.List;


public class ChannelPickerActivity extends ActionBarActivity {

    private static final String TAG = "ChannelPickerActivity";

    private ListView listView;

    private List<ChannelNumber> allChannels = new ArrayList<ChannelNumber>();

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_picker);

        listView = (ListView) findViewById(R.id.channel_picker_list_view);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        for (ChannelNumber channel : ChannelNumber.values()) {
            allChannels.add(channel);
        }
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
