package com.pinkycindy.emas_student.modul.profil;

import com.pinkycindy.emas_student.data.model.Student;

import java.util.ArrayList;

/**
 * Created by Pinky Cindy
 */
public interface ProfilContract {
    interface view{
        void showProfil(ArrayList<Student> employees);
    }

    interface presenter{
        void getProfil(String emp_id);
    }

}
