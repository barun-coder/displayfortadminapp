package com.displayfort.app.screen;

/**
 * Created by pc on 17/10/2018 18:17.
 * DisplayFortSoftware
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.displayfort.app.R;
import com.displayfort.app.base.BaseActivity;
import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;

public class ScreenDetailActivity extends BaseActivity {

    private ScreenDetailViewHolder screenDetailViewHolder;
    private ProfileContentViewHolder profileContentViewHolder;
    private ProfileHeadViewHolder profileHeadViewHolder;
    private ProfileZoomViewHolder profileZoomViewHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_detail_layout);
        screenDetailViewHolder = new ScreenDetailViewHolder(findViewById(R.id.container_Ll), this);
        setHeaderToolbar();
        loadViewForCode();
        setScroolView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.general_rl: {
                Intent intent = new Intent(this, UpdateGeneralActivity.class);
                intent.putExtra("SCREEN", "Mega Wall");
                startActivityWithAnim(intent);
            }
            break;
            case R.id.assign_btn: {
                Intent intent = new Intent(this, AssignAdsScreenActivity.class);
//                intent.putExtra("SCREEN", "Mega Wall");
                startActivityWithAnim(intent);
            }
            break;
            case R.id.link_rl:
                break;
            case R.id.location_rl:
                break;
        }
        super.onClick(v);
    }

    private void setScroolView() {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth,(int) (0.8F * (mScreenWidth)));
        screenDetailViewHolder.mScrollView.setHeaderLayoutParams(localObject);
    }

    private void setHeaderToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.argb(30, 0, 0, 0));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        SetToolBar(getIntent().getExtras().getString("SCREEN", "Screen Detail"));
    }


    private void loadViewForCode() {
        View headView = LayoutInflater.from(this).inflate(R.layout.profile_head_view, null, false);
        View zoomView = LayoutInflater.from(this).inflate(R.layout.profile_zoom_view, null, false);
        View contentView = LayoutInflater.from(this).inflate(R.layout.profile_content_view, null, false);
        screenDetailViewHolder.mScrollView.setHeaderView(headView);
        screenDetailViewHolder.mScrollView.setZoomView(zoomView);
        screenDetailViewHolder.mScrollView.setScrollContentView(contentView);
//        screenDetailViewHolder.mScrollView.setHeaderViewSize(LinearLayout.LayoutParams.MATCH_PARENT,2200);
        profileContentViewHolder = new ProfileContentViewHolder(contentView, this);
        profileHeadViewHolder = new ProfileHeadViewHolder(headView, this);
        profileZoomViewHolder = new ProfileZoomViewHolder(zoomView, this);
    }

    public class ScreenDetailViewHolder {
        public PullToZoomScrollViewEx mScrollView;

        public ScreenDetailViewHolder(View view, View.OnClickListener listener) {
            mScrollView = (PullToZoomScrollViewEx) view.findViewById(R.id.scroll_view);
        }
    }


    public class ProfileContentViewHolder {
        public RelativeLayout mGeneralRl;
        public TextView mScreenNameTv;
        public TextView mVpnTv;
        public TextView mUniqueIdTv;
        public TextView mDataCardTv;
        public TextView mTagsTv;
        public TextView mExpiryDateTv;
        public RelativeLayout mLinkRl;
        public TextView mPartnersTv;
        public TextView mAgeGroupTv;
        public TextView mScreenTv;
        public TextView mResolutionTv;
        public RelativeLayout mLocationRl;
        public ImageView mLocationIv;


        public ProfileContentViewHolder(View view, View.OnClickListener listener) {
            mGeneralRl = (RelativeLayout) view.findViewById(R.id.general_rl);
            mScreenNameTv = (TextView) view.findViewById(R.id.screen_name_tv);
            mVpnTv = (TextView) view.findViewById(R.id.vpn_tv);
            mUniqueIdTv = (TextView) view.findViewById(R.id.unique_id_tv);
            mDataCardTv = (TextView) view.findViewById(R.id.data_card_tv);
            mTagsTv = (TextView) view.findViewById(R.id.tags_tv);
            mExpiryDateTv = (TextView) view.findViewById(R.id.expiry_date_tv);

            mGeneralRl.setOnClickListener(listener);
            mLinkRl = (RelativeLayout) view.findViewById(R.id.link_rl);
            mPartnersTv = (TextView) view.findViewById(R.id.partners_tv);
            mAgeGroupTv = (TextView) view.findViewById(R.id.age_group_tv);
            mScreenTv = (TextView) view.findViewById(R.id.screen_tv);
            mResolutionTv = (TextView) view.findViewById(R.id.resolution_tv);

            mLinkRl.setOnClickListener(listener);
            mLocationRl = (RelativeLayout) view.findViewById(R.id.location_rl);
            mLocationIv = (ImageView) view.findViewById(R.id.location_iv);

            mLocationRl.setOnClickListener(listener);

        }
    }

    public class ProfileHeadViewHolder {
        public Button mEnableBtn;
        public Button mAssignBtn;

        public ProfileHeadViewHolder(View view, View.OnClickListener listener) {
            mEnableBtn = (Button) view.findViewById(R.id.enable_btn);
            mAssignBtn = (Button) view.findViewById(R.id.assign_btn);

            mEnableBtn.setOnClickListener(listener);
            mAssignBtn.setOnClickListener(listener);
        }
    }

    public class ProfileZoomViewHolder {
        public ImageView mZoomImageIv;

        public ProfileZoomViewHolder(View view, View.OnClickListener listener) {
            mZoomImageIv = (ImageView) view.findViewById(R.id.zoom_image_iv);
            mZoomImageIv.setOnClickListener(listener);
        }
    }
}