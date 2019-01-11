package com.displayfort.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.displayfort.app.R;
import com.displayfort.app.model.PartnersDao;

import java.util.ArrayList;


/**
 * Created by husain on 9/6/17.
 */

public class PartnersListAdapter extends RecyclerView.Adapter<PartnersListAdapter.ViewHolder> {

    private ArrayList<PartnersDao> finalList = new ArrayList<>();
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout mContainerLeft;
        public RelativeLayout mContainerRight;


        public ViewHolder(View view) {
            super(view);
            mContainerLeft = view.findViewById(R.id.container_left_rl);
            mContainerRight = view.findViewById(R.id.container_right_rl);

        }

    }

    public PartnersListAdapter(Context context, ArrayList<PartnersDao> searchList) {
        this.finalList = searchList;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.partners_layout_item, viewGroup, false);


        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        PartnersDao PartnersDao = finalList.get(position);
        if (position % 2 == 0) {
            viewHolder.mContainerLeft.setVisibility(View.VISIBLE);
            viewHolder.mContainerRight.setVisibility(View.GONE);
        } else {
            viewHolder.mContainerLeft.setVisibility(View.GONE);
            viewHolder.mContainerRight.setVisibility(View.VISIBLE);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return (finalList != null) ? finalList.size() : 0;
    }

    public void setlist(ArrayList<PartnersDao> notificationDaos) {
        this.finalList = notificationDaos;
        notifyDataSetChanged();
    }
}
