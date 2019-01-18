package com.displayfort.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.displayfort.app.R;
import com.displayfort.app.model.AdsProfile;

import java.util.ArrayList;


/**
 * Created by husain on 9/6/17.
 */

public class AdsProfileItemListAdapter extends RecyclerView.Adapter<AdsProfileItemListAdapter.ViewHolder> {

    private ArrayList<AdsProfile> finalList = new ArrayList<>();
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mAdsProfileNameTv;
        public TextView mSocietytypeIv;
        public TextView mStatustb;

        public ViewHolder(View view) {
            super(view);
            mAdsProfileNameTv = view.findViewById(R.id.adp_title_tv);
            mSocietytypeIv = view.findViewById(R.id.unique_tv);
            mStatustb = view.findViewById(R.id.status_tv);
        }

    }

    public AdsProfileItemListAdapter(Context context, ArrayList<AdsProfile> searchList) {
        this.finalList = searchList;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_add_media_layout, viewGroup, false);
        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        AdsProfile AdsProfile = finalList.get(position);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return (finalList != null) ? finalList.size() : 0;
    }

    public void setlist(ArrayList<AdsProfile> notificationDaos) {
        this.finalList = notificationDaos;
        notifyDataSetChanged();
    }
}
