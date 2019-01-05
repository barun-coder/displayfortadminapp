package com.displayfort.app.base;

import com.displayfort.app.R;
import com.displayfort.app.model.Friend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 16/10/2018 17:05.
 * DisplayFortSoftware
 */
public class Constant {

    public static final String ACTIVTY_TYPE = "Activity_type";
    public static final String CLOSE = "Close";
    public static final String HOME = "Home";
    public static final String ADS_PROFILE = "Ads";
    public static final String SCREEN = "Screens";
    public static final String SETTING = "Setting";
    public static final String LOGOUT = "Logout";
    public static final String LOGS = "Logs";
    public static final String REPORT = "Report";
    public static final String MEDIA = "Media";
    public static final String STATUS = "Status";
    public static final List<Friend> friends = new ArrayList<>();

    static {
        friends.add(new Friend(R.mipmap.home_screen, "SCREEN", R.color.colorPrimary, "Travelling", "Flights", "Books", "Painting", "Design"));
        friends.add(new Friend(R.mipmap.home_ads, "PROFILE", R.color.colorPrimary, "Sales", "Pets", "Skiing", "Hairstyles", "Сoffee"));
        friends.add(new Friend(R.mipmap.home_schedile, "SCHEDULE", R.color.colorPrimary, "Android", "Development", "Design", "Wearables", "Pets"));
        friends.add(new Friend(R.mipmap.home_unassigned, "UNASSIGN", R.color.colorPrimary, "Design", "Fitness", "Healthcare", "UI/UX", "Chatting"));
        friends.add(new Friend(R.mipmap.home_media, "MEDIA", R.color.colorPrimary, "Development", "Android", "Healthcare", "Sport", "Rock Music"));
        friends.add(new Friend(R.mipmap.home_expiry, "EXPIRY", R.color.colorPrimary, "Android", "IOS", "Application", "Development", "Company"));
    }
}
