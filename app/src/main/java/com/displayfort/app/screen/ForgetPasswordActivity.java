package com.displayfort.app.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.displayfort.app.R;
import com.displayfort.app.base.BaseActivity;
import com.displayfort.app.validation.ValidationUtils;

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {

    private Context context;
    private ForgetPasswordViewHolder forgetPasswordViewHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password_activity);
        context = this;
        SetToolBar("Forgot Password");
        forgetPasswordViewHolder = new ForgetPasswordViewHolder(findViewById(R.id.container_Ll), this);
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, ForgetPasswordActivity.class);
        return intent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_btn:
                String emailid = forgetPasswordViewHolder.mEmailEt.getText().toString();
                if (ValidationUtils.isValidString(emailid, 4)) {

                }
                break;
            default:
                break;
        }
    }

    /**/
    private class ForgetPasswordViewHolder {
        public ImageView mLoaogLoginIv;
        public TextView mFpTv;
        public EditText mEmailEt;
        public Button mSendBtn;

        public ForgetPasswordViewHolder(View view, View.OnClickListener listener) {
            mLoaogLoginIv = (ImageView) view.findViewById(R.id.loaog_login_iv);
            mFpTv = (TextView) view.findViewById(R.id.fp_tv);
            mEmailEt = (EditText) view.findViewById(R.id.email_et);
            mSendBtn = (Button) view.findViewById(R.id.send_btn);

            mSendBtn.setOnClickListener(listener);
        }
    }

}