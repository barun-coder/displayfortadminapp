package com.displayfort.app.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.displayfort.app.R;
import com.displayfort.app.base.BaseActivity;
import com.displayfort.app.base.BaseAnimation;

public class AddAdProfileActivity extends BaseActivity implements View.OnClickListener {

    private Context context;
    private AddAdProfileViewHolder addAdProfileViewHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ad_profile_layout);
        context = this;
        SetToolBarITT("Ad-Profile", "SAVE", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddAdProfileActivity.class);
                startActivityWithAnim(intent, BaseAnimation.EFFECT_TYPE.TAB_SLIDE_RIGHT);
            }
        });
        addAdProfileViewHolder = new AddAdProfileViewHolder(findViewById(R.id.container_ll), this);
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, AddAdProfileActivity.class);
        return intent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_media_btn:
                Intent intent = new Intent(context, AddAdMediaActivity.class);
                startActivityWithResultAnim(intent, 301, BaseAnimation.EFFECT_TYPE.TAB_SLIDE_RIGHT);
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 301) {

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    public class AddAdProfileViewHolder {
        public EditText mProfileEt;
        public LinearLayout mMediaLl;
        public Button mAddMediaBtn;

        public AddAdProfileViewHolder(View view, View.OnClickListener listener) {
            mProfileEt = (EditText) view.findViewById(R.id.profile_et);
            mMediaLl = (LinearLayout) view.findViewById(R.id.media_ll);
            mAddMediaBtn = (Button) view.findViewById(R.id.add_media_btn);

            mProfileEt.setOnClickListener(listener);
            mMediaLl.setOnClickListener(listener);
            mAddMediaBtn.setOnClickListener(listener);
        }
    }
}