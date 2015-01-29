package com.deanoj.nowon.util;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by deano on 03/01/15.
 */
public class DisplayHelperTest extends AndroidTestCase {

    public void testToDateString()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, 0, 4, 3, 0, 0);
        Assert.assertEquals("04-01-2015 03:00:00", DisplayHelper.toDateString(calendar.getTime()));
    }

    public void testParseDateString() throws ParseException
    {
        String dateString = "2015-01-04 01:40:00Z";
        Date result = DisplayHelper.parseDateString(dateString);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(result.getTime());

        Assert.assertEquals(2015, calendar.get(Calendar.YEAR));
        Assert.assertEquals(Calendar.JANUARY, calendar.get(Calendar.MONTH));
        Assert.assertEquals(04, calendar.get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(01, calendar.get(Calendar.HOUR));
        Assert.assertEquals(40, calendar.get(Calendar.MINUTE));
    }

    public void testCleanHtml() {

        Assert.assertEquals("David Starkey's Magna Carta", DisplayHelper.cleanHtml("David Starkey&#39;s Magna Carta"));
    }
}
