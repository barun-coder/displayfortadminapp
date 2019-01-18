package com.displayfort.app.newScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.displayfort.app.R;
import com.displayfort.app.adapter.AdsProfileListAdapter;
import com.displayfort.app.base.BaseActivity;
import com.displayfort.app.base.BaseAnimation;
import com.displayfort.app.model.AdsProfile;
import com.displayfort.app.screen.AddAdProfileActivity;
import com.displayfort.app.screen.ScreenScheduleActivity;
import com.displayfort.app.widgets.RecyclerItemClickListener;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;

import java.util.ArrayList;

public class AdsProfileListActivity extends BaseActivity implements View.OnClickListener {
    private Context context;
    private HomeViewHolder viewholder;
    private AdsProfileListAdapter adsProfileListAdapter;
    private ArrayList<AdsProfile> adsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ads_profile_list_layout);
        context = this;
        SetToolBarITI("Ads Profile", R.mipmap.media_library_plus_icon, "Add Profiles", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddAdProfileActivity.class);
                startActivityWithAnim(intent, BaseAnimation.EFFECT_TYPE.TAB_SLIDE_RIGHT);
            }
        });
        viewholder = new HomeViewHolder(findViewById(R.id.container_Ll), this);
        setAdapter();
    }

    private void setAdapter() {
        viewholder.mRecyclerViewRv.setLayoutManager(new LinearLayoutManager(context));
        adsList = getList();
        adsProfileListAdapter = new AdsProfileListAdapter(context, adsList);
        viewholder.mRecyclerViewRv.setAdapter(adsProfileListAdapter);
        viewholder.mRecyclerViewRv.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                }));

    }

    private ArrayList<AdsProfile> getList() {
        ArrayList<AdsProfile> AdsProfiles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AdsProfiles.add(new AdsProfile());
        }
        return AdsProfiles;
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, AdsProfileListActivity.class);
        return intent;
    }


    public class HomeViewHolder {
        public SwipyRefreshLayout mSwipeRefreshSr;
        public RecyclerView mRecyclerViewRv;

        public HomeViewHolder(View view, View.OnClickListener listener) {
            mSwipeRefreshSr = (SwipyRefreshLayout) view.findViewById(R.id.swipeRefresh_sr);
            mRecyclerViewRv = (RecyclerView) view.findViewById(R.id.recyclerView_rv);

            mSwipeRefreshSr.setOnClickListener(listener);
            mRecyclerViewRv.setOnClickListener(listener);
        }
    }


}