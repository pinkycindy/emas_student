package com.pinkycindy.emas_student.modul.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class CloseAttendancesService extends Service {

    int attendances_id, employee_id, classroom_id, spots_id, student_id;
    Double lat_spot, lng_spot, checkinLat;
    String checkin;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("service :", "run");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       // return super.onStartCommand(intent, flags, startId);



        attendances_id= intent.getIntExtra("attendances_id",0);
        lat_spot = intent.getDoubleExtra("lat",0);
        lng_spot = intent.getDoubleExtra("lng",0);

        Log.d("ID Absen", String.valueOf(attendances_id));
        Log.d("lat", String.valueOf(lat_spot));


        Intent i = new Intent(getApplicationContext(), CloseAttendancesReceiver.class);
        i.putExtra("attendances_id",attendances_id);
        i.putExtra("lat", lat_spot);
        i.putExtra("lng", lng_spot);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),9876,i,0);
        AlarmManager alarmManagers = (AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE);
        alarmManagers.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 60*1000, 60*1000,  pendingIntent);

        return START_STICKY;


    }

    public CloseAttendancesService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
