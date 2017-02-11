package com.jungwonlee.bmn_project;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

import java.util.ArrayList;

public class Search extends AppCompatActivity implements LocationListener {

    private GoogleApiClient client;
    private GoogleApiClient client2;
    private TMapView tMapView = null;
    private TMapPoint startPoint = null;
    private TMapPoint endPoint = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        setContentView(R.layout.activity_search);

    }

    //지도 보여주기
    public TMapView ViewMap() {
        tMapView = new TMapView(this);
        tMapView.setSKPMapApiKey("e17e2369-9a7c-3270-b592-4320bbd3b7e6");
        tMapView.setCompassMode(true);
        tMapView.setIconVisibility(true);
        tMapView.setZoomLevel(15);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);

        //현재위치로 초기화
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        double Longitude, Latitude;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
      /*  if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            lm.requestLocationUpdates(lm.GPS_PROVIDER, 1000, 0, this);
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Latitude = location.getLatitude();
            Longitude = location.getLongitude();
        }*/
        //else{
        lm.requestLocationUpdates(lm.NETWORK_PROVIDER, 1000, 0, this);
        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Latitude = location.getLatitude();
        Longitude = location.getLongitude();
        //}
        tMapView.setLocationPoint(Longitude, Latitude);
        tMapView.setTrackingMode(true);
        tMapView.setSightVisible(true);
        //frameLayout.addView(tMapView);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        return tMapView;
    }


    //LocationListener
    @Override
    public void onLocationChanged(Location location) {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        // lm.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    //현재위치로 화면을 옮기는 함수.
    public void CurLoc() {

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        double Longitude, Latitude;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Latitude = location.getLatitude();
        Longitude = location.getLongitude();
        tMapView.setLocationPoint(Longitude, Latitude);
        tMapView.setCenterPoint(Longitude, Latitude);
        tMapView.setTrackingMode(true);
        tMapView.setSightVisible(true);


    }

    //출발지 검색 함수
    public void SearchStartPoint(String point) {
        TMapData tMapData = new TMapData();

        tMapData.findTitlePOI(point, new TMapData.FindTitlePOIListenerCallback() {
            @Override
            public void onFindTitlePOI(ArrayList<TMapPOIItem> arrayList) {
                if (arrayList.isEmpty()) ;
                    //Toast.makeText(, "찾을 수 없습니다!", Toast.LENGTH_LONG).show();
                else {
                    TMapPOIItem tMapPOIItem = arrayList.get(0);
                    double latitude = tMapPOIItem.getPOIPoint().getLatitude();
                    double longtitude = tMapPOIItem.getPOIPoint().getLongitude();
                    tMapView.setLocationPoint(longtitude, latitude);
                    tMapView.setCenterPoint(longtitude, latitude);
                }
            }
        });
        TMapPoint tpoint = tMapView.getLocationPoint();
        startPoint = tpoint;
    }

    //도착지 검색 함수
    public void DesSearch(String Des) {
        TMapData tMapData = new TMapData();
        tMapData.findTitlePOI(Des, new TMapData.FindTitlePOIListenerCallback() {
            @Override
            public void onFindTitlePOI(ArrayList<TMapPOIItem> arrayList) {

                if (arrayList.isEmpty())
                    ;
                else {
                    TMapPOIItem tMapPOIItem = arrayList.get(0);
                    tMapView.setLocationPoint(tMapPOIItem.getPOIPoint().getLongitude(), tMapPOIItem.getPOIPoint().getLatitude());
                    tMapView.setCenterPoint(tMapPOIItem.getPOIPoint().getLongitude(), tMapPOIItem.getPOIPoint().getLatitude());
                }
            }
        });

        TMapPoint tPoint = tMapView.getLocationPoint();
        endPoint = tPoint;
    }
    //경로탐색 시작 함수
    public void PathSearch(){
        TMapData tMapData = new TMapData();
        tMapData.findPathDataWithType(TMapData.TMapPathType.BICYCLE_PATH,startPoint, endPoint, new TMapData.FindPathDataListenerCallback() {
            @Override
            public void onFindPathData(TMapPolyLine tMapPolyLine) {
                tMapView.addTMapPath(tMapPolyLine);
            }
        });
    }


    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Search Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        AppIndex.AppIndexApi.start(client2, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client2, getIndexApiAction());
        client2.disconnect();
    }
}


