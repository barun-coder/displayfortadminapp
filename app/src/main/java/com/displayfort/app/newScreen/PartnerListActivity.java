package com.displayfort.app.newScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.displayfort.app.R;
import com.displayfort.app.adapter.PartnersListAdapter;
import com.displayfort.app.base.BaseActivity;
import com.displayfort.app.model.PartnersDao;
import com.displayfort.app.widgets.RecyclerItemClickListener;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;

import java.util.ArrayList;

public class PartnerListActivity extends BaseActivity implements View.OnClickListener {
    private Context context;
    private PartnersViewHolder viewholder;
    private PartnersListAdapter screenListAdapter;
    private ArrayList<PartnersDao> partnersList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partners_layout);
        context = this;
        SetToolBar("Partners");
        viewholder = new PartnersViewHolder(findViewById(R.id.container_Ll), this);
        setAdapter();
    }

    private void setAdapter() {
        viewholder.mRecyclerViewRv.setLayoutManager(new LinearLayoutManager(context));
        partnersList = getList();
        screenListAdapter = new PartnersListAdapter(context, partnersList);
        viewholder.mRecyclerViewRv.setAdapter(screenListAdapter);
        viewholder.mRecyclerViewRv.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                }));

    }

    private ArrayList<PartnersDao> getList() {
        ArrayList<PartnersDao> ScreenDaos = new ArrayList<>();
        ScreenDaos.add(new PartnersDao());
        ScreenDaos.add(new PartnersDao());
        ScreenDaos.add(new PartnersDao());
        ScreenDaos.add(new PartnersDao());
        ScreenDaos.add(new PartnersDao());
        ScreenDaos.add(new PartnersDao());
        ScreenDaos.add(new PartnersDao());
        ScreenDaos.add(new PartnersDao());
        return ScreenDaos;
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, PartnerListActivity.class);
        return intent;
    }


    public class PartnersViewHolder {
        public SwipyRefreshLayout mSwipeRefreshSr;
        public RecyclerView mRecyclerViewRv;

        public PartnersViewHolder(View view, View.OnClickListener listener) {
            mSwipeRefreshSr = (SwipyRefreshLayout) view.findViewById(R.id.swipeRefresh_sr);
            mRecyclerViewRv = (RecyclerView) view.findViewById(R.id.recyclerView_rv);

            mSwipeRefreshSr.setOnClickListener(listener);
            mRecyclerViewRv.setOnClickListener(listener);
        }
    }


}