package com.displayfort.app.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.displayfort.app.R;
import com.displayfort.app.base.BaseActivity;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class AddAdMediaActivity extends BaseActivity implements View.OnClickListener {

    private Context context;
    private AddAdMediaViewHolder addAdMediaViewHolder;
    private static final String[] ANDROID_VERSIONS = {
            "IMAGES",
            "GIF",
            "VIDEO",
            "URL"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ad_media_layout);
        context = this;
        SetToolBar("Add Media");
        addAdMediaViewHolder = new AddAdMediaViewHolder(findViewById(R.id.container_ll), this);
        init();
    }

    private void init() {
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setItems(ANDROID_VERSIONS);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
        spinner.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
                Snackbar.make(spinner, "Nothing selected", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, AddAdMediaActivity.class);
        return intent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_btn:
                setResult(RESULT_OK);
                finishActivityWithAnim();
                break;
            case R.id.week_days_su_tv:
                setWeekk(addAdMediaViewHolder.mWeekDaysSuIv);
                break;
            case R.id.week_days_m_tv:
                setWeekk(addAdMediaViewHolder.mWeekDaysMIv);
                break;
            case R.id.week_days_t_tv:
                setWeekk(addAdMediaViewHolder.mWeekDaysTIv);
                break;
            case R.id.week_days_w_tv:
                setWeekk(addAdMediaViewHolder.mWeekDaysWIv);
                break;
            case R.id.week_days_th_tv:
                setWeekk(addAdMediaViewHolder.mWeekDaysThIv);
                break;
            case R.id.week_days_f_tv:
                setWeekk(addAdMediaViewHolder.mWeekDaysFIv);
                break;
            case R.id.week_days_s_tv:
                setWeekk(addAdMediaViewHolder.mWeekDaysSIv);
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    private void setWeekk(TextView mweekdaysIv) {
        mweekdaysIv.setSelected(!mweekdaysIv.isSelected());
    }

    public class AddAdMediaViewHolder {
        public ImageView mMediaIv;
        public ImageView mAttachIv;
        public TextView mWeekDaysSuIv;
        public TextView mWeekDaysMIv;
        public TextView mWeekDaysTIv;
        public TextView mWeekDaysWIv;
        public TextView mWeekDaysThIv;
        public TextView mWeekDaysFIv;
        public TextView mWeekDaysSIv;
        public EditText mSecondsEt;
        public EditText mPriorityOrderEt;
        public Button mUpdateBtn;

        public AddAdMediaViewHolder(View view, View.OnClickListener listener) {

            mMediaIv = (ImageView) view.findViewById(R.id.media_iv);
            mAttachIv = (ImageView) view.findViewById(R.id.attach_iv);
            mWeekDaysSuIv = (TextView) view.findViewById(R.id.week_days_su_tv);
            mWeekDaysMIv = (TextView) view.findViewById(R.id.week_days_m_tv);
            mWeekDaysTIv = (TextView) view.findViewById(R.id.week_days_t_tv);
            mWeekDaysWIv = (TextView) view.findViewById(R.id.week_days_w_tv);
            mWeekDaysThIv = (TextView) view.findViewById(R.id.week_days_th_tv);
            mWeekDaysFIv = (TextView) view.findViewById(R.id.week_days_f_tv);
            mWeekDaysSIv = (TextView) view.findViewById(R.id.week_days_s_tv);
            mSecondsEt = (EditText) view.findViewById(R.id.seconds_et);
            mPriorityOrderEt = (EditText) view.findViewById(R.id.priority_order_et);
            mUpdateBtn = (Button) view.findViewById(R.id.update_btn);


            mWeekDaysSuIv.setOnClickListener(listener);
            mWeekDaysMIv.setOnClickListener(listener);
            mWeekDaysTIv.setOnClickListener(listener);
            mWeekDaysWIv.setOnClickListener(listener);
            mWeekDaysThIv.setOnClickListener(listener);
            mWeekDaysFIv.setOnClickListener(listener);
            mWeekDaysSIv.setOnClickListener(listener);

            mWeekDaysSuIv.setSelected(true);
            mWeekDaysMIv.setSelected(true);
            mWeekDaysTIv.setSelected(true);
            mWeekDaysWIv.setSelected(true);
            mWeekDaysThIv.setSelected(true);
            mWeekDaysFIv.setSelected(true);
            mWeekDaysSIv.setSelected(true);


            mUpdateBtn.setOnClickListener(listener);
        }
    }
}