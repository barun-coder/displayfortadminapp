package com.displayfort.app.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.displayfort.app.R;
import com.tapadoo.alerter.Alerter;

/**
 * Base Class contain all common Method use in activity
 *
 * @author Husain
 */
public class BaseActivity extends AppCompatActivity implements OnClickListener {
    private Context mContext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext = this;


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                onBackPressed();
        }
    }

    /**
     * @param intent Start Activity with Custom animation
     */
    public void startActivityWithAnim(Intent intent) {
        startActivityWithAnim(intent, BaseAnimation.EFFECT_TYPE.TAB_DEAFULT);
    }

    public void startActivityWithAnim(Intent intent, BaseAnimation.EFFECT_TYPE type) {
        startActivity(intent);
        setAnimationTransition(type);

    }

    public void startActivityWithResultAnim(Intent intent, int requestCode) {
        startActivityWithResultAnim(intent, requestCode,
                BaseAnimation.EFFECT_TYPE.TAB_DEAFULT);
    }

    public void startActivityWithResultAnim(Intent intent, int requestCode,
                                            BaseAnimation.EFFECT_TYPE type) {
        startActivityForResult(intent, requestCode);
        setAnimationTransition(type);
    }

    public void finishActivityWithAnim() {
        finishActivityWithAnim(BaseAnimation.EFFECT_TYPE.TAB_DEAFULT);

    }

    public void finishActivityWithAnim(BaseAnimation.EFFECT_TYPE type) {
        finish();
        setAnimationTransition(type);


    }

    private void setAnimationTransition(BaseAnimation.EFFECT_TYPE type) {
        BaseAnimation.setAnimationTransition(this, type);

    }

    /*toolbar*/
    public void SetToolBar(String title) {
        ImageView leftIconIv = findViewById(R.id.imgBack);
        leftIconIv.setOnClickListener(this);
        TextView mtxtToolbarTitleTv = (TextView) findViewById(R.id.txtToolbarTitle);
        mtxtToolbarTitleTv.setText(title);
    }

    public void SetToolBarIT(String title, OnClickListener leftListener) {
        ImageView leftIconIv = findViewById(R.id.imgBack);
        if (leftListener != null) {
            leftIconIv.setVisibility(View.VISIBLE);
            leftIconIv.setOnClickListener(leftListener);
        } else {
            leftIconIv.setVisibility(View.INVISIBLE);
        }
        TextView mtxtToolbarTitleTv = (TextView) findViewById(R.id.txtToolbarTitle);
        mtxtToolbarTitleTv.setText(title);
    }

    public void SetToolBarITI(String title, OnClickListener leftListener, int rightIv, OnClickListener rightListener) {
        ImageView leftIconIv = findViewById(R.id.imgBack);
        ImageView rightIconIv = findViewById(R.id.rightIv);
        rightIconIv.setVisibility(View.VISIBLE);
        if (rightIv != 0) {
            rightIconIv.setImageResource(rightIv);
        }
        rightIconIv.setOnClickListener(rightListener);
        if (leftListener != null) {
            leftIconIv.setVisibility(View.VISIBLE);
            leftIconIv.setOnClickListener(leftListener);
        } else {
            leftIconIv.setVisibility(View.INVISIBLE);
        }
        TextView mtxtToolbarTitleTv = (TextView) findViewById(R.id.txtToolbarTitle);
        mtxtToolbarTitleTv.setText(title);
    }

    public void SetToolBarITI(String title, int rightIv, OnClickListener rightListener) {
        ImageView leftIconIv = findViewById(R.id.imgBack);
        leftIconIv.setOnClickListener(this);
        ImageView rightIconIv = findViewById(R.id.rightIv);
        rightIconIv.setVisibility(View.VISIBLE);
        if (rightIv != 0) {
            rightIconIv.setImageResource(rightIv);
        }
        rightIconIv.setOnClickListener(rightListener);

        TextView mtxtToolbarTitleTv = (TextView) findViewById(R.id.txtToolbarTitle);
        mtxtToolbarTitleTv.setText(title);
    }

    public void showError(String title, String msg) {
        Alerter.create(this)
                .setDuration(1500)
                .setTitle(title)
                .setText(msg)
                .setBackgroundColorRes(R.color.colorPrimaryDark) // or setBackgroundColorInt(Color.CYAN)
                .show();
    }
}
