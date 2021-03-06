package com.displayfort.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.displayfort.app.R;
import com.displayfort.app.model.ScheduleDao;

import java.util.ArrayList;


/**
 * Created by husain on 9/6/17.
 */

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ViewHolder> {

    private ArrayList<ScheduleDao> finalList = new ArrayList<>();
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mScheduleDaoNameTv;
        public TextView mSocietytypeIv;
        public TextView mStatustb;

        public ViewHolder(View view) {
            super(view);
            mScheduleDaoNameTv = view.findViewById(R.id.adp_title_tv);
            mSocietytypeIv = view.findViewById(R.id.unique_tv);
            mStatustb = view.findViewById(R.id.status_tv);
        }

    }

    public ScheduleListAdapter(Context context, ArrayList<ScheduleDao> searchList) {
        this.finalList = searchList;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_schedule_layout, viewGroup, false);
        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        ScheduleDao ScheduleDao = finalList.get(position);
//        viewHolder.mAdsIv.setSelected((Math.random() < 0.5));
//        this.c1 = a.getInt(com.lightfire.gradienttextcolor.R.styleable.GradientTextView_gradientStart, 0);
//        this.c2 = a.getInt(com.lightfire.gradienttextcolor.R.styleable.GradientTextView_gradientEnd, 0);
//        LinearGradient shader = new LinearGradient(0.0F, 0.0F, 0.0F, this.getTextSize(), new int[]{this.c1, this.c2}, new float[]{0.0F, 1.0F}, Shader.TileMode.CLAMP);
//        this.getPaint().setShader(this.shader);
//        viewHolder.mScheduleDaoNameTv.getPaint().setShader(textShader);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return (finalList != null) ? finalList.size() : 0;
    }

    public void setlist(ArrayList<ScheduleDao> notificationDaos) {
        this.finalList = notificationDaos;
        notifyDataSetChanged();
    }
}
