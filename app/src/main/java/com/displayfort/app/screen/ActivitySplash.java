package com.displayfort.app.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.displayfort.app.DFPrefrence;
import com.displayfort.app.R;
import com.displayfort.app.base.BaseActivity;


public class ActivitySplash extends BaseActivity implements View.OnClickListener {

    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;
        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(2 * 1000);

                    // After 5 seconds redirect to another intent
//                    new DFPrefrence(context).setIsLogin(true);
//                    new DFPrefrence(context).setLoginSessionKey("b908f2c58bafd1c362fcf266c1770c69");
                    if (new DFPrefrence(context).IsLogin()) {
                        startActivityWithAnim(new Intent(getBaseContext(), NewHomeScreenActivity.class));
                    } else {
                        startActivityWithAnim(new Intent(getBaseContext(), LoginActivity.class));
                    }

                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, ActivitySplash.class);
        return intent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }

}