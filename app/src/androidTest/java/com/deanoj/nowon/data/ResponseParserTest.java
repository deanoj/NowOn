package com.deanoj.nowon.data;

import android.test.ActivityUnitTestCase;
import android.test.AndroidTestCase;

import com.deanoj.nowon.data.results.Channel;
import com.deanoj.nowon.util.ResponseParser;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by deano on 01/01/15.
 */
public class ResponseParserTest extends AndroidTestCase {

    ResponseParser parser;

    List<Channel> channels;

    @Override
    public void setUp() {
        ResponseParser parser = ResponseParser.getInstance();
        try {
            parser.parseResponse(new JSONObject(Faker.RT_RESPONSE_JSON));
            channels = parser.getResults().getChannels();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void testChannelListSize()
    {
        Assert.assertEquals(5, channels.size());
    }

    public void testChannelNames()
    {
        Assert.assertEquals("BBC One", channels.get(0).getDisplayName());
        Assert.assertEquals("BBC Two", channels.get(1).getDisplayName());
    }


}
