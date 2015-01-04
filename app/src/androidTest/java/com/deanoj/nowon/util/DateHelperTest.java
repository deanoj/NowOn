package com.deanoj.nowon.util;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Calendar;

/**
 * Created by deano on 03/01/15.
 */
public class DateHelperTest extends AndroidTestCase {

    public void testToDateString()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, 0, 4, 3, 0, 0);
        Assert.assertEquals("04-01-2015 03:00:00", DateHelper.toDateString(calendar.getTime()));
    }
}
