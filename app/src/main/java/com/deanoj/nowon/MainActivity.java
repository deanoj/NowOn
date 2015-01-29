package com.deanoj.nowon;

import android.app.DialogFragment;
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
import com.deanoj.nowon.data.ChannelNumber;
import com.deanoj.nowon.data.adapter.ChannelAdapter;
import com.deanoj.nowon.data.results.Channel;
import com.deanoj.nowon.net.NetworkSingleton;
import com.deanoj.nowon.ui.TimePickerFragment;
import com.deanoj.nowon.ui.DatePickerFragment;
import com.deanoj.nowon.util.RequestHelper;
import com.deanoj.nowon.util.ResponseParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity implements TimePickerFragment.MyTimePickerListener,
DatePickerFragment.MyDatePickerListener {

    private static final String TAG = "MainActivity";
    private static final String GITHUB_URL = "https://raw.githubusercontent.com/deanoj/deanoj.github.io/master/assets/tv.txt";
    private static final String TEST_URL = "http://192.168.20.20/default/tv.txt";

    private ListView listView;

    private ChannelAdapter adapter;

    private List<Channel> channels = new ArrayList<>();

    private ProgressDialog progress;

    private ResponseParser parser;

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
        super.onStop();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "oncreate");

        parser = ResponseParser.getInstance();

        if (parser.getResults().getChannels().size() == 0) {
            doRequest();
        }

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

        adapter = new ChannelAdapter(this,
                android.R.layout.simple_list_item_2, android.R.id.text1,
                parser.getResults().getChannels());

        listView.setAdapter(adapter);

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
            case R.id.action_about:
                showAbout();
                return true;
            case R.id.action_settings:
                showSettings();
                return true;
            case R.id.action_time:
                showTimePickerDialog();
                return true;
            case R.id.action_date:
                showDatePickerDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showTimePickerDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    private void showSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }


    private void showChannelPicker() {
        Intent intent = new Intent(this, ChannelPickerActivity.class);
        startActivity(intent);
    }

    private void showAbout() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }


    private void parseResponse(String response) {
        Log.d(TAG, "parsing response");
        try {
            parser.parseResponse(new JSONObject(response));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter.notifyDataSetChanged();
    }

    private void doRequest() {
        String url = getUrl();
        Log.d(TAG, "Making request to " + url);

        progress = new ProgressDialog(this);
        progress.setMessage("Loading...");
        progress.show();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.v(TAG, response.toString());
                        progress.dismiss();
                        parseResponse(response.toString());

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

    // construct URL to query API
    public String getUrl()
    {
        Uri.Builder builder = new Uri.Builder();
        String startDate = RequestHelper.getDateStringHour(parser.getTime());
        Log.d(TAG, "start date: " + startDate);
        builder.scheme("http")
                .authority("www.radiotimes.com")
                .appendPath("rt-service")
                .appendPath("schedule")
                .appendPath("get")
                .appendQueryParameter("startDate", startDate)
                .appendQueryParameter("hours", "3")
                .appendQueryParameter("totalWidthUnits", "720")
                .appendQueryParameter("channels",
                        RequestHelper.getChannelQueryParameter(Arrays.asList(ChannelNumber.values())));

        return builder.build().toString();
    }

    public void updateResults() {
        parser.getResults().clearAll();
        doRequest();
    }

}
