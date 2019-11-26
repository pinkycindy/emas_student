package com.pinkycindy.emas_student.modul.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.LocationListener;

public class AttendancesService extends Service{

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;

    Double lat, lng;
    int id_class, spot_id;
    String provider;
    protected String latitude,longitude;
    protected boolean gps_enabled,network_enabled;

    PendingIntent pendingIntent;
    AlarmManager alarmManager;


    public AttendancesService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("service", "running");



//        AlarmManager alarmMgr = alarmMgr=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
//        Intent alarmIntent = alarmIntent = new Intent("AlarmIntentReceiver");
//        PendingIntent pendingAlarmIntent = pendingAlarmIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, alarmIntent, 0);
//        alarmMgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 5*60*1000, 20*60*1000,  pendingAlarmIntent); //start in 30 secs and rest in 3 mins interval


        //AlarmManager alarmManagers = new AlarmManager;
       //Intent intents = new Intent();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);

         id_class = intent.getIntExtra("id_class",0);
         spot_id = intent.getIntExtra("spot_id",0);
         lat = intent.getDoubleExtra("lat",0);
         lng = intent.getDoubleExtra("lng",0);

//        Log.d("ID class", String.valueOf(id));
//        Log.d("lat", String.valueOf(lat));
//        Log.d("spot", String.valueOf(spot_id));
//

        //cek apa absensi tutor sdh buka setiap 5 menit
        Intent intents = new Intent(getApplicationContext(), AttendancesReceiver.class);
        intents.putExtra("id_class",id_class);
        intents.putExtra("spot_id", spot_id);
        intents.putExtra("lat", lat);
        intents.putExtra("lng", lng);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),12345678,intents,0);
        AlarmManager alarmManagers = (AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE);
        alarmManagers.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 5*1000, 60*1000,  pendingIntent);




        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



}
