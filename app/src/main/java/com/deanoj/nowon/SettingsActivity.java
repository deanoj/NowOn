package com.deanoj.nowon;

import android.os.Bundle;
import android.preference.MultiSelectListPreference;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;
import com.deanoj.nowon.data.ChannelNumber;
import com.deanoj.nowon.util.ResponseParser;


public class SettingsActivity extends ActionBarActivity {

    private static final String TAG = "SettingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getFragmentManager().beginTransaction()
                .replace(R.id.preference_container, new PrefsFragment())
                .commit();

    }

    public static class PrefsFragment extends PreferenceFragment {

        ResponseParser parser;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);
            MultiSelectListPreference listPreference = (MultiSelectListPreference) getPreferenceManager()
                    .findPreference(getString(R.string.id_user_channel_list));


            listPreference.setEntries(ChannelNumber.getChannelTitlesAsArray());
            listPreference.setEntryValues(ChannelNumber.getChannelValuesAsArray());

            listPreference.setDefaultValue(ChannelNumber.getDefaultChannelValuesAsArray());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
