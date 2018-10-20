package com.displayfort.app.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.displayfort.app.R;
import com.displayfort.app.adapter.AdsProfileListAdapter;
import com.displayfort.app.adapter.AssignAdsProfileListAdapter;
import com.displayfort.app.base.BaseActivity;
import com.displayfort.app.model.AdsProfile;
import com.displayfort.app.widgets.RecyclerItemClickListener;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;

import java.util.ArrayList;

public class AssignAdsScreenActivity extends BaseActivity implements View.OnClickListener {

    private Context context;
    private AssignAdsViewHolder assignAdsViewHolder;
    private ArrayList<AdsProfile> adsList;
    private AssignAdsProfileListAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_ads_screen);
        context = this;
        SetToolBarITI("Assign Ads", R.mipmap.ic_add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        assignAdsViewHolder = new AssignAdsViewHolder(findViewById(R.id.container_ll), this);
        setAdapter();
    }

    private void setAdapter() {
        assignAdsViewHolder.mRecyclerViewRv.setLayoutManager(new LinearLayoutManager(context));
        adsList = getList();
        listAdapter = new AssignAdsProfileListAdapter(context, adsList);
        assignAdsViewHolder.mRecyclerViewRv.setAdapter(listAdapter);
        assignAdsViewHolder.mRecyclerViewRv.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                    }
                }));

    }

    private ArrayList<AdsProfile> getList() {
        ArrayList<AdsProfile> AdsProfiles = new ArrayList<>();
        AdsProfiles.add(new AdsProfile());
        AdsProfiles.add(new AdsProfile());
        AdsProfiles.add(new AdsProfile());
        AdsProfiles.add(new AdsProfile());
        AdsProfiles.add(new AdsProfile());
        return AdsProfiles;
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, AssignAdsScreenActivity.class);
        return intent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                super.onClick(v);
                break;
        }
    }

    /**/
    public class AssignAdsViewHolder {
        public SwipyRefreshLayout mSwipeRefreshSr;
        public RecyclerView mRecyclerViewRv;

        public AssignAdsViewHolder(View view, View.OnClickListener listener) {
            mSwipeRefreshSr = (SwipyRefreshLayout) view.findViewById(R.id.swipeRefresh_sr);
            mRecyclerViewRv = (RecyclerView) view.findViewById(R.id.recyclerView_rv);
            mSwipeRefreshSr.setEnabled(false);
        }
    }
}