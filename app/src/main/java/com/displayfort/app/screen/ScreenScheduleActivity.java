package com.displayfort.app.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.displayfort.app.R;
import com.displayfort.app.TextUtils.TypefaceHashTagAutocompleteBrandenReg;
import com.displayfort.app.base.BaseActivity;
import com.displayfort.app.base.BaseAnimation;
import com.displayfort.app.widgets.HashTagSuggestAdapter;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ScreenScheduleActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "SwitchDateTimeDialogFragment";
    private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";
    private static final String STATE_TEXTVIEW = "STATE_TEXTVIEW";
    private Context context;
    private ScreenScheduleViewHolder viewholder;
    private SwitchDateTimeDialogFragment dateTimeFragment;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_schedule_activity);
        context = this;
        SetToolBar("Schedule Screen");
        viewholder = new ScreenScheduleViewHolder(findViewById(R.id.container_ll), this);
        initHashTag();
        initiDatetimePicker();
    }

    private void initHashTag() {
        final TypefaceHashTagAutocompleteBrandenReg textView = (TypefaceHashTagAutocompleteBrandenReg) findViewById(R.id.input_form);

        HashTagSuggestAdapter adapter = new HashTagSuggestAdapter(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        adapter.setCursorPositionListener(new HashTagSuggestAdapter.CursorPositionListener() {
            @Override
            public int currentCursorPosition() {
                return textView.getSelectionStart();
            }
        });

        textView.setAdapter(adapter);
    }

    private static final String[] COUNTRIES = new String[]{
            "#Screen1", "#Screen2", "#Screen3", "#Germany", "#Spain"
    };


    private void initiDatetimePicker() {
        // Construct SwitchDateTimePicker
        dateTimeFragment = (SwitchDateTimeDialogFragment) getSupportFragmentManager().findFragmentByTag(TAG_DATETIME_FRAGMENT);
        if (dateTimeFragment == null) {
            dateTimeFragment = SwitchDateTimeDialogFragment.newInstance(
                    getString(R.string.label_datetime_dialog),
                    getString(android.R.string.ok),
                    getString(android.R.string.cancel)
//                    ,getString(R.string.clean) // Optional
            );
        }

        // Optionally define a timezone
        dateTimeFragment.setTimeZone(TimeZone.getDefault());

        // Init format
        final SimpleDateFormat myDateFormat = new SimpleDateFormat("d MMM yyyy HH:mm", java.util.Locale.getDefault());
        // Assign unmodifiable values
        dateTimeFragment.set24HoursMode(false);
        dateTimeFragment.setHighlightAMPMSelection(false);
        dateTimeFragment.setMinimumDateTime(new Date());
        dateTimeFragment.setMaximumDateTime(new GregorianCalendar(2025, Calendar.DECEMBER, 31).getTime());

        // Define new day and month format
        try {
            dateTimeFragment.setSimpleDateMonthAndDayFormat(new SimpleDateFormat("MMMM dd", Locale.getDefault()));
        } catch (SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException e) {
            e.printStackTrace();
        }

        // Set listener for date
        // Or use dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
        dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonWithNeutralClickListener() {
            @Override
            public void onPositiveButtonClick(Date date) {
                if (textView != null)
                    textView.setText(myDateFormat.format(date));
            }

            @Override
            public void onNegativeButtonClick(Date date) {
                // Do nothing
            }

            @Override
            public void onNeutralButtonClick(Date date) {
                // Optional if neutral button does'nt exists
                if (textView != null)
                    textView.setText("");
            }
        });
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, ScreenScheduleActivity.class);
        return intent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_date_tv:
                textView = viewholder.mStartDateTv;
                dateTimeFragment.startAtCalendarView();
                dateTimeFragment.setDefaultDateTime(new Date());
                dateTimeFragment.show(getSupportFragmentManager(), TAG_DATETIME_FRAGMENT);
                break;
            case R.id.end_date_tv:
                textView = viewholder.mEndDateTv;
                dateTimeFragment.startAtCalendarView();
                dateTimeFragment.setDefaultDateTime(new Date());
                dateTimeFragment.show(getSupportFragmentManager(), TAG_DATETIME_FRAGMENT);
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    public class ScreenScheduleViewHolder {
        public TextView mStartDateTv;
        public TextView mEndDateTv;

        public ScreenScheduleViewHolder(View view, View.OnClickListener listener) {
            mStartDateTv = (TextView) view.findViewById(R.id.start_date_tv);
            mEndDateTv = (TextView) view.findViewById(R.id.end_date_tv);

            mStartDateTv.setOnClickListener(listener);
            mEndDateTv.setOnClickListener(listener);
        }
    }


}