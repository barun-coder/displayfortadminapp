package com.displayfort.app.screen;

/**
 * Created by pc on 17/10/2018 18:17.
 * DisplayFortSoftware
 */

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.displayfort.app.R;
import com.displayfort.app.adapter.ScreenContentAdapter;
import com.displayfort.app.base.BaseActivity;
import com.displayfort.app.base.Constant;
import com.displayfort.app.model.ScreenContentDao;
import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class ScreenDetailActivity extends BaseActivity {

    private ScreenDetailViewHolder screenDetailViewHolder;

    private Context context;
    private RadioGroup radioGroup;
    private int CURRENT_TYPE = Constant.GENERAL;
    private ArrayList<ScreenContentDao> generalList, linkList, locationList;
    private String latlng, street, city, state, country, zipcode;
    private ScreenContentAdapter screengeneralListAdapter, screenlinkListAdapter, screenlocationListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_detail_layout);
        context = this;
        SetToolBar("Screens");
        screenDetailViewHolder = new ScreenDetailViewHolder(findViewById(R.id.container_Ll), this);
        init();
        screenDetailViewHolder.mGeneralLL.setVisibility(View.VISIBLE);
        setAdapter();
    }

    private void setAdapter() {
        screenDetailViewHolder.mGenralRecyclerViewRv.setLayoutManager(new LinearLayoutManager(context));
        generalList = getList(Constant.GENERAL);
        screengeneralListAdapter = new ScreenContentAdapter(context, generalList);
        screenDetailViewHolder.mGenralRecyclerViewRv.setAdapter(screengeneralListAdapter);
        /**/
        screenDetailViewHolder.mLinkRecyclerViewRv.setLayoutManager(new LinearLayoutManager(context));
        linkList = getList(Constant.LINK);
        screenlinkListAdapter = new ScreenContentAdapter(context, linkList);
        screenDetailViewHolder.mLinkRecyclerViewRv.setAdapter(screenlinkListAdapter);
        /**/
        screenDetailViewHolder.mLocationRecyclerViewRv.setLayoutManager(new LinearLayoutManager(context));
        locationList = getList(Constant.LOCATION);
        screenlocationListAdapter = new ScreenContentAdapter(context, locationList);
        screenDetailViewHolder.mLocationRecyclerViewRv.setAdapter(screenlocationListAdapter);

    }

    private ArrayList<ScreenContentDao> getList(int type) {
        ArrayList<ScreenContentDao> ScreenDaos = new ArrayList<>();
        if (type == Constant.GENERAL) {
            ScreenDaos.add(new ScreenContentDao("Screen Name", "Google Island"));
            ScreenDaos.add(new ScreenContentDao("VPN", "NA"));
            ScreenDaos.add(new ScreenContentDao("UniqueId", "12:25:65:84"));
            ScreenDaos.add(new ScreenContentDao("Data Card", "1452365874521"));
            ScreenDaos.add(new ScreenContentDao("Hash Tags", "#Google"));
            ScreenDaos.add(new ScreenContentDao("Expiry", "NA"));

        } else if (type == Constant.LINK) {
            ScreenDaos.add(new ScreenContentDao("Partners", "sundar pichai"));
            ScreenDaos.add(new ScreenContentDao("Age Group", "18-40"));
            ScreenDaos.add(new ScreenContentDao("Size", "55"));
            ScreenDaos.add(new ScreenContentDao("Resolution", "1080x1920"));
        } else {
            ScreenDaos.add(new ScreenContentDao("Lat.Lng", latlng));
            ScreenDaos.add(new ScreenContentDao("Street", street));
            ScreenDaos.add(new ScreenContentDao("City", city));
            ScreenDaos.add(new ScreenContentDao("State", state));
            ScreenDaos.add(new ScreenContentDao("Country", country));
            ScreenDaos.add(new ScreenContentDao("Zipcode", zipcode));
        }
        return ScreenDaos;
    }


    private void init() {
        latlng = "40.847256, -122.423481";
        street = "45 Spear St";
        city = "San Francisco";
        state = "CA";
        country = "USA";
        zipcode = "94105";
        radioGroup = (RadioGroup) findViewById(R.id.payment_option_rg);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                screenDetailViewHolder.mGeneralLL.setVisibility(View.GONE);
                screenDetailViewHolder.mLinkLl.setVisibility(View.GONE);
                screenDetailViewHolder.mLocationLl.setVisibility(View.GONE);
                if (checkedId == R.id.general_rb) {
                    CURRENT_TYPE = Constant.GENERAL;
                    screenDetailViewHolder.mGeneralLL.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.link_rb) {
                    CURRENT_TYPE = Constant.LINK;
                    screenDetailViewHolder.mLinkLl.setVisibility(View.VISIBLE);
                } else {
                    CURRENT_TYPE = Constant.LOCATION;
                    screenDetailViewHolder.mLocationLl.setVisibility(View.VISIBLE);
                }


            }
        });
    }


    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void RequestPermission(String permision, int code) {
        ActivityCompat.requestPermissions(this,
                new String[]{permision},
                code);
        mayRequestContacts(permision, code);
    }

    private boolean mayRequestContacts(final String permision, final int mRequestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(permision) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(permision)) {
            Snackbar.make(screenDetailViewHolder.hexaImageIv, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{permision}, mRequestCode);
                        }
                    });
        } else {
            requestPermissions(new String[]{permision}, mRequestCode);
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 102: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    EasyImage.openChooserWithGallery(this, "Choose Source", 0);
                } else {
                    Toast.makeText(context, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hexaImage_iv: {
                if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                    if (!checkIfAlreadyhavePermission()) {
                        RequestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 102);
                    } else {
                        //EasyImage.openChooserWithGallery(Activity activity, String chooserTitle, int type)
                        EasyImage.openChooserWithGallery(this, "Pick orderID", 0);
                    }
                }
            }
            break;
            case R.id.general_edit_btn: {
                Intent intent = new Intent(this, UpdateGeneralActivity.class);
                startActivityWithAnim(intent);
            }
            break;
            case R.id.link_edit_btn: {
                Intent intent = new Intent(this, UpdateLinkDetailActivity.class);
                startActivityWithAnim(intent);
            }
            break;
            case R.id.location_edit_btn:
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(ScreenDetailActivity.this), 203);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            default:
                super.onClick(v);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 203) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                Geocoder geocoder = new Geocoder(this);
                try {
                    List<Address> addresses = geocoder.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1);

                    city = addresses.get(0).getAddressLine(1) + "";
                    state = place.getAddress() + "";
                    country = place.getLocale().getCountry();
                    zipcode = addresses.get(0).getLocality();

                } catch (IOException e) {
                    e.printStackTrace();
                    city = "City";
                    state = "State";
                    country = "Country";
                    zipcode = "ZIPCODE";
                }

                latlng = place.getLatLng().latitude + "/" + place.getLatLng().longitude;
                street = place.getAddress() + "";
                locationList = getList(Constant.LOCATION);
                screenlocationListAdapter.setlist(locationList);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
            EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
                @Override
                public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                    //Some error handling
                }

                @Override
                public void onImagesPicked(List<File> imageFiles, EasyImage.ImageSource source, int type) {
                    for (File imageFile : imageFiles) {
                        //Handle the images
                        File imgFile = imageFile;
                        if (imgFile.exists()) {
                            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                            screenDetailViewHolder.hexaImageIv.setImageBitmap(myBitmap);
                        }
                    }
                }
            });
        }
    }

    public class ScreenDetailViewHolder {
        private final RecyclerView mGenralRecyclerViewRv, mLinkRecyclerViewRv, mLocationRecyclerViewRv;
        public Button mGeneralEditBtn, mLinkEditBtn, mLocationEditBtn;
        public LinearLayout mGeneralLL, mLinkLl, mLocationLl;
        private ImageView hexaImageIv;

        public ScreenDetailViewHolder(View view, View.OnClickListener listener) {

            mGeneralLL = view.findViewById(R.id.general_ll);
            mLinkLl = view.findViewById(R.id.link_ll);
            mLocationLl = view.findViewById(R.id.location_ll);

            mGeneralEditBtn = view.findViewById(R.id.general_edit_btn);
            mLinkEditBtn = view.findViewById(R.id.link_edit_btn);
            mLocationEditBtn = view.findViewById(R.id.location_edit_btn);

            mGeneralEditBtn.setOnClickListener(listener);
            mLinkEditBtn.setOnClickListener(listener);
            mLocationEditBtn.setOnClickListener(listener);

            mGeneralLL.setVisibility(View.VISIBLE);
            mLinkLl.setVisibility(View.GONE);
            mLocationLl.setVisibility(View.GONE);

            mGenralRecyclerViewRv = view.findViewById(R.id.general_recyclerView_rv);
            mLinkRecyclerViewRv = view.findViewById(R.id.link_recyclerView_rv);
            mLocationRecyclerViewRv = view.findViewById(R.id.location_recyclerView_rv);

            hexaImageIv = view.findViewById(R.id.hexaImage_iv);
            hexaImageIv.setOnClickListener(listener);
        }
    }


}