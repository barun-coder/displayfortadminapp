package com.displayfort.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.displayfort.app.R;
import com.displayfort.app.model.ScreenContentDao;

import java.util.ArrayList;


/**
 * Created by husain on 9/6/17.
 */

public class ScreenContentAdapter extends RecyclerView.Adapter<ScreenContentAdapter.ViewHolder> {

    private ArrayList<ScreenContentDao> finalList = new ArrayList<>();
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNameTv;
        public TextView mNameValueTv;

        public ViewHolder(View view) {
            super(view);
            mNameTv = view.findViewById(R.id.screen_name_title);
            mNameValueTv = view.findViewById(R.id.screen_name_tv);
        }

    }

    public ScreenContentAdapter(Context context, ArrayList<ScreenContentDao> searchList) {
        this.finalList = searchList;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_view, viewGroup, false);

        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        ScreenContentDao screenContentDao = finalList.get(position);
        viewHolder.mNameTv.setText(screenContentDao.title);
        viewHolder.mNameValueTv.setText(screenContentDao.name);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return (finalList != null) ? finalList.size() : 0;
    }

    public void setlist(ArrayList<ScreenContentDao> notificationDaos) {
        this.finalList = notificationDaos;
        notifyDataSetChanged();
    }
}
