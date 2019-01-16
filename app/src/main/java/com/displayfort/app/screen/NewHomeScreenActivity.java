package com.displayfort.app.screen;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.displayfort.app.R;
import com.displayfort.app.base.BaseActivity;
import com.displayfort.app.base.BaseAnimation;
import com.displayfort.app.base.BaseFragment;
import com.displayfort.app.base.Constant;
import com.displayfort.app.newScreen.AdsProfileListActivity;
import com.displayfort.app.newScreen.MediaListActivity;
import com.displayfort.app.newScreen.PartnerListActivity;
import com.displayfort.app.newScreen.ScheduleListActivity;
import com.displayfort.app.newScreen.ScreenListActivity;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.util.ViewAnimator;


public class NewHomeScreenActivity extends BaseActivity implements View.OnClickListener {

    private Context context;
    private NewHomeScreenViewHolder screenViewHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_home_screen_layout);
        context = this;
        screenViewHolder = new NewHomeScreenViewHolder(findViewById(R.id.container_rl), this);

    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, NewHomeScreenActivity.class);
        return intent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_layout_ll:
                hideLayout(v);
                break;
            case R.id.left_iv:
                break;
            case R.id.right_iv:
                break;
            case R.id.top_iv:
                break;
            case R.id.bottom_iv:
                break;
            case R.id.menu_hamburger_iv:
                if (screenViewHolder.mMenuLayoutLl.getVisibility() == View.VISIBLE) {
                    HideWithAnimation();
                } else {
                    ShowWithAnimation();

                }
                break;
            case R.id.screen_ll:
                HideWithAnimation(Constant.SCREEN);
                break;
            case R.id.ads_profile_ll:
                HideWithAnimation(Constant.ADS_PROFILE);
                break;
            case R.id.schedule_ll:
                HideWithAnimation(Constant.SCHEDULE);
                break;
            case R.id.unassign_ll:
                HideWithAnimation(Constant.PARTNERS);
                break;
            case R.id.media_ll:
                HideWithAnimation(Constant.MEDIA);
                break;
            case R.id.expiry_ll:
                HideWithAnimation(Constant.STATUS);
                break;
            default:
                break;
        }
    }

    public void hideLayout(View view) {
        HideWithAnimation();
    }

    private void ShowWithAnimation() {
        Animation bottomUp = AnimationUtils.loadAnimation(context,
                R.anim.bottom_up);
        screenViewHolder.mMenuLayoutLl.startAnimation(bottomUp);
        screenViewHolder.mMenuLayoutLl.setVisibility(View.VISIBLE);
    }

    private void HideWithAnimation() {
        Animation bottomDown = AnimationUtils.loadAnimation(context,
                R.anim.bottom_down);
        bottomDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                screenViewHolder.mMenuLayoutLl.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        screenViewHolder.mMenuLayoutLl.startAnimation(bottomDown);

    }

    private void HideWithAnimation(final String fragmentStr) {
        Animation bottomDown = AnimationUtils.loadAnimation(context,
                R.anim.bottom_down);
        bottomDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                screenViewHolder.mMenuLayoutLl.setVisibility(View.GONE);
                if (fragmentStr.equalsIgnoreCase(Constant.PARTNERS)) {
                    Intent intent = new Intent(context, PartnerListActivity.class);
                    intent.putExtra(Constant.ACTIVTY_TYPE, fragmentStr);
                    startActivityWithAnim(intent, BaseAnimation.EFFECT_TYPE.TAB_SLIDE_RIGHT);
                } else if (fragmentStr.equalsIgnoreCase(Constant.SCREEN)) {
                    Intent intent = new Intent(context, ScreenListActivity.class);
                    intent.putExtra(Constant.ACTIVTY_TYPE, fragmentStr);
                    startActivityWithAnim(intent, BaseAnimation.EFFECT_TYPE.TAB_SLIDE_RIGHT);
                } else if (fragmentStr.equalsIgnoreCase(Constant.ADS_PROFILE)) {
                    Intent intent = new Intent(context, AdsProfileListActivity.class);
                    intent.putExtra(Constant.ACTIVTY_TYPE, fragmentStr);
                    startActivityWithAnim(intent, BaseAnimation.EFFECT_TYPE.TAB_SLIDE_RIGHT);
                }else if (fragmentStr.equalsIgnoreCase(Constant.MEDIA)) {
                    Intent intent = new Intent(context, MediaListActivity.class);
                    intent.putExtra(Constant.ACTIVTY_TYPE, fragmentStr);
                    startActivityWithAnim(intent, BaseAnimation.EFFECT_TYPE.TAB_SLIDE_RIGHT);
                }else if (fragmentStr.equalsIgnoreCase(Constant.SCHEDULE)) {
                    Intent intent = new Intent(context, ScheduleListActivity.class);
                    intent.putExtra(Constant.ACTIVTY_TYPE, fragmentStr);
                    startActivityWithAnim(intent, BaseAnimation.EFFECT_TYPE.TAB_SLIDE_RIGHT);
                } else {
                    Intent intent = new Intent(context, SubActivityScreen.class);
                    intent.putExtra(Constant.ACTIVTY_TYPE, fragmentStr);
                    startActivityWithAnim(intent, BaseAnimation.EFFECT_TYPE.TAB_SLIDE_DOWN_TO_UP);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        screenViewHolder.mMenuLayoutLl.startAnimation(bottomDown);

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


    public class NewHomeScreenViewHolder {
        private ImageView mMenuHamburgerIv, mLeftIv, mRightIv, mTopIv, mBottomIv;
        public LinearLayout mMenuLayoutLl;
        public LinearLayout mScreenLl;
        public LinearLayout mAdsProfileLl;
        public LinearLayout mScheduleLl;
        public LinearLayout mUnassignLl;
        public LinearLayout mMediaLl;
        public LinearLayout mExpiryLl;

        public NewHomeScreenViewHolder(View view, View.OnClickListener listener) {
            mMenuHamburgerIv = (ImageView) view.findViewById(R.id.menu_hamburger_iv);
            mTopIv = (ImageView) view.findViewById(R.id.top_iv);
            mLeftIv = (ImageView) view.findViewById(R.id.left_iv);
            mBottomIv = (ImageView) view.findViewById(R.id.bottom_iv);
            mRightIv = (ImageView) view.findViewById(R.id.right_iv);

            mMenuLayoutLl = (LinearLayout) view.findViewById(R.id.menu_layout_ll);
            mScreenLl = (LinearLayout) view.findViewById(R.id.screen_ll);
            mAdsProfileLl = (LinearLayout) view.findViewById(R.id.ads_profile_ll);
            mScheduleLl = (LinearLayout) view.findViewById(R.id.schedule_ll);
            mUnassignLl = (LinearLayout) view.findViewById(R.id.unassign_ll);
            mMediaLl = (LinearLayout) view.findViewById(R.id.media_ll);
            mExpiryLl = (LinearLayout) view.findViewById(R.id.expiry_ll);

            mMenuHamburgerIv.setOnClickListener(listener);
            mTopIv.setOnClickListener(listener);
            mLeftIv.setOnClickListener(listener);
            mBottomIv.setOnClickListener(listener);
            mRightIv.setOnClickListener(listener);
            mMenuLayoutLl.setOnClickListener(listener);
            mMenuLayoutLl.setVisibility(View.GONE);

            mScreenLl.setOnClickListener(listener);
            mAdsProfileLl.setOnClickListener(listener);
            mScheduleLl.setOnClickListener(listener);
            mUnassignLl.setOnClickListener(listener);
            mMediaLl.setOnClickListener(listener);
            mExpiryLl.setOnClickListener(listener);
        }
    }

    @Override
    public void onBackPressed() {
        if (screenViewHolder.mMenuLayoutLl.getVisibility() == View.VISIBLE) {
            HideWithAnimation();
        } else {
            super.onBackPressed();
        }
    }
}