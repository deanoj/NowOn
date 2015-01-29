package com.deanoj.nowon.data;

/**
 * Created by deano on 09/01/15.
 */
public enum ChannelNumber {

    BBC_ONE (94, "BBC One"),
    BBC_TWO (105, "BBC Two"),
    ITV (26, "ITV"),
    CHANNEL_4 (132, "Channel 4"),
    CHANNEL_5 (134, "Channel 5"),
    BBC_THREE (45, "BBC Three"),
    BBC_FOUR (47, "BBC Four"),
    ITV2 (185, "ITV2"),
    ITV3 (1859, "ITV3"),
    ITV4 (1961, "ITV4"),

    ITVBE (11708, "ITV Be"),
    ITV_ENCORE (11680, "ITV Encore"),
    ITV_ENCORE_HD (11682, "ITV Encore HD"),
    E4 (158, "E4"),
    MORE4 (1959, "More4"),
    FOUR_SEVEN (2056, "4Seven"),
    CHALLENGE (11740, "Challenge"),
    FOUR_SEVEN_HD (11691, "4Seven HD"),
    FILM4 (160, "Film4"),
    SKY1 (248, "Sky 1"),
    SKY_ATLANTIC (2685, "Sky Atlantic"),
    SKY_LIVING (197, "Sky Living"),
    PICK (1963, "Pick"),
    DAVE (2050, "Dave");

    //11708,11680,11682,158,1959,2056,11740,11691,160,248,2685,197,1963,2050

    private final long id;

    private final String title;

    ChannelNumber(long id, String title) {
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
