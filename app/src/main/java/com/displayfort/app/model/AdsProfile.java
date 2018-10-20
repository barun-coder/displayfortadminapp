package com.displayfort.app.model;

/**
 * Created by pc on 16/10/2018 18:00.
 * DisplayFortSoftware
 */
public class AdsProfile {
    public String name = "";
    public String id = "";
    public String unqueId = "";
    public String ExpiryDate = "";
    public String imageUrl = "";
    public int status = 0;
    public boolean isSelected = false;

    public AdsProfile() {
    }

    public AdsProfile(boolean isselected) {
        this.isSelected = isselected;
    }
}
