package com.displayfort.app.newScreen;

/**
 * Created by pc on 17/10/2018 18:17.
 * DisplayFortSoftware
 */

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.displayfort.app.R;
import com.displayfort.app.base.BaseActivity;

public class MyProfileDetailActivity extends BaseActivity {


    private Context context;
    private PartnersDetailViewHolder screenDetailViewHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partners_detail_layout);
        context = this;
        SetToolBar("Screens");
        screenDetailViewHolder = new PartnersDetailViewHolder(findViewById(R.id.container_Ll), this);
        init();
    }

    private void init() {
        screenDetailViewHolder.mToggleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    Log.d("alarmCheck", "ALARM SET TO TRUE");
                } else {
                    Log.d("alarmCheck", "ALARM SET TO FALSE");
                }
            }
        });
    }

    public class PartnersDetailViewHolder {
        public LinearLayout mContainerLl;
        public ImageView mHexaImageIv;
        public EditText mUsernameEt;
        public EditText mFirstnameEt;
        public EditText mLastnameEt;
        public EditText mMobileEt;
        public EditText mEmailEt;
        public EditText mCompanyNameEt;
        public Button mUpdateBtn;
        public CheckBox mToggleBtn;

        public PartnersDetailViewHolder(View view, View.OnClickListener listener) {
            mContainerLl = (LinearLayout) view.findViewById(R.id.container_Ll);
            mHexaImageIv = (ImageView) view.findViewById(R.id.hexaImage_iv);
            mUsernameEt = (EditText) view.findViewById(R.id.username_et);
            mFirstnameEt = (EditText) view.findViewById(R.id.firstname_et);
            mLastnameEt = (EditText) view.findViewById(R.id.lastname_et);
            mMobileEt = (EditText) view.findViewById(R.id.mobile_et);
            mEmailEt = (EditText) view.findViewById(R.id.email_et);
            mCompanyNameEt = (EditText) view.findViewById(R.id.company_name_et);
            mUpdateBtn = (Button) view.findViewById(R.id.update_btn);
            mToggleBtn = view.findViewById(R.id.chkState);

            mUpdateBtn.setOnClickListener(listener);
        }
    }


}