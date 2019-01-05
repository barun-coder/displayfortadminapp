package com.displayfort.app.screen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TimeUtils;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.displayfort.app.R;
import com.displayfort.app.base.BaseActivity;
import com.displayfort.app.base.BaseAnimation;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.github.sundeepk.compactcalendarview.domain.Event;

public class ScreenViewActivity extends BaseActivity implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener,
        WeekView.EmptyViewLongPressListener, View.OnClickListener, OnMenuItemClickListener, OnMenuItemLongClickListener {

    private static final String TAG = "BaseAc";
    private Context context;
    private ScreenViewViewHolder viewholder;
    private WeekView mWeekView;
    private ContextMenuDialogFragment mMenuDialogFragment;
    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private CalendarView calendarView;
    private CompactCalendarView compactCalendar;
    private ArrayList<Event> eventList;
    private TextView events_tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_view_layout);
        context = this;
        SetToolBar(getIntent().getExtras().getString("SCREEN", "Screen Detail"));
        viewholder = new ScreenViewViewHolder(findViewById(R.id.container_Ll), this);
        init();
    }

    private void init() {
        initToolbar();
        initMenuFragment();
        /**/
        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        // Set first day of week to Monday, defaults to Monday so calling setFirstDayOfWeek is not necessary
        // Use constants provided by Java Calendar class
        compactCalendar.setFirstDayOfWeek(Calendar.MONDAY);

        // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT
        Event ev1 = new Event(Color.GREEN, (System.currentTimeMillis() + (TimeUnit.DAYS.toMillis(2))), "Schedulled  from 3:30 PM to midnight");
        compactCalendar.addEvent(ev1);

        // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT
        Event ev2 = new Event(Color.GREEN, (System.currentTimeMillis() + (TimeUnit.HOURS.toMillis(3))), "Schedulled  from 6:30 AM to midnight");
        compactCalendar.addEvent(ev2);

        // define a listener to receive callbacks when certain events happen.
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendar.getEvents(dateClicked);
                Log.d(TAG, "Day was clicked: " + dateClicked + " with events " + events);
                if (events != null && events.size() > 0) {
                    events_tv.setText("");
                    for (Event event : events) {
                        events_tv.append(event.getData() + "\n");
                    }
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
            }
        });
        /**/
        events_tv = findViewById(R.id.events_tv);
        events_tv.setText("");
        compactCalendar.setVisibility(View.VISIBLE);

        calendarView = findViewById(R.id.calendar_view);
        calendarView.setVisibility(View.GONE);
        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) findViewById(R.id.weekView);
        mWeekView.setVisibility(View.GONE);
        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);

        // Set long press listener for empty view
        mWeekView.setEmptyViewLongPressListener(this);

        // Set up a date time interpreter to interpret how the date and time will be formatted in
        // the week view. This is optional.
        setupDateTimeInterpreter(false);
    }

    private void initToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        TextView mToolBarTextView = findViewById(R.id.txtToolbarTitle);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        mToolbar.setNavigationIcon(null);
        mToolBarTextView.setText("Mega Wall");
    }

    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    private List<MenuObject> getMenuObjects() {
        // You can use any [resource, bitmap, drawable, color] as image:
        // item.setResource(...)
        // item.setBitmap(...)
        // item.setDrawable(...)
        // item.setColor(...)
        // You can set image ScaleType:
        // item.setScaleType(ScaleType.FIT_XY)
        // You can use any [resource, drawable, color] as background:
        // item.setBgResource(...)
        // item.setBgDrawable(...)
        // item.setBgColor(...)
        // You can use any [color] as text color:
        // item.setTextColor(...)
        // You can set any [color] as divider color:
        // item.setDividerColor(...)

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.mipmap.finish);

        MenuObject send = new MenuObject("Today");
        send.setResource(R.mipmap.today);

        MenuObject like = new MenuObject("Day View");
        like.setResource(R.mipmap.day_view);

        MenuObject addFr = new MenuObject("3 Day View");
        addFr.setResource(R.mipmap.three_day);

        MenuObject addFav = new MenuObject("Week View");
        addFav.setResource(R.mipmap.week_view);
        MenuObject calen = new MenuObject("Calendar View");
        calen.setResource(R.mipmap.calendar);


        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        menuObjects.add(calen);

        return menuObjects;
    }


    /**
     * Set up a date time interpreter which will show short date values when in week view and long
     * date values otherwise.
     *
     * @param shortDate True if the date values should be short.
     */
    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }

    protected String getEventTitle(Calendar time) {
        return String.format("Display %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH) + 1, time.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {
        Toast.makeText(this, "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show();
    }

    public WeekView getWeekView() {
        return mWeekView;
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, ScreenViewActivity.class);
        return intent;
    }

//    @Override
//    public boolean onCreateOptionsMenu(final Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                mMenuDialogFragment.show(getSupportFragmentManager(), ContextMenuDialogFragment.TAG);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else {
            finishActivityWithAnim();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.assign_btn: {
                Intent intent = new Intent(context, AssignAdsScreenActivity.class);
                startActivityWithAnim(intent, BaseAnimation.EFFECT_TYPE.TAB_SLIDE_RIGHT);
            }
            break;
            case R.id.schedule_btn: {
                Intent intent = new Intent(context, ScreenScheduleActivity.class);
                startActivityWithAnim(intent, BaseAnimation.EFFECT_TYPE.TAB_SLIDE_RIGHT);

            }
            default:
                super.onClick(v);
                break;
        }
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        eventList = new ArrayList<>();
        compactCalendar.removeAllEvents();
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);

        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 2);
        WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.evenetss));


        if (eventMatches(event, newYear, newMonth)) {
            events.add(event);
            eventList.add(new Event(Color.GREEN, event.getStartTime().getTimeInMillis(), event.getName()));
        }


        //AllDay event
        startTime = Calendar.getInstance();
        startTime.add(Calendar.DAY_OF_MONTH, 2);
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 23);
        event = new WeekViewEvent(7, getEventTitle(startTime), null, startTime, endTime);
        event.setColor(getResources().getColor(R.color.evenetss));
        if (eventMatches(event, newYear, newMonth)) {
            events.add(event);
            eventList.add(new Event(Color.GREEN, event.getStartTime().getTimeInMillis(), event.getName()));
        }
        compactCalendar.addEvents(eventList);
        return events;
    }

    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        switch (position) {
            case 1:
                compactCalendar.setVisibility(View.GONE);
                mWeekView.setVisibility(View.VISIBLE);
                mWeekView.goToToday();
                mWeekView.setNumberOfVisibleDays(1);
                break;
            case 2:
                compactCalendar.setVisibility(View.GONE);
                mWeekView.setVisibility(View.VISIBLE);
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                break;
            case 3:
                compactCalendar.setVisibility(View.GONE);
                mWeekView.setVisibility(View.VISIBLE);
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                break;
            case 4:
                compactCalendar.setVisibility(View.GONE);
                mWeekView.setVisibility(View.VISIBLE);
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    mWeekViewType = TYPE_WEEK_VIEW;
                    mWeekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                break;
            case 5:
                compactCalendar.setVisibility(View.VISIBLE);
                mWeekView.setVisibility(View.GONE);


            {

            }
            break;
        }
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        Toast.makeText(this, "Long clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

    public class ScreenViewViewHolder {
        //        public CalendarView mCalendarView;
        public Button mEnableBtn;
        public Button mAssignBtn;
        public Button mScheduleBtn;

        public ScreenViewViewHolder(View view, View.OnClickListener listener) {
//            mCalendarView = (CalendarView) view.findViewById(R.id.calendar_view);
            mEnableBtn = (Button) view.findViewById(R.id.enable_btn);
            mAssignBtn = (Button) view.findViewById(R.id.assign_btn);
            mScheduleBtn = (Button) view.findViewById(R.id.schedule_btn);
            mEnableBtn.setOnClickListener(listener);
            mAssignBtn.setOnClickListener(listener);
            mScheduleBtn.setOnClickListener(listener);
        }
    }
}