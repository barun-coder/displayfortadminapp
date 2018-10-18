package com.displayfort.app.fragments;

/**
 * Created by pc on 16/10/2018 15:32.
 * DisplayFortSoftware
 */

import android.content.Context;
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
import com.displayfort.app.adapter.AdsProfileListAdapter;
import com.displayfort.app.base.BaseFragment;
import com.displayfort.app.model.AdsProfile;
import com.displayfort.app.widgets.RecyclerItemClickListener;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;

import java.util.ArrayList;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class AdsFragment extends BaseFragment implements View.OnClickListener {


    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;
    private HomeViewHolder homeViewHolder;
    private Context mContext;

    private AdsProfileListAdapter listAdapter;
    private ArrayList<AdsProfile> adsList;

    public static ScreenFragment newInstance() {
        ScreenFragment contentFragment = new ScreenFragment();
        Bundle bundle = new Bundle();
//        bundle.putInt(Integer.class.getName(), resId);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
        homeViewHolder = new HomeViewHolder(view, this);
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
        View rootView = inflater.inflate(R.layout.ads_profile_fragmnt, container, false);
        mContext = getActivity();
        return rootView;
    }

    private void setAdapter() {
        homeViewHolder.mRecyclerViewRv.setLayoutManager(new LinearLayoutManager(mContext));
        adsList = getList();
        listAdapter = new AdsProfileListAdapter(mContext, adsList);
        homeViewHolder.mRecyclerViewRv.setAdapter(listAdapter);
        homeViewHolder.mRecyclerViewRv.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
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
        return AdsProfiles;
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
                    AdsFragment.this.bitmap = bitmap;
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
