package com.deanoj.nowon;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.deanoj.nowon.data.adapter.ChannelAdapter;
import com.deanoj.nowon.net.NetworkSingleton;
import com.deanoj.nowon.util.ResponseParser;
import com.deanoj.nowon.data.results.Channel;
import com.deanoj.nowon.service.NowService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";
    private static final String GITHUB_URL = "https://raw.githubusercontent.com/deanoj/deanoj.github.io/master/assets/tv.txt";
    private static final String TEST_URL = "http://192.168.20.20/default/tv.txt";

    private ListView listView;

    private ChannelAdapter adapter;

    private List<Channel> channels;

    private NowService mService;

    private boolean mBound = false;

    private ProgressDialog progress;

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service)
        {
            NowService.LocalBinder binder = (NowService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;

            Log.d(TAG, "NowService connected");
            Log.d(TAG, mService.getResults().getChannels().toString());

            if (mService.isRequiresUpdate()) {
                doRequest();
            }
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

        Log.d(TAG, "NowService connecting...");
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.v(TAG, "item clicked: " + position);

                Intent intent = new Intent(getApplicationContext(), ChannelActivity.class);
                intent.putExtra(ChannelActivity.CHANNEL_POSITION, position);
                startActivity(intent);
            }
        });

        progress = new ProgressDialog(this);
        progress.setMessage("Loading...");
        progress.show();

        Log.v(TAG, "complete");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_channel_picker:
                showChannelPicker();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void showChannelPicker() {
        Intent intent = new Intent(this, ChannelPickerActivity.class);
        startActivity(intent);
    }


    private void parseResponse(String response) {
        ResponseParser parser = ResponseParser.getInstance();
        try {
            parser.parseResponse(new JSONObject(response));
            channels = parser.getResults().getChannels();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        adapter = new ChannelAdapter(this,
                android.R.layout.simple_list_item_2, android.R.id.text1,
                channels);

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void doRequest() {
        Log.d(TAG, "Making request");

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, mService.getUrl(), null, new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.v(TAG, response.toString());
                        progress.dismiss();
                        parseResponse(response.toString());
                        mService.setRequiresUpdate(false);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.e(TAG, "error");
                        progress.dismiss();
                    }
                }
        );

        NetworkSingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }
}
