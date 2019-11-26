package com.pinkycindy.emas_student.modul.schedule.inDay;

import com.pinkycindy.emas_student.data.model.Classroom;

import java.util.ArrayList;

/**
 * Created by Pinky Cindy
 */

public interface DayScheduleContract {

    interface view{
        void showSchedule(ArrayList<Classroom> classrooms);

    }

    interface presenter{
        void getSchedule(String id, int day);

    }

}
