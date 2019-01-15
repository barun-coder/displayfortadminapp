package com.displayfort.app.fragments;

/**
 * Created by pc on 16/10/2018 15:32.
 * DisplayFortSoftware
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.displayfort.app.R;
import com.displayfort.app.adapter.MediaListAdapter;
import com.displayfort.app.base.BaseFragment;
import com.displayfort.app.model.MediaDao;
import com.displayfort.app.screen.ScreenDetailActivity;
import com.displayfort.app.widgets.RecyclerItemClickListener;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;

import java.util.ArrayList;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class MediaFragment extends BaseFragment {


    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;
    private MediaViewHolder mediaViewHolder;
    private Context mContext;

    private MediaListAdapter mediaListAdapter;
    private ArrayList<MediaDao> screenList;

    public static MediaFragment newInstance() {
        MediaFragment contentFragment = new MediaFragment();
        Bundle bundle = new Bundle();
//        bundle.putInt(Integer.class.getName(), resId);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
        mediaViewHolder = new MediaViewHolder(view, this);
        setAdapter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getInt(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.media_add_fragmnt, container, false);
        mContext = getActivity();
        return rootView;
    }

    private void setAdapter() {
        mediaViewHolder.mRecyclerViewRv.setLayoutManager(new LinearLayoutManager(mContext));
        screenList = getList();
                mediaViewHolder.mRecyclerViewRv.setAdapter(mediaListAdapter);
        mediaViewHolder.mRecyclerViewRv.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
//                        Intent intent = new Intent(mContext, ScreenDetailActivity.class);
//                        intent.putExtra("SCREEN", screenList.get(position).name);
//                        startActivityWithAnim(getActivity(), intent);
                    }
                }));

    }

    private ArrayList<MediaDao> getList() {
        ArrayList<MediaDao> ScreenDaos = new ArrayList<>();
        ScreenDaos.add(new MediaDao());
        ScreenDaos.add(new MediaDao());
        ScreenDaos.add(new MediaDao());
        ScreenDaos.add(new MediaDao());
        return ScreenDaos;
    }

    @Override
    public void takeScreenShot() {
        try {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                            containerView.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    containerView.draw(canvas);
                    MediaFragment.this.bitmap = bitmap;
                }
            };

            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    public class MediaViewHolder {
        public SwipyRefreshLayout mSwipeRefreshSr;
        public RecyclerView mRecyclerViewRv;

        public MediaViewHolder(View view, View.OnClickListener listener) {
            mSwipeRefreshSr = (SwipyRefreshLayout) view.findViewById(R.id.swipeRefresh_sr);
            mRecyclerViewRv = (RecyclerView) view.findViewById(R.id.recyclerView_rv);

            mSwipeRefreshSr.setOnClickListener(listener);
            mRecyclerViewRv.setOnClickListener(listener);
        }
    }
}