package com.example.baky.wakeup.Util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlarmService extends Service {
    TMapView tMapView;
    TMapPoint currentPosition;

    TMapGpsManager tMapGpsManager;
    int totalTime;
    int skyValue;
    int ptyValue;

    int flag;

    Location loc;

    private LocationManager mLocationManager;
    private boolean isGpsReceived;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //Service 객체와 Activity간의 데이터 통신을 위한 객체
        //없는경우 return null
        return null;
    }

    @Override
    public void onCreate() {
        //서비스에서 가장 먼저 호출(최초에 한번만)
        super.onCreate();
        Log.d("dddddd", "service create");
        tMapView = new TMapView(this);
        tMapGpsManager = new TMapGpsManager(this);
        tMapGpsManager.setMinTime(1000);
        tMapGpsManager.setMinDistance(5);
        tMapGpsManager.setProvider(TMapGpsManager.NETWORK_PROVIDER);

        tMapView.setSKTMapApiKey("fc6bf3b8-6c38-44ac-b106-e95197fc2c49");

        isGpsReceived = false;
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @SuppressLint("MissingPermission")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //서비스가 호출될 때 마다 실행
        flag = intent.getIntExtra("FLAG",0);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,10,gpsListener);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,3000,10,networkListener);

        while(loc != null){
        }

        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        //서비스가 종료될 때 실행
        super.onDestroy();
    }

    public void updateWithNewLocation(Location location, String provider) {
        if (isGpsReceived) {
            if (LocationManager.GPS_PROVIDER.equals(provider)) {
                loc = location;
                getWeather(loc);
            } else {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                @SuppressLint("MissingPermission")
                long gpsGenTime = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getTime();
                long currentTime = System.currentTimeMillis();

                if((currentTime - gpsGenTime)>20000){
                    loc = location;
                    getWeather(loc);
                    isGpsReceived = false;
                }
            }
        } else {
            loc = location;
            getWeather(loc);
        }
    }

    LocationListener gpsListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            isGpsReceived = true;
            updateWithNewLocation(location,"gps");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    LocationListener networkListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateWithNewLocation(location,"network");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    public void getWeather(Location current){
        LatXLngY weatherPosition = new LatXLngY(current.getLatitude(),current.getLongitude());
        weatherPosition.setXY(0);

        Log.d("dddddd",weatherPosition.getX()+" "+weatherPosition.getY());

        ContentValues cv = new ContentValues();

        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat time = new SimpleDateFormat("HH00");
        Date now = new Date(System.currentTimeMillis());
        now.setHours(now.getHours()-1);
        String getDate = date.format(now);
        String getTime = time.format(now);
        Log.d("dddd time",getTime);


        cv.put("ServiceKey", "9Z9Cxz4TBLaLlbwuwWbqUzO9uNoqjn1B6qwQOqUSPUm8aKZD3Zudkc8at3SoiF75tqBLSAA4FOgUX6izJ9rOVw%3D%3D");
        cv.put("base_date", getDate);
        cv.put("base_time", getTime);
        cv.put("nx", Integer.parseInt(String.valueOf(Math.round(weatherPosition.getX()))));
        cv.put("ny", Integer.parseInt(String.valueOf(Math.round(weatherPosition.getY()))));
        cv.put("numOfRows", 10);
        cv.put("pageNo", 1);
        cv.put("_type","json");


        String url = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib?";
        NetworkTask networkTask = new NetworkTask(url, cv, new OnAsyncCallbackListener<String>() {
            @Override
            public void onSuccess(String object) {
                DataJson weatherResult;
                weatherResult = gotoShared(object);
                for(int i=0;i<weatherResult.items.size();i++){
                    if(weatherResult.items.get(i).category.equals("SKY")){
                        skyValue = weatherResult.items.get(i).obsrValue;
                    } else if(weatherResult.items.get(i).category.equals("PTY")){
                        ptyValue = weatherResult.items.get(i).obsrValue;
                    }
                }
                Intent gpsRe = new Intent(getApplicationContext(),AlarmReceiver.class);
                gpsRe.setAction("AlarmService");
                gpsRe.putExtra("FLAG",0);
                gpsRe.putExtra("sky",skyValue);
                gpsRe.putExtra("pty",ptyValue);
                sendBroadcast(gpsRe);

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        networkTask.execute();

    }

    public DataJson gotoShared(String s){
        //String tmpJson = ApplicationController.getInstance().getJsonString();

        Log.d("ddd","parsing");
        //json파싱부분
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(s);
        JsonObject responseObject = (JsonObject) jsonObject.get("response");
        JsonObject bodyObject = (JsonObject) responseObject.get("body");
        JsonObject itemsObject = (JsonObject) bodyObject.get("items");

        DataJson dataJson = new Gson().fromJson(itemsObject, DataJson.class);
//        for(DataJson.item item1 : dataJson.items){
//            Log.d("itemtitle",item1.title);
//        }
        return dataJson;

    }
}

class NetworkTask extends AsyncTask<Void, Void, String> {
    private String url;
    private ContentValues values;
    private OnAsyncCallbackListener<String> mCallback;
    String whatEver = null;

    public NetworkTask(String url, ContentValues values, OnAsyncCallbackListener<String> callback) {

        this.mCallback = callback;
        this.url = url;
        this.values = values;
    }
    @Override
    protected String doInBackground(Void... voids) {

        String result; // 요청 결과를 저장할 변수.
        RequestActivity requestHttpURLConnection = new RequestActivity();
        result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mCallback.onSuccess(s);
    }
}
