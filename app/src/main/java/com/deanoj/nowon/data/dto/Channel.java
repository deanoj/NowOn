package com.deanoj.nowon.data.dto;

import java.util.List;

/**
 * Created by deano on 01/01/15.
 */
public class Channel {

    private String displayName;

    private List<TvListing> tvListings;

    private int id;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<TvListing> getTvListings() {
        return tvListings;
    }

    public void setTvListings(List<TvListing> tvListings) {
        this.tvListings = tvListings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
