package com.displayfort.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.displayfort.app.R;
import com.displayfort.app.interfaces.OnMediaClick;
import com.displayfort.app.model.MediaDao;
import com.displayfort.app.model.ScreenDao;

import java.util.ArrayList;


/**
 * Created by husain on 9/6/17.
 */

public class MediaListAdapter extends RecyclerView.Adapter<MediaListAdapter.ViewHolder> {

    private final OnMediaClick onMediaClick;
    private final ImageView topIv;
    private ArrayList<MediaDao> finalList = new ArrayList<>();
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mSocietyNameTv;
        public TextView mSocietytypeIv;
        public ImageView mStatustb;
        public RelativeLayout mMediaFolderRl;

        public ViewHolder(View view) {
            super(view);
            mMediaFolderRl = view.findViewById(R.id.media_folder_rl);
            mSocietyNameTv = view.findViewById(R.id.title_tv);
            mSocietytypeIv = view.findViewById(R.id.unique_tv);
            mStatustb = view.findViewById(R.id.status_tb);
        }

    }

    public MediaListAdapter(Context context, ArrayList<MediaDao> searchList, ImageView topIv, OnMediaClick onMediaClick) {
        this.finalList = searchList;
        this.onMediaClick = onMediaClick;
        this.topIv = topIv;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_media_layout, viewGroup, false);
        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final MediaDao mediaDao = finalList.get(position);
        viewHolder.mSocietyNameTv.setText(mediaDao.foldername);
        viewHolder.mMediaFolderRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topIv.getVisibility() != View.VISIBLE) {
                    onMediaClick.onMediaClick(mediaDao);
                } else {
                    viewHolder.mMediaFolderRl.setSelected(!viewHolder.mMediaFolderRl.isSelected());
                    mediaDao.isSelect = !mediaDao.isSelect;
                }
            }
        });
        viewHolder.mMediaFolderRl.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (topIv.getVisibility() != View.VISIBLE) {
                    topIv.setVisibility(View.VISIBLE);
                }
                viewHolder.mMediaFolderRl.setSelected(!viewHolder.mMediaFolderRl.isSelected());
                mediaDao.isSelect = !mediaDao.isSelect;

                return true;
            }
        });
        topIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < finalList.size(); i++) {
                    finalList.get(i).isSelect = false;
                }
                topIv.setVisibility(View.GONE);
                notifyDataSetChanged();
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return (finalList != null) ? finalList.size() : 0;
    }

    public void setlist(ArrayList<MediaDao> notificationDaos) {
        this.finalList = notificationDaos;
        notifyDataSetChanged();
    }

    public ArrayList<MediaDao> getlist() {
        return this.finalList;
    }
}
