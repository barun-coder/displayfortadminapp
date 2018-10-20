package com.displayfort.app.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.displayfort.app.R;
import com.displayfort.app.base.BaseActivity;

public class UpdateGeneralActivity extends BaseActivity implements View.OnClickListener {

    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_general_layout);
        context = this;
        SetToolBar(getIntent().getExtras().getString("SCREEN", "Update Detail"));
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, UpdateGeneralActivity.class);
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