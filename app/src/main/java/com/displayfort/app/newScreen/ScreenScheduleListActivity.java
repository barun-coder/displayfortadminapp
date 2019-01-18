package com.displayfort.app.newScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.displayfort.app.R;
import com.displayfort.app.adapter.ScheduleListAdapter;
import com.displayfort.app.adapter.ScreenScheduleListAdapter;
import com.displayfort.app.base.BaseActivity;
import com.displayfort.app.model.ScheduleDao;
import com.displayfort.app.widgets.RecyclerItemClickListener;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;

import java.util.ArrayList;

public class ScreenScheduleListActivity extends BaseActivity implements View.OnClickListener {
    private Context context;
    private HomeViewHolder viewholder;
    private ScreenScheduleListAdapter adsProfileListAdapter;
    private ArrayList<ScheduleDao> adsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_list_layout);
        context = this;
        SetToolBarITI("Screen Schedule", R.mipmap.media_library_plus_icon, "Add Schedule", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewholder = new HomeViewHolder(findViewById(R.id.container_Ll), this);
        setAdapter();
    }

    private void setAdapter() {
        viewholder.mRecyclerViewRv.setLayoutManager(new LinearLayoutManager(context));
        adsList = getList();
        adsProfileListAdapter = new ScreenScheduleListAdapter(context, adsList);
        viewholder.mRecyclerViewRv.setAdapter(adsProfileListAdapter);
        viewholder.mRecyclerViewRv.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                }));

    }

    private ArrayList<ScheduleDao> getList() {
        ArrayList<ScheduleDao> ScheduleDaos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ScheduleDaos.add(new ScheduleDao());
        }
        return ScheduleDaos;
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, ScreenScheduleListActivity.class);
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