package com.displayfort.app.model;

/**
 * Created by pc on 16/10/2018 18:00.
 * DisplayFortSoftware
 */
public class MediaDao {
    public String foldername = "FOLDER";
    public int filecount = 0;
    public boolean isSelect = false;

    public MediaDao() {
    }

    public MediaDao(String foldername) {
        this.foldername = foldername;
    }
}
