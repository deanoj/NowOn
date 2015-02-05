package com.deanoj.nowon.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by deano on 31/01/15.
 */
public class UserChannels extends TreeSet {

    public UserChannels() {
        super(new ChannelComparator());
    }

    public static class ChannelComparator implements Comparator {

        @Override
        public int compare(Object lhs, Object rhs) {
            return ((ChannelNumber) lhs).ordinal() - ((ChannelNumber) rhs).ordinal();
        }
    }
}
