package com.displayfort.app.newScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.displayfort.app.R;
import com.displayfort.app.adapter.MediaListAdapter;
import com.displayfort.app.base.BaseActivity;
import com.displayfort.app.base.BaseAnimation;
import com.displayfort.app.base.Constant;
import com.displayfort.app.interfaces.OnMediaClick;
import com.displayfort.app.model.MediaDao;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;

import java.util.ArrayList;

public class MediaListActivity extends BaseActivity implements View.OnClickListener {
    private Context context;
    private MediaViewViewHolder viewholder;
    private MediaListAdapter mediaListAdapter;
    private ArrayList<MediaDao> mediaList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_layout);
        context = this;
        SetToolBar("Media's");
        viewholder = new MediaViewViewHolder(findViewById(R.id.container_Ll), this);
        setAdapter();
    }

    private void setAdapter() {
        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        viewholder.mRecyclerViewRv.setLayoutManager(manager);
        mediaList = getList();
        mediaListAdapter = new MediaListAdapter(context, mediaList, viewholder.mTopIv, new OnMediaClick() {
            @Override
            public void onMediaClick(MediaDao mediaDao) {
                Intent intent = new Intent(context, MediaItemListActivity.class);
                startActivityWithAnim(intent, BaseAnimation.EFFECT_TYPE.TAB_SLIDE_RIGHT);
            }

            @Override
            public void onMediaLongClick(MediaDao mediaDao) {

            }
        });
        viewholder.mRecyclerViewRv.setAdapter(mediaListAdapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_iv:
                addFolder();
                break;
            case R.id.right_iv:
                SearchMEdia();
                break;
            case R.id.top_iv:
                break;
            case R.id.bottom_iv:
                ArrayList<MediaDao> deleteList = new ArrayList<>();
                for (MediaDao mediaDao : mediaListAdapter.getlist()) {
                    if (mediaDao.isSelect) {
                        deleteList.add(mediaDao);
                    }
                }
                Toast.makeText(context, "Delete Total " + deleteList.size(), Toast.LENGTH_SHORT).show();
                break;
            default:
                super.onClick(v);
        }

    }

    private void addFolder() {
        //Custom layouts are discouraged due to the intended use of Snackbars,but this will do your task!
        final Snackbar snackbar = Snackbar.make(viewholder.mTopIv, "", Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

        // Inflate your custom view with an Edit Text
        LayoutInflater objLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View snackView = objLayoutInflater.inflate(R.layout.media_bottom_add_folder, null); // custom_snac_layout is your custom xml
        final EditText editText = snackView.findViewById(R.id.folder_name_et);
        ImageView btnIV = snackView.findViewById(R.id.btn_iv);
        btnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textName = editText.getText().toString();
                if (textName != null && textName.length() > 0) {
                    mediaList.add(0, new MediaDao(textName));
                    mediaListAdapter.notifyDataSetChanged();
                }
                snackbar.dismiss();
            }
        });
        ImageView mCloseIv = snackView.findViewById(R.id.close_iv);
        mCloseIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        layout.addView(snackView, 0);
        snackbar.show();
    }

    private void SearchMEdia() {
        //Custom layouts are discouraged due to the intended use of Snackbars,but this will do your task!
        final Snackbar snackbar = Snackbar.make(viewholder.mTopIv, "Hey Whats Up", Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

        // Inflate your custom view with an Edit Text
        LayoutInflater objLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View snackView = objLayoutInflater.inflate(R.layout.media_bottom_search, null); // custom_snac_layout is your custom xml
        EditText editText = snackView.findViewById(R.id.folder_name_et);
        ImageView btnIV = snackView.findViewById(R.id.btn_iv);
        btnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        ImageView mCloseIv = snackView.findViewById(R.id.close_iv);
        mCloseIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        layout.addView(snackView, 0);
        snackbar.show();
    }

    private ArrayList<MediaDao> getList() {
        ArrayList<MediaDao> ScreenDaos = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            ScreenDaos.add(new MediaDao());
        }
        return ScreenDaos;
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, MediaListActivity.class);
        return intent;
    }


    public class MediaViewViewHolder {
        public SwipyRefreshLayout mSwipeRefreshSr;
        public RecyclerView mRecyclerViewRv;
        private ImageView mLeftIv, mRightIv, mTopIv, mBottomIv;

        public MediaViewViewHolder(View view, View.OnClickListener listener) {
            mSwipeRefreshSr = (SwipyRefreshLayout) view.findViewById(R.id.swipeRefresh_sr);
            mRecyclerViewRv = (RecyclerView) view.findViewById(R.id.recyclerView_rv);
            mTopIv = (ImageView) view.findViewById(R.id.top_iv);
            mLeftIv = (ImageView) view.findViewById(R.id.left_iv);
            mBottomIv = (ImageView) view.findViewById(R.id.bottom_iv);
            mRightIv = (ImageView) view.findViewById(R.id.right_iv);
            mTopIv.setVisibility(View.GONE);
            mTopIv.setOnClickListener(listener);
            mLeftIv.setOnClickListener(listener);
            mBottomIv.setOnClickListener(listener);
            mRightIv.setOnClickListener(listener);

            mSwipeRefreshSr.setOnClickListener(listener);
            mRecyclerViewRv.setOnClickListener(listener);
        }
    }


}
