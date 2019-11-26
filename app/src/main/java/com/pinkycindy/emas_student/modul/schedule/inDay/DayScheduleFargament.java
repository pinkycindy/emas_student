package com.pinkycindy.emas_student.modul.schedule.inDay;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pinkycindy.emas_student.Base.BaseFragment;
import com.pinkycindy.emas_student.R;
import com.pinkycindy.emas_student.data.model.Classroom;
import com.pinkycindy.emas_student.data.source.local.SessionAttendances;
import com.pinkycindy.emas_student.data.source.local.SessionManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import butterknife.ButterKnife;

/**
 * Created by Pinky Cindy
 */
@SuppressLint("ValidFragment")
public class DayScheduleFargament extends BaseFragment implements DayScheduleContract.view {

    private SessionAttendances sessionAttendances;
    private TextView status;
    private ImageView ivUpload, ivStatistic, ivClassroom;
    private LinearLayout li;
    private SessionManager sessionManager;
    private RecyclerView rc_scedule;
    private DayScheduleContract.presenter presenter;
    private DayScheduleAdapter dayScheduleAdapter;

    String empId, attendancesId;

    int day;

    @SuppressLint("ValidFragment")
    public DayScheduleFargament(int i) {
        super();
        this.day = i;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(getActivity());
        presenter = new DaySchedulePresenter(this);
        sessionManager = new SessionManager(getActivity());
        sessionAttendances = new SessionAttendances(getContext());

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        //System.out.println(calendar.get(Calendar.DAY_OF_WEEK));

        HashMap<String, String> user = sessionManager.getUserDetails();
        empId = user.get(SessionManager.KEY_ID);

        HashMap<String, String> data = sessionAttendances.getStatusDetail();
        attendancesId = data.get(SessionAttendances.KEY_ID_ATTENDANCES);

        Log.d("session emp id ", empId);
        Log.d("session attendances id ", attendancesId);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_scheduleinday, container, false);


        rc_scedule = view.findViewById(R.id.rec_schedule);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rc_scedule.setLayoutManager(layoutManager);


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("run presenter","y");
        presenter.getSchedule(empId, day);


    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showSchedule(ArrayList<Classroom> classrooms) {
            dayScheduleAdapter = new DayScheduleAdapter(classrooms, getActivity());
            rc_scedule.setAdapter(dayScheduleAdapter);
           // classrooms.notifyAll();

    }
}
