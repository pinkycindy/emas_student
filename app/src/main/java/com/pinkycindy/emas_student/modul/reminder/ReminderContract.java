package com.pinkycindy.emas_student.modul.reminder;

import com.pinkycindy.emas_student.data.model.Classroom;

import java.util.ArrayList;

/**
 * Created by Pinky Cindy
 */
public interface ReminderContract {

    interface view{
        void showWindow();
        void createAlarm(ArrayList<Classroom> classsroom);

    }

    interface presenter{
        void createalarm(ArrayList<Classroom> classsroom);

    }
}
