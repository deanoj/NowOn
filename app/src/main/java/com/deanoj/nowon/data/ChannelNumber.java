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
    SKY_1 (248, "Sky 1"),
    SKY_ATLANTIC (2685, "Sky Atlantic"),
    SKY_LIVING (197, "Sky Living"),
    PICK (1963, "Pick"),
    DAVE (2050, "Dave"),

    REALLY(1882, "Really"),
    YESTERDAY(801, "Yesterday"),
    DRAMA(5097, "Drama"),
    WATCH(2115, "Watch"),
    GOLD(288, "GOLD"),
    SYFY(2122, "Syfy"),
    MOVIES4MEN(2058, "Movies4Men"),
    MOVIE_MIX(5072, "Movie Mix"),
    TRUE_ENTERTAINMENT(2603, "True Entertainment"),
    FIVE_STAR(2062, "5*"),
    FIVE_USA(2008, "5USA"),
    COMEDY_CENTRAL(1061, "Comedy Central"),

    COMEDY_CENTRAL_EXTRA(1201, "Comedy Central Extra"),
    UNIVERSAL_CHANNEL(180, "Universal Channel"),
    SKY_SPORTS_1(262, "Sky Sports 1"),
    SKY_SPORTS_2(264, "Sky Sports 2"),
    SKY_SPORTS_3(265, "Sky Sports 3"),
    SKY_SPORTS_4(2200, "Sky Sports 4"),
    SKY_SPORTS_5(11693, "Sky Sports 5"),
    ALIBI(292, "Alibi"),
    FOX(1461, "FOX"),
    SKY_ARTS_1(40, "Sky Arts 1"),
    SKY_ARTS_2(2122, "Sky Arts 2"),
    SKY_2(922, "Sky 2"),
    SKY_LIVINGIT(2189, "Sky Livingit"),
    DISCOVERY_CHANNEL(147, "Discovery Channel"),
    HISTORY(182, "History"),
    QUEST(2179, "Quest"),
    CBEEBIES(483, "CBeebies"),
    CBBC(482, "CBBC"),
    CITV(1981, "CITV"),
    EDEN(1601, "Eden"),
    NATIONAL_GEOGRAPHIC_CHANNEL(213, "National Geographic Channel"),
    HOME(2134, "Home"),
    FOOD_NETWORK(2185, "Food Network"),
    REALITY_TV(11719, "Reality TV"),
    ITV_ENCORE_PLUS_ONE(11744, "ITV Encore +1");

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

    public static String[] getChannelTitlesAsArray() {
        String[] titles = new String[ChannelNumber.values().length];
        for (int i = 0; i < ChannelNumber.values().length; i++) {
            titles[i] = ChannelNumber.values()[i].getTitle();
        }
        return titles;
    }

    public static String[] getChannelValuesAsArray() {
        String[] values = new String[ChannelNumber.values().length];
        for (int i = 0; i < ChannelNumber.values().length; i++) {
            values[i] = ChannelNumber.values()[i].getIdAsString();
        }
        return values;
    }

    public static String[] getDefaultChannelValuesAsArray() {
        String[] channels = new String[4];

        channels[0] = BBC_ONE.getIdAsString();
        channels[1] = BBC_TWO.getIdAsString();
        channels[2] = ITV.getIdAsString();
        channels[3] = CHANNEL_4.getIdAsString();

        return channels;
    }
}
