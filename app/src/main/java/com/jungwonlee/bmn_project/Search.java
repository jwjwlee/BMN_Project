package com.jungwonlee.bmn_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

public class Search extends AppCompatActivity {

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void ViewMap(){
        // RelativeLayout relativeLayout = new RelativeLayout(this);

        FrameLayout frameLayout = new FrameLayout(this);
        TMapView tMapView = new TMapView(this);

        tMapView.setSKPMapApiKey("e17e2369-9a7c-3270-b592-4320bbd3b7e6");
        tMapView.setCompassMode(true);
        tMapView.setIconVisibility(true);
        tMapView.setZoomLevel(15);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        tMapView.setTrackingMode(true);
        tMapView.setSightVisible(true);

        //현재위치로 초기화
        tMapView.setLocationPoint(126.651933, 37.449979);


        frameLayout.addView(tMapView);
        setContentView(frameLayout);
        // relativeLayout.addView(tMapView);
        // setContentView(relativeLayout);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    //현재위치로 화면을 옮기는 함수.
    public void CurLoc(){
        FrameLayout frameLayout = new FrameLayout(this);
        TMapView tMapView = new TMapView(this);
        TMapMarkerItem tItem = new TMapMarkerItem();

        TMapPoint tpoint = tMapView.getLocationPoint();
        double Latitude = tpoint.getLatitude();
        double Longitude = tpoint.getLongitude();
        //tMapView.setLocationPoint(126.651933, 37.449979);
        tItem.setTMapPoint(tpoint);


    }
}
