package com.deanoj.nowon;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.deanoj.nowon.data.adapter.ChannelPickerAdapter;
import com.deanoj.nowon.service.NowService;


public class ChannelPickerActivity extends ActionBarActivity {

    private static final String TAG = "ChannelPickerActivity";

    private ListView listView;

    private NowService mService;

    private boolean mBound = false;

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service)
        {
            NowService.LocalBinder binder = (NowService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;

            Log.d(TAG, "NowService connected");
            Log.d(TAG, mService.getResults().getChannels().toString());

            final ChannelPickerAdapter adapter = new ChannelPickerAdapter(getApplicationContext(),
                    android.R.layout.simple_list_item_checked,
                    mService.getResults().getChannels());


            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            mBound = false;
        }
    };

    @Override
    public void onStart() {
        super.onStart();

        Intent intent = new Intent(this, NowService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBound) {
            // do something
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_picker);

        listView = (ListView) findViewById(R.id.channel_picker_list_view);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView check = (CheckedTextView)view;
                Log.d(TAG, "item clicked "+position);
                //mService.setChannelSelection(position, check.isChecked());
            }
        });

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
