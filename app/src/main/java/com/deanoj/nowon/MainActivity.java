package com.deanoj.nowon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.deanoj.nowon.data.NetworkSingleton;
import com.deanoj.nowon.data.ResponseParser;
import com.deanoj.nowon.data.dto.Channel;

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

    private final Date startDate = new Date();

    private int[] channelNumbers = {94, 105, 26, 2203, 132};

    private int hours = 3;

    private int totalWidthUnits = 720;


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

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, buildUrl(), null, new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.v(TAG, response.toString());
                        pd.dismiss();
                        parseResponse(response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.e(TAG, "error");
                        pd.dismiss();
                    }
                });

        NetworkSingleton.getInstance(this).addToRequestQueue(jsObjRequest);
        // MOCK resposne
        //parseResponse(Faker.RT_RESPONSE_JSON);
        Log.v(TAG, buildUrl());
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

    private String buildUrl() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("www.radiotimes.com")
                .appendPath("rt-service")
                .appendPath("schedule")
                .appendPath("get")
                .appendQueryParameter("startDate", "12-01-2015 22:00:00")
                .appendQueryParameter("hours", "3")
                .appendQueryParameter("totalWidthUnits", "720")
                .appendQueryParameter("channels","94,105,26,2203,132");

        //return builder.build().toString();
        return TEST_URL;
    }
}
