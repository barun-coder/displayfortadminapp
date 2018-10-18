package com.displayfort.app;

import android.app.Application;
import android.content.Context;
import android.content.ServiceConnection;

/**
 * Created by Husain on 07-03-2016.
 */
public class DFApplication extends Application {
    public static Context context;
    private static DFApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        DFApplication.context = getApplicationContext();
//        Mint.initAndStartSession(getApplicationContext(), "91479eda");
    }


    public static synchronized DFApplication getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return DFApplication.context;
    }


    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    @Override
    public Context getBaseContext() {

        return super.getBaseContext();
    }

    @Override
    public void unbindService(ServiceConnection conn) {

        super.unbindService(conn);
    }
}
