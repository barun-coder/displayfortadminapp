package com.displayfort.app.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.displayfort.app.R;
import com.displayfort.app.base.BaseActivity;

public class UpdateLinkDetailActivity extends BaseActivity implements View.OnClickListener {

    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_link_layout);
        context = this;
        SetToolBar("Update Link");
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, UpdateLinkDetailActivity.class);
        return intent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                super.onClick(v);
                break;
        }
    }

}