package com.displayfort.app.newScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.displayfort.app.R;
import com.displayfort.app.adapter.PartnersListAdapter;
import com.displayfort.app.adapter.ScreenListAdapter;
import com.displayfort.app.base.BaseActivity;
import com.displayfort.app.fragments.ScreenFragment;
import com.displayfort.app.model.PartnersDao;
import com.displayfort.app.model.ScreenDao;
import com.displayfort.app.widgets.RecyclerItemClickListener;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;

import java.util.ArrayList;

public class ScreenListActivity extends BaseActivity implements View.OnClickListener {
    private Context context;
    private HomeViewHolder viewholder;
    private ScreenListAdapter screenListAdapter;
    private ArrayList<ScreenDao> screenList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screens_list_layout);
        context = this;
        SetToolBar("Screens");
        viewholder = new HomeViewHolder(findViewById(R.id.container_Ll), this);
        setAdapter();
    }

    private void setAdapter() {
        viewholder.mRecyclerViewRv.setLayoutManager(new LinearLayoutManager(context));
        screenList = getList();
        screenListAdapter = new ScreenListAdapter(context, screenList);
        viewholder.mRecyclerViewRv.setAdapter(screenListAdapter);
        viewholder.mRecyclerViewRv.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                }));

    }

    private ArrayList<ScreenDao> getList() {
        ArrayList<ScreenDao> ScreenDaos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ScreenDaos.add(new ScreenDao());
        }
        return ScreenDaos;
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, ScreenListActivity.class);
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