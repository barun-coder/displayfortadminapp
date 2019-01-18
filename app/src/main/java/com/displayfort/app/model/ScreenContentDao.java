package com.displayfort.app.model;

/**
 * Created by pc on 16/10/2018 18:00.
 * DisplayFortSoftware
 */
public class ScreenContentDao {
    public String name = "";
    public String title = "";

    public ScreenContentDao() {
    }

    public ScreenContentDao(String title, String name) {
        this.name = name;
        this.title = title;
    }
}
