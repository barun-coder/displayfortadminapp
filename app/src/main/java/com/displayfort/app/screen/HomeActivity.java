package com.displayfort.app.screen;

/**
 * Created by pc on 16/10/2018 15:31.
 * DisplayFortSoftware
 */

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.displayfort.app.DFPrefrence;
import com.displayfort.app.R;
import com.displayfort.app.Utils.Dialogs;
import com.displayfort.app.base.BaseActivity;
import com.displayfort.app.base.BaseFragment;
import com.displayfort.app.base.Constant;
import com.displayfort.app.fragments.AdsFragment;
import com.displayfort.app.fragments.HomeFragment;
import com.displayfort.app.fragments.ScreenFragment;
import com.displayfort.app.fragments.SettingFragment;
import com.displayfort.app.widgets.NewViewAnimator;

import java.util.ArrayList;
import java.util.List;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;

import yalantis.com.sidemenu.util.ViewAnimator;


public class HomeActivity extends BaseActivity implements ViewAnimator.ViewAnimatorListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
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
        setContentView(R.layout.home_activity);
        context = this;
        homeFragment = HomeFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, homeFragment)
                .commit();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        setActionBar();
        createMenuList();
        viewAnimator = new NewViewAnimator<>(this, list, homeFragment, drawerLayout, this);
    }

    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(Constant.CLOSE, R.mipmap.remove);
        list.add(menuItem0);
        SlideMenuItem menuItem1 = new SlideMenuItem(Constant.HOME, R.mipmap.home);
        list.add(menuItem1);
        SlideMenuItem menuItem2 = new SlideMenuItem(Constant.SCREEN, R.mipmap.screens);
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem(Constant.ADS_PROFILE, R.mipmap.ad_profile);
        list.add(menuItem3);
        SlideMenuItem menuItem7 = new SlideMenuItem(Constant.LOGS, R.mipmap.logs);
        list.add(menuItem7);
        SlideMenuItem menuItem8 = new SlideMenuItem(Constant.REPORT, R.mipmap.report);
        list.add(menuItem8);
        SlideMenuItem menuItem9 = new SlideMenuItem(Constant.MEDIA, R.mipmap.media_library);
        list.add(menuItem9);
        SlideMenuItem menuItem4 = new SlideMenuItem(Constant.SETTING, R.mipmap.settings);
        list.add(menuItem4);
        SlideMenuItem menuItem5 = new SlideMenuItem(Constant.LOGOUT, R.mipmap.logout);
        list.add(menuItem5);


    }


    private void setActionBar() {
        titleTv = findViewById(R.id.toolbar_title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    private ScreenShotable replaceFragment(ScreenShotable screenShotable, int topPosition, BaseFragment homeFragment) {
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
        animator.start();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, homeFragment).commit();
        return homeFragment;
    }

    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        switch (slideMenuItem.getName()) {
            case Constant.CLOSE:
                return screenShotable;
            case Constant.HOME:
                titleTv.setText(slideMenuItem.getName());
                if (!currentFragment.equalsIgnoreCase(slideMenuItem.getName())) {
                    currentFragment = slideMenuItem.getName();
//                    currentFragment = slideMenuItem.getName();
                    return replaceFragment(screenShotable, position, HomeFragment.newInstance());
                }
                break;
            case Constant.SCREEN:
                titleTv.setText(slideMenuItem.getName());
                if (!currentFragment.equalsIgnoreCase(slideMenuItem.getName())) {
                    currentFragment = slideMenuItem.getName();
                    return replaceFragment(screenShotable, position, ScreenFragment.newInstance());
                }
                break;
            case Constant.ADS_PROFILE:
                titleTv.setText(slideMenuItem.getName());
                if (!currentFragment.equalsIgnoreCase(slideMenuItem.getName())) {
                    currentFragment = slideMenuItem.getName();
                    return replaceFragment(screenShotable, position, AdsFragment.newInstance());
                }
                break;
            case Constant.SETTING:
                titleTv.setText(slideMenuItem.getName());
                if (!currentFragment.equalsIgnoreCase(slideMenuItem.getName())) {
                    currentFragment = slideMenuItem.getName();
                    return replaceFragment(screenShotable, position, SettingFragment.newInstance());
                }
                break;
            case Constant.LOGS:
                titleTv.setText(slideMenuItem.getName());
                if (!currentFragment.equalsIgnoreCase(slideMenuItem.getName())) {
                    currentFragment = slideMenuItem.getName();
                    return replaceFragment(screenShotable, position, SettingFragment.newInstance());
                }
                break;
            case Constant.REPORT:
                titleTv.setText(slideMenuItem.getName());
                if (!currentFragment.equalsIgnoreCase(slideMenuItem.getName())) {
                    currentFragment = slideMenuItem.getName();
                    return replaceFragment(screenShotable, position, SettingFragment.newInstance());
                }
                break;
            case Constant.STATUS:
                titleTv.setText(slideMenuItem.getName());
                if (!currentFragment.equalsIgnoreCase(slideMenuItem.getName())) {
                    currentFragment = slideMenuItem.getName();
                    return replaceFragment(screenShotable, position, SettingFragment.newInstance());
                }
                break;
            case Constant.MEDIA:
                titleTv.setText(slideMenuItem.getName());
                if (!currentFragment.equalsIgnoreCase(slideMenuItem.getName())) {
                    currentFragment = slideMenuItem.getName();
                    return replaceFragment(screenShotable, position, SettingFragment.newInstance());
                }
                break;
            case Constant.LOGOUT:
                LogoutProcess(context);
                break;
            default:
                titleTv.setText(slideMenuItem.getName());
                if (!currentFragment.equalsIgnoreCase(slideMenuItem.getName())) {
                    currentFragment = slideMenuItem.getName();
                    return replaceFragment(screenShotable, position, HomeFragment.newInstance());
                }
        }
        return screenShotable;
    }

    public void LogoutProcess(final Context context) {
        Dialogs.showYesNolDialog(context, "Confirmation", "Are you sure you want to Logout", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Dialog) v.getTag()).dismiss();
                new DFPrefrence(context).setClearPrefrence();
                Intent intent = new Intent(context, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityWithAnim(intent);
            }
        });
    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);

    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();

    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }
}