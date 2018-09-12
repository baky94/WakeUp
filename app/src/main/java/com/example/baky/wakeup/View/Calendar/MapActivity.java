package com.example.baky.wakeup.View.Calendar;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.baky.wakeup.R;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapMarkerItem2;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity {

    TMapView tMapView;
    String search;
    TMapData tMapData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        search = this.getIntent().getStringExtra("search");
        Log.d("search",search);
        LinearLayout linearLayoutTMap = (LinearLayout)findViewById(R.id.linearLayoutTMap);
        tMapData = new TMapData();
        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey("fc6bf3b8-6c38-44ac-b106-e95197fc2c49");

        tMapView.setZoomLevel(15);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);

        tMapData.findAllPOI(search, new TMapData.FindAllPOIListenerCallback() {
            @Override
            public void onFindAllPOI(ArrayList<TMapPOIItem> arrayList) {
                //tMapView.addTMapPOIItem(arrayList);
                for(int i=0;i<arrayList.size();i++){
                    TMapPOIItem item = (TMapPOIItem)arrayList.get(i);
                    TMapPoint tMapPoint = item.getPOIPoint();
                    TMapMarkerItem marker = new TMapMarkerItem();
                    marker.setTMapPoint(tMapPoint);
                    marker.setName(item.name);
                    marker.setVisible(TMapMarkerItem.VISIBLE);
                    marker.setCanShowCallout(true);
                    marker.setCalloutTitle(item.name);
                    Drawable drawable = getResources().getDrawable(R.drawable.right_arrow_btn);
                    Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

                    tMapView.addMarkerItem("marker"+i,marker);
                    if(i==0){
                        tMapView.setCenterPoint(item.getPOIPoint().getLongitude(),item.getPOIPoint().getLatitude());
                    }
                }
            }
        });


        tMapView.setOnCalloutRightButtonClickListener(new TMapView.OnCalloutRightButtonClickCallback() {
            @Override
            public void onCalloutRightButton(TMapMarkerItem tMapMarkerItem) {
                Log.d("marker",tMapMarkerItem.latitude+" : "+tMapMarkerItem.longitude);
            }
        });


        linearLayoutTMap.addView(tMapView);
    }
}
