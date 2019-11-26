package com.pinkycindy.emas_student.modul.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pinkycindy.emas_student.Base.BaseActivity;
import com.pinkycindy.emas_student.data.model.Classroom;
import com.pinkycindy.emas_student.data.model.Student;
import com.pinkycindy.emas_student.modul.MainActivity;

import java.util.ArrayList;

/**
 * Created by Pinky Cindy
 */
public class ReminderActivity extends BaseActivity implements ReminderContract.view {



    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    private ReminderContract.presenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        presenter = new ReminderPresenter(this, getApplicationContext());


        Intent intent = getIntent();
        ArrayList<Student> data_emp = new ArrayList<Student>();
        data_emp = (ArrayList<Student>) getIntent().getSerializableExtra("emp");

        ArrayList<Classroom> clssemp =  (ArrayList<Classroom>) data_emp.get(0).getClassrooms();
        int lenght = clssemp.size();
        presenter.createalarm(clssemp);
        //cek data

        String name = data_emp.get(0).getName();
        //Log.d("namaa", String.valueOf(lenght));



       //setAlarm();

    }
    @Override
    public void createAlarm(ArrayList<Classroom> classsroom) {

    }

//    private void setAlarm() {
//        Intent intent = new Intent(ReminderActivity.this, ReminderReceiver.class);
//        pendingIntent = PendingIntent.getBroadcast(
//                ReminderActivity.this, 234324243, intent, 0);
//        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
//                + (15 * 1000), pendingIntent);
//        Toast.makeText(ReminderActivity.this, "Alarm set in " + 15 + " seconds",Toast.LENGTH_LONG).show();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showWindow() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);

    }
}
