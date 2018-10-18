package com.displayfort.app.fragments;

/**
 * Created by pc on 16/10/2018 15:32.
 * DisplayFortSoftware
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.displayfort.app.R;
import com.displayfort.app.base.BaseFragment;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class HomeFragment extends BaseFragment {



    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;

    public static HomeFragment newInstance() {
        HomeFragment contentFragment = new HomeFragment();
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
        View rootView = inflater.inflate(R.layout.home_fragmnt, container, false);

        return rootView;
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
                    HomeFragment.this.bitmap = bitmap;
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
}
