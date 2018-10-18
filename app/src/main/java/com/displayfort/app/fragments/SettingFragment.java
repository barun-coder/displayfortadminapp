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
import com.displayfort.app.adapter.ScreenListAdapter;
import com.displayfort.app.base.BaseFragment;
import com.displayfort.app.model.ScreenDao;
import com.displayfort.app.widgets.RecyclerItemClickListener;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;

import java.util.ArrayList;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener {


    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;
 private Context mContext;

    private ScreenListAdapter screenListAdapter;
    private ArrayList<ScreenDao> screenList;

    public static SettingFragment newInstance() {
        SettingFragment contentFragment = new SettingFragment();
        Bundle bundle = new Bundle();
//        bundle.putInt(Integer.class.getName(), resId);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getInt(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.setting_fragmnt, container, false);
        mContext = getActivity();
        return rootView;
    }

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                SettingFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }


}
