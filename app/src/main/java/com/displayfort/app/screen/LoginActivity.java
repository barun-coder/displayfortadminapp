package com.displayfort.app.screen;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.displayfort.app.DFPrefrence;
import com.displayfort.app.R;
import com.displayfort.app.base.BaseActivity;
import com.displayfort.app.validation.ValidationUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {
    private static final int RC_SIGN_IN = 101;
    private static String TAG = "LOGIN";
    private Context context;
    private ActivityViewHolder activityViewHolder;
    private String username, password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        context = this;
        activityViewHolder = new ActivityViewHolder(findViewById(R.id.container_Ll), this);
        init();


    }

    private void init() {
        ImageView imageView = findViewById(R.id.car);
        TranslateAnimation mAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 1.5f);
        mAnimation.setDuration(400);
        mAnimation.setRepeatCount(1500);
        mAnimation.setRepeatMode(Animation.REVERSE);
        mAnimation.setInterpolator(new LinearInterpolator());
        imageView.setAnimation(mAnimation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                if (isValid()) {
//                    loginRequest();
                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivityWithAnim(intent);
                    finishActivityWithAnim();
                    new DFPrefrence(context).setIsLogin(true);

                }

                break;
            case R.id.forget_password_tv:
                Intent intent = new Intent(context, ForgetPasswordActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivityWithAnim(intent);
                finishActivityWithAnim();
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    private boolean isValid() {
        username = activityViewHolder.mEmailEt.getText().toString();
        password = activityViewHolder.mPasswordEt.getText().toString();
        if (!ValidationUtils.isValidString(username, 2)) {
            showError("Not a valid Username.", "Please enter valid Username");
            return false;
        }

        if (!ValidationUtils.isValidString(password, 6)) {
            showError("Not a valid Password.", "Please enter valid password");
            return false;
        }

        return true;

    }

    public class ActivityViewHolder {
        public ImageView mLoaogLoginIv;
        public EditText mEmailEt;
        public EditText mPasswordEt;
        public Button mLoginBtn;
        public TextView mForgetPasswordTv;

        public ActivityViewHolder(View view, View.OnClickListener listener) {
            mLoaogLoginIv = (ImageView) view.findViewById(R.id.loaog_login_iv);
            mEmailEt = (EditText) view.findViewById(R.id.email_et);
            mPasswordEt = (EditText) view.findViewById(R.id.password_et);
            mLoginBtn = (Button) view.findViewById(R.id.login_btn);
            mForgetPasswordTv = (TextView) view.findViewById(R.id.forget_password_tv);


            mLoginBtn.setOnClickListener(listener);
            mForgetPasswordTv.setOnClickListener(listener);
        }
    }
}

