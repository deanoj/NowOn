package com.deanoj.nowon;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by deano on 23/01/15.
 */
public class MainActivityTest extends ActivityUnitTestCase<MainActivity>
{

    private Intent mMainIntent;

    public MainActivityTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mMainIntent = new Intent(getInstrumentation().getTargetContext(),
                MainActivity.class);
    }

    /**
     * Tests the preconditions of this test fixture.
     */
    @MediumTest
    public void testPreconditions() {
        startActivity(mMainIntent, null, null);
        final ListView channelList = (ListView) getActivity().findViewById(R.id.channel_picker_list_view);

        assertNotNull("mMainIntent is null", mMainIntent);
    }

}
