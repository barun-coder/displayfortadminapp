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
import com.displayfort.app.base.BaseAnimation;
import com.displayfort.app.newScreen.MediaListActivity;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class AddAdProfileMediaContentActivity extends BaseActivity implements View.OnClickListener {

    private Context context;
    private AddAdMediaViewHolder addAdMediaViewHolder;
    private int SECONDS = 1;
    private int PROIRITY = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_profile_media_content_layout);
        context = this;
        SetToolBar("Add Media");
        addAdMediaViewHolder = new AddAdMediaViewHolder(findViewById(R.id.container_Ll), this);
        init();
    }

    private void init() {

    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, AddAdProfileMediaContentActivity.class);
        return intent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_btn:
                setResult(RESULT_OK);
                finishActivityWithAnim();
                break;
            case R.id.upload_iv:
                Intent intent = new Intent(context, MediaListActivity.class);
                startActivityWithResultAnim(intent, 302, BaseAnimation.EFFECT_TYPE.TAB_SLIDE_RIGHT);
                break;
            case R.id.delete_iv:
                setResult(RESULT_OK);
                finishActivityWithAnim();
                break;

            case R.id.minus_iv:
                if (SECONDS > 0) {
                    addAdMediaViewHolder.mSecondsEt.setText((--SECONDS)+"");
                }
                break;

            case R.id.plus_id:
                addAdMediaViewHolder.mSecondsEt.setText((++SECONDS)+"");
                break;
            case R.id.pr_minus_iv:
                if (SECONDS > 0) {
                    addAdMediaViewHolder.mPriorityTv.setText((--PROIRITY)+"");
                }
                break;
            case R.id.pr_plus_id:
                addAdMediaViewHolder.mPriorityTv.setText((++PROIRITY)+"");
                break;
            case R.id.sun_btn:
                setWeekk(addAdMediaViewHolder.mSunBtn);
                break;
            case R.id.mon_btn:
                setWeekk(addAdMediaViewHolder.mMonBtn);
                break;
            case R.id.tue_btn:
                setWeekk(addAdMediaViewHolder.mTueBtn);
                break;
            case R.id.wed_btn:
                setWeekk(addAdMediaViewHolder.mWedBtn);
                break;
            case R.id.thu_btn:
                setWeekk(addAdMediaViewHolder.mThuBtn);
                break;
            case R.id.fri_btn:
                setWeekk(addAdMediaViewHolder.mFriBtn);
                break;
            case R.id.sat_btn:
                setWeekk(addAdMediaViewHolder.mSatBtn);
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
        public ImageView mDeleteIv;
        public ImageView mUploadIv;
        public ImageView mPlusId;
        public ImageView mMinusIv;
        public TextView mSecondsEt;
        public Button mSunBtn;
        public Button mMonBtn;
        public Button mTueBtn;
        public Button mWedBtn;
        public Button mThuBtn;
        public Button mFriBtn;
        public Button mSatBtn;
        public ImageView mMediaIv;
        public ImageView mPrPlusId;
        public ImageView mPrMinusIv;
        public TextView mPriorityTv;
        public Button mSaveBtn;

        public AddAdMediaViewHolder(View view, View.OnClickListener listener) {

            mDeleteIv = (ImageView) view.findViewById(R.id.delete_iv);
            mUploadIv = (ImageView) view.findViewById(R.id.upload_iv);
            mPlusId = (ImageView) view.findViewById(R.id.plus_id);
            mMinusIv = (ImageView) view.findViewById(R.id.minus_iv);
            mSecondsEt = (TextView) view.findViewById(R.id.seconds_et);
            mMediaIv = (ImageView) view.findViewById(R.id.media_iv);
            /**/
            mSunBtn = (Button) view.findViewById(R.id.sun_btn);
            mMonBtn = (Button) view.findViewById(R.id.mon_btn);
            mTueBtn = (Button) view.findViewById(R.id.tue_btn);
            mWedBtn = (Button) view.findViewById(R.id.wed_btn);
            mThuBtn = (Button) view.findViewById(R.id.thu_btn);
            mFriBtn = (Button) view.findViewById(R.id.fri_btn);
            mSatBtn = (Button) view.findViewById(R.id.sat_btn);
            /**/
            mPrPlusId = (ImageView) view.findViewById(R.id.pr_plus_id);
            mPrMinusIv = (ImageView) view.findViewById(R.id.pr_minus_iv);
            mPriorityTv = (TextView) view.findViewById(R.id.priority_tv);
            mSaveBtn = (Button) view.findViewById(R.id.save_btn);


            mDeleteIv.setOnClickListener(listener);
            mUploadIv.setOnClickListener(listener);
            mPlusId.setOnClickListener(listener);
            mMinusIv.setOnClickListener(listener);
            mSecondsEt.setOnClickListener(listener);
            mSunBtn.setOnClickListener(listener);
            mMonBtn.setOnClickListener(listener);
            mTueBtn.setOnClickListener(listener);
            mWedBtn.setOnClickListener(listener);
            mThuBtn.setOnClickListener(listener);
            mFriBtn.setOnClickListener(listener);
            mSatBtn.setOnClickListener(listener);
            mPrPlusId.setOnClickListener(listener);
            mPrMinusIv.setOnClickListener(listener);
            mPriorityTv.setOnClickListener(listener);
            mSaveBtn.setOnClickListener(listener);

            mSunBtn.setSelected(false);
            mMonBtn.setSelected(false);
            mTueBtn.setSelected(false);
            mWedBtn.setSelected(false);
            mThuBtn.setSelected(false);
            mFriBtn.setSelected(false);
            mSatBtn.setSelected(false);

        }
    }
}