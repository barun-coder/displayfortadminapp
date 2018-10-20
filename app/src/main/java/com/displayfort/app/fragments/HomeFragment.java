package com.displayfort.app.fragments;

/**
 * Created by pc on 16/10/2018 15:32.
 * DisplayFortSoftware
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.displayfort.app.R;
import com.displayfort.app.TextUtils.DisplayUtil;
import com.displayfort.app.base.BaseFragment;
import com.displayfort.app.base.Constant;
import com.displayfort.app.model.Friend;
import com.yalantis.flipviewpager.adapter.BaseFlipAdapter;
import com.yalantis.flipviewpager.utils.FlipSettings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class HomeFragment extends BaseFragment {


    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;
    private ListView listView;
    private Context context;

    public static HomeFragment newInstance() {
        HomeFragment contentFragment = new HomeFragment();
        Bundle bundle = new Bundle();
//        bundle.putInt(Integer.class.getName(), resId);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        this.containerView = view.findViewById(R.id.container);
        this.listView = (ListView) view.findViewById(R.id.friends);
        init();
    }

    private void init() {
        FlipSettings settings = new FlipSettings.Builder().defaultPage(1).build();
        listView.setAdapter(new FriendsAdapter(context, Constant.friends, settings));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Friend f = (Friend) listView.getAdapter().getItem(position);

                Toast.makeText(getActivity(), f.getNickname(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getInt(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragmnt, container, false);

        return rootView;
    }

    @Override
    public void takeScreenShot() {
        try {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                            containerView.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    containerView.draw(canvas);
                    HomeFragment.this.bitmap = bitmap;
                }
            };

            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    class FriendsAdapter extends BaseFlipAdapter {

        private final int PAGES = 3;
        private int[] IDS_INTEREST = {R.id.interest_1, R.id.interest_2, R.id.interest_3, R.id.interest_4, R.id.interest_5};

        public FriendsAdapter(Context context, List<Friend> items, FlipSettings settings) {
            super(context, items, settings);
        }


        @Override
        public View getPage(int position, View convertView, ViewGroup parent, Object friend1, Object friend2) {
            final FriendsHolder holder;

            if (convertView == null) {
                holder = new FriendsHolder();
                convertView = getLayoutInflater().inflate(R.layout.friends_merge_page, parent, false);
                holder.viewLL = convertView.findViewById(R.id.view_ll);
                DisplayMetrics displayMetrics = getScreenSize(getActivity());
                int dp = (int) DisplayUtil.convertDpToPixel(1, context);
                int width = (int) ((displayMetrics.widthPixels - dp) / 2);
                holder.leftAvatar = (TextView) convertView.findViewById(R.id.first);
                holder.rightAvatar = (TextView) convertView.findViewById(R.id.second);
                holder.rightLl = convertView.findViewById(R.id.second_ll);
                holder.leftLl = convertView.findViewById(R.id.first_ll);
                holder.leftLl.setLayoutParams(new LinearLayout.LayoutParams(width, width));
                holder.rightLl.setLayoutParams(new LinearLayout.LayoutParams(width, width));
                holder.infoPage = getLayoutInflater().inflate(R.layout.friends_info, parent, false);
                holder.nickName = (TextView) holder.infoPage.findViewById(R.id.nickname);

                for (int id : IDS_INTEREST)
                    holder.interests.add((TextView) holder.infoPage.findViewById(id));

                convertView.setTag(holder);
            } else {
                holder = (FriendsHolder) convertView.getTag();
            }

            switch (position) {
                // Merged page with 2 friends
                case 1:
                    holder.leftAvatar.setText(((Friend) friend1).getNickname());
                    holder.leftAvatar.setCompoundDrawablesWithIntrinsicBounds(0, ((Friend) friend1).getAvatar(), 0, 0);
                    if (friend2 != null) {
                        holder.rightAvatar.setText(((Friend) friend2).getNickname());
                        holder.rightAvatar.setCompoundDrawablesWithIntrinsicBounds(0, ((Friend) friend1).getAvatar(), 0, 0);
                    }
                    break;
                default:
                    fillHolder(holder, position == 0 ? (Friend) friend1 : (Friend) friend2);
                    holder.infoPage.setTag(holder);
                    return holder.infoPage;
            }
            return convertView;
        }

        @Override
        public int getPagesCount() {
            return PAGES;
        }

        private void fillHolder(FriendsHolder holder, Friend friend) {
            if (friend == null)
                return;
            Iterator<TextView> iViews = holder.interests.iterator();
            Iterator<String> iInterests = friend.getInterests().iterator();
            while (iViews.hasNext() && iInterests.hasNext())
                iViews.next().setText(iInterests.next());
            holder.infoPage.setBackgroundColor(getResources().getColor(friend.getBackground()));
            holder.nickName.setText(friend.getNickname());
        }

        class FriendsHolder {
            TextView leftAvatar, rightAvatar;
            LinearLayout rightLl, leftLl;
            View infoPage;

            List<TextView> interests = new ArrayList<>();
            TextView nickName;
            public LinearLayout viewLL;
        }
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}
