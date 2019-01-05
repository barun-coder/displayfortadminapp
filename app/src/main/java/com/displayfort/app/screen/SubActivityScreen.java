package com.displayfort.app.screen;

/**
 * Created by pc on 16/10/2018 15:31.
 * DisplayFortSoftware
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.displayfort.app.R;
import com.displayfort.app.base.BaseActivity;
import com.displayfort.app.base.BaseFragment;
import com.displayfort.app.base.Constant;
import com.displayfort.app.fragments.AdsFragment;
import com.displayfort.app.fragments.HomeFragment;
import com.displayfort.app.fragments.LogsFragment;
import com.displayfort.app.fragments.MediaFragment;
import com.displayfort.app.fragments.ReportFragment;
import com.displayfort.app.fragments.ScreenFragment;
import com.displayfort.app.fragments.SettingFragment;
import com.displayfort.app.fragments.StatusFragment;
import com.displayfort.app.widgets.NewViewAnimator;

import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;


public class SubActivityScreen extends BaseActivity {

    private List<SlideMenuItem> list = new ArrayList<>();
    private HomeFragment homeFragment;
    private NewViewAnimator viewAnimator;
    private LinearLayout linearLayout;
    private String currentFragment = Constant.HOME;
    private TextView titleTv;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity_layout);
        context = this;
        setActionBar();
        onSwitch(getIntent().getStringExtra(Constant.ACTIVTY_TYPE));
    }


    private void setActionBar() {
        titleTv = findViewById(R.id.txtToolbarTitle);
    }


    private ScreenShotable replaceFragment(BaseFragment homeFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_overlay, homeFragment).commit();
        return homeFragment;
    }


    public void onSwitch(String slideMenuItem) {
        switch (slideMenuItem) {

            case Constant.HOME:
                titleTv.setText(slideMenuItem);
                replaceFragment(HomeFragment.newInstance());

                break;
            case Constant.SCREEN:
                titleTv.setText(slideMenuItem);
                replaceFragment(ScreenFragment.newInstance());

                break;
            case Constant.ADS_PROFILE:
                titleTv.setText(slideMenuItem);
                replaceFragment(AdsFragment.newInstance());

                break;
            case Constant.SETTING:
                titleTv.setText(slideMenuItem);
                if (!currentFragment.equalsIgnoreCase(slideMenuItem)) {
                    currentFragment = slideMenuItem;
                    replaceFragment(SettingFragment.newInstance());
                }
                break;
            case Constant.LOGS:
                titleTv.setText(slideMenuItem);
                if (!currentFragment.equalsIgnoreCase(slideMenuItem)) {
                    currentFragment = slideMenuItem;
                    replaceFragment(LogsFragment.newInstance());
                }
                break;
            case Constant.REPORT:
                titleTv.setText(slideMenuItem);
                if (!currentFragment.equalsIgnoreCase(slideMenuItem)) {
                    currentFragment = slideMenuItem;
                    replaceFragment(ReportFragment.newInstance());
                }
                break;
            case Constant.STATUS:
                titleTv.setText(slideMenuItem);
                if (!currentFragment.equalsIgnoreCase(slideMenuItem)) {
                    currentFragment = slideMenuItem;
                    replaceFragment(StatusFragment.newInstance());
                }
                break;
            case Constant.MEDIA:
                titleTv.setText(slideMenuItem);
                if (!currentFragment.equalsIgnoreCase(slideMenuItem)) {
                    currentFragment = slideMenuItem;
                    replaceFragment(MediaFragment.newInstance());
                }
                break;

        }

    }


}