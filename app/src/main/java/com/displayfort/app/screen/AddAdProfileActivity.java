package com.displayfort.app.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.displayfort.app.R;
import com.displayfort.app.adapter.AdsProfileItemListAdapter;
import com.displayfort.app.base.BaseActivity;
import com.displayfort.app.base.BaseAnimation;
import com.displayfort.app.model.AdsProfile;
import com.displayfort.app.model.AdsProfile;
import com.displayfort.app.widgets.RecyclerItemClickListener;

import java.util.ArrayList;

public class AddAdProfileActivity extends BaseActivity implements View.OnClickListener {

    private Context context;
    private AddAdProfileViewHolder addAdProfileViewHolder;
    private AdsProfileItemListAdapter adsProfileListAdapter;
    private ArrayList<AdsProfile> profileItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ad_profile_layout);
        context = this;
        SetToolBar("Ad Profile");
        addAdProfileViewHolder = new AddAdProfileViewHolder(findViewById(R.id.container_ll), this);
        init();
        setAdapter();
    }

    private void init() {
        addAdProfileViewHolder.mToggleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    Log.d("alarmCheck", "ALARM SET TO TRUE");
                } else {
                    Log.d("alarmCheck", "ALARM SET TO FALSE");
                }
            }
        });
    }

    private void setAdapter() {
        addAdProfileViewHolder.mRecyclerViewRv.setLayoutManager(new LinearLayoutManager(context));
        profileItems = getList();
        adsProfileListAdapter = new AdsProfileItemListAdapter(context, profileItems);
        addAdProfileViewHolder.mRecyclerViewRv.setAdapter(adsProfileListAdapter);
        addAdProfileViewHolder.mRecyclerViewRv.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
//                        Intent intent = new Intent(context, ScreenDetailActivity.class);
//                        startActivityWithAnim(intent, BaseAnimation.EFFECT_TYPE.TAB_SLIDE_RIGHT);
                    }
                }));

    }

    private ArrayList<AdsProfile> getList() {
        ArrayList<AdsProfile> AdsProfiles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            AdsProfiles.add(new AdsProfile());
        }
        return AdsProfiles;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_ads:
                Intent intent = new Intent(context, AddAdProfileMediaContentActivity.class);
                startActivityWithAnim(intent, BaseAnimation.EFFECT_TYPE.TAB_SLIDE_RIGHT);
                break;
            default:
                super.onClick(v);
        }

    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, AddAdProfileActivity.class);
        return intent;
    }


    public class AddAdProfileViewHolder {
        public EditText mProfileEt;
        public RecyclerView mRecyclerViewRv;
        public CheckBox mToggleBtn;
        public RelativeLayout mAddMediaCntRL;
        public TextView mAdsCountTv;
        public Button mAddMediaBtn;

        public AddAdProfileViewHolder(View view, View.OnClickListener listener) {
            mProfileEt = view.findViewById(R.id.profile_et);
            mToggleBtn = view.findViewById(R.id.chkState);
            mAddMediaCntRL = view.findViewById(R.id.head_ads);
            mAdsCountTv = view.findViewById(R.id.adp_count_tv);
            mAddMediaBtn = view.findViewById(R.id.add_media_btn);

            mRecyclerViewRv = (RecyclerView) view.findViewById(R.id.recyclerView_rv);
            mAddMediaCntRL.setOnClickListener(listener);
            mAddMediaBtn.setOnClickListener(listener);

        }
    }
}