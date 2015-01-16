package com.deanoj.nowon.util;

/**
 * Created by deano on 09/01/15.
 */
public enum ChannelEnum {

    BBC_ONE (94, "BBC One"),
    BBC_TWO (105, "BBC Two"),
    ITV (26, "ITV"),
    CHANNEL_4 (132, "Channel 4");

    private final long id;

    private final String title;

    ChannelEnum(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getIdAsString() {
        return String.valueOf(this.id);
    }
}
