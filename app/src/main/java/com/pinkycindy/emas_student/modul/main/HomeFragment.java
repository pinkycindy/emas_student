package com.pinkycindy.emas_student.modul.main;

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
public class HomeFragment  extends BaseFragment implements HomeContract.view {

    private SessionAttendances sessionAttendances;
    private TextView status, tvLokasi, tvChcekin, tvSpot;
    private ImageView ivUpload, ivStatistic, ivClassroom;
    private LinearLayout li;
    private SessionManager sessionManager;
    private RecyclerView rc_scedule;
    private HomeContract.presenter presenter;
    private HomeAdapter homeAdapter;

    String empId, attendancesId, attendancesCheckin, attendancesSpot, attendancesLocation;
    int day;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(getActivity());
        presenter = new HomePresenter(this);
        sessionManager = new SessionManager(getActivity());
        sessionAttendances = new SessionAttendances(getContext());

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        day = calendar.get(Calendar.DAY_OF_WEEK);
        //System.out.println(calendar.get(Calendar.DAY_OF_WEEK));

        HashMap<String, String> user = sessionManager.getUserDetails();
        empId = user.get(SessionManager.KEY_ID);

        HashMap<String, String> data = sessionAttendances.getStatusDetail();
        attendancesId = data.get(SessionAttendances.KEY_ID_ATTENDANCES);
        attendancesCheckin = data.get(SessionAttendances.KEY_CHECKIN);
        attendancesSpot = data.get(SessionAttendances.KEY_NAMA_SPOT);
        attendancesLocation = data.get(SessionAttendances.KEY_LOKASI);

        Log.d("session emp id ", empId);
        Log.d("session attendances id ", attendancesId);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        status = view.findViewById(R.id.attendances_status);
        li = view.findViewById(R.id.status);
        rc_scedule = view.findViewById(R.id.recycler_view);
        tvChcekin = view.findViewById(R.id.tv_checkin);
        tvSpot = view.findViewById(R.id.tv_spot);
        tvLokasi = view.findViewById(R.id.tv_location);

        if (attendancesId.equals("0")) {
            status.setText("CLOSE");
            li.setBackgroundColor(getResources().getColor(R.color.yellow));
            tvChcekin.setText("-");
            tvSpot.setText("-");
            tvLokasi.setText("-");
        } else {
            status.setText("OPEN");
            li.setBackgroundColor(getResources().getColor(R.color.blue));
            tvChcekin.setText(attendancesCheckin);
            tvSpot.setText(attendancesSpot);
            tvLokasi.setText(attendancesLocation);
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rc_scedule.setLayoutManager(layoutManager);



//
//        ivClassroom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), ClassroomActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                //i.putExtra("up", up);
//                getActivity().startActivity(i);
//            }
//        });
//
//        ivStatistic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), ReportActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                //i.putExtra("up", up);
//                getActivity().startActivity(i);
//            }
//        });
//        ivUpload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), UploadModulActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                //i.putExtra("up", up);
//                getActivity().startActivity(i);
//            }
//        });


        return view;

    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("run presenter","y");
        presenter.getSchedule(empId, day);


    }

    @Override
    public void showSchedule(ArrayList<Classroom> classrooms, Boolean sukses) {
        Log.d("sukses", String.valueOf(sukses));
        if(sukses) {
            homeAdapter = new HomeAdapter(classrooms, getActivity());
            rc_scedule.setAdapter(homeAdapter);
        }
        else{

        }
    }
}