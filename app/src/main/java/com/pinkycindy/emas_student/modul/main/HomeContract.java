package com.pinkycindy.emas_student.modul.main;

import com.pinkycindy.emas_student.data.model.Classroom;

import java.util.ArrayList;

/**
 * Created by Pinky Cindy
 */
public interface HomeContract  {
    interface view{
        void showSchedule(ArrayList<Classroom> classrooms, Boolean sukses);

    }

    interface presenter{
        void getSchedule(String id, int day);

    }
}
