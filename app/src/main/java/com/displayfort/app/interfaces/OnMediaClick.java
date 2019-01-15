package com.displayfort.app.interfaces;

import com.displayfort.app.model.MediaDao;

/**
 * Created by pc on 15/01/2019 15:17.
 * DisplayFortSoftware
 */
public interface OnMediaClick {
    void onMediaClick(MediaDao mediaDao);

    void onMediaLongClick(MediaDao mediaDao);
}
