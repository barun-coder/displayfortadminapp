package com.displayfort.app.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.displayfort.app.R;
import com.displayfort.app.screen.NewHomeScreenActivity;
import com.tapadoo.alerter.Alerter;

import net.alhazmy13.mediapicker.Utility;

/**
 * Base Class contain all common Method use in activity
 *
 * @author Husain
 */
public class BaseActivity extends AppCompatActivity implements OnClickListener {
    private Context mContext;

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {

                    return false;
                }

            });
        }

        // If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext = this;


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtToolbarTitle:
            case R.id.imgBack:
                hideSoftKeyboard(this);
                onBackPressed();
        }
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
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
        TextView mtxtToolbarTitleTv = (TextView) findViewById(R.id.txtToolbarTitle);
        leftIconIv.setOnClickListener(this);
        mtxtToolbarTitleTv.setOnClickListener(this);
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

    public void SetToolBarITI(String title, int rightIv, String righttxt, OnClickListener rightListener) {
        ImageView leftIconIv = findViewById(R.id.imgBack);
        TextView mtxtToolbarTitleTv = (TextView) findViewById(R.id.txtToolbarTitle);
        leftIconIv.setOnClickListener(this);
        mtxtToolbarTitleTv.setOnClickListener(this);
        mtxtToolbarTitleTv.setText(title);

        ImageView rightIconIv = findViewById(R.id.rightIv);
        TextView rightTv = findViewById(R.id.right_tv);
        RelativeLayout rightRl = findViewById(R.id.right_rl);
        rightRl.setVisibility(View.VISIBLE);
        rightTv.setText(righttxt);

        if (rightIv != 0) {
            rightIconIv.setVisibility(View.VISIBLE);
            rightIconIv.setImageResource(rightIv);
        } else {
            rightIconIv.setVisibility(View.INVISIBLE);
        }
        rightRl.setOnClickListener(rightListener);


    }

    public void SetToolBarITT(String title, String rightIv, OnClickListener rightListener) {
        ImageView leftIconIv = findViewById(R.id.imgBack);
        leftIconIv.setOnClickListener(this);
        TextView rightIconIv = findViewById(R.id.rightTv);
        rightIconIv.setVisibility(View.VISIBLE);
        if (rightIv != null) {
            rightIconIv.setText(rightIv);
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

    public void headLogo(View view) {
        Intent intent = new Intent(this, NewHomeScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityWithAnim(intent);
        finishActivityWithAnim();
    }
}
