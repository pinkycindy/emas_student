package com.pinkycindy.emas_student.modul.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.pinkycindy.emas_student.data.model.Classroom;
import com.pinkycindy.emas_student.data.source.local.SessionManager;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by Pinky Cindy
 */
public class ReminderPresenter implements ReminderContract.presenter{

    private ReminderContract.view view;
    SessionManager session ;
    Context con;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;


    public ReminderPresenter(ReminderContract.view view, Context con) {
        this.view = view;
        this.con = con;
    }


    @Override
    public void createalarm(ArrayList<Classroom> classsroom) {

        int lenght = classsroom.size();
        int lngalarm = lenght*2;


        AlarmManager[] alarmManagers = new AlarmManager[lngalarm];
        Intent intents[] = new Intent[alarmManagers.length];
        for(int i=0;i<lenght;i++){
            int day1 = classsroom.get(i).getDayFirst();
            String hour1 = classsroom.get(i).getHourFirst();
            String[] kata = hour1.split(":");
            String jam1 = kata[0];
            String menit1 = kata[1];
            int spotid = classsroom.get(i).getSpotId();
            intents[i] = new Intent(con, ReminderReceiver.class);
            intents[i].putExtra("id",classsroom.get(i).getId());
            intents[i].putExtra("spot_id", classsroom.get(i).getSpotId());
            Log.d("spot id", String.valueOf(spotid));
            intents[i].putExtra("lat", classsroom.get(i).getLat());
            intents[i].putExtra("lng", classsroom.get(i).getLng());
//            intents[i].putExtra("Alarm_no", i);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(con,i,intents[i],0);
            Calendar cal=Calendar.getInstance();
            cal.set(Calendar.DAY_OF_WEEK, day1);
            cal.set(Calendar.HOUR_OF_DAY,6);
            cal.set(Calendar.MINUTE, 28);
            cal.set(Calendar.SECOND, 0);

          //  Log.d("cal lama", cal.getTime().toString());
            if(cal.getTimeInMillis() < System.currentTimeMillis()) {
                cal.add(Calendar.DAY_OF_YEAR, 7);
            }
            Log.d("Jadwal", cal.getTime().toString());

            alarmManagers[i] = (AlarmManager)con.getSystemService(ALARM_SERVICE);
            alarmManagers[i].set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),pendingIntent);

        }

        for(int i=lenght;i<lngalarm;i++){
            int day2 = classsroom.get(i-lenght).getDayFirst();
            String hour2 = classsroom.get(i-lenght).getHourFirst();
            String[] kata = hour2.split(":");
            String jam2 = kata[0];
            String menit2 = kata[1];
            int spotid = classsroom.get(i-lenght).getSpotId();
            intents[i] = new Intent(con, ReminderReceiver.class);
            intents[i].putExtra("id",classsroom.get(i-lenght).getId());
            intents[i].putExtra("spot_id", classsroom.get(i-lenght).getSpotId());
            Log.d("spot id", String.valueOf(spotid));
            intents[i].putExtra("lat", classsroom.get(i-lenght).getLat());
            intents[i].putExtra("lng", classsroom.get(i-lenght).getLng());
//            intents[i].putExtra("Alarm_no", i);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(con,i,intents[i],0);
            Calendar cal=Calendar.getInstance();
            cal.set(Calendar.DAY_OF_WEEK, day2);
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(jam2));
            cal.set(Calendar.MINUTE, Integer.parseInt(menit2));
            cal.set(Calendar.SECOND, 0);

            //  Log.d("cal lama", cal.getTime().toString());
            if(cal.getTimeInMillis() < System.currentTimeMillis()) {
                cal.add(Calendar.DAY_OF_YEAR, 7);
            }
            Log.d("Jadwal", cal.getTime().toString());

            alarmManagers[i] = (AlarmManager)con.getSystemService(ALARM_SERVICE);
            alarmManagers[i].set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),pendingIntent);

        }
        view.showWindow();
    }
}
