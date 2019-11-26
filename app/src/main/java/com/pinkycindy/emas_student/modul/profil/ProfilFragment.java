package com.pinkycindy.emas_student.modul.profil;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pinkycindy.emas_student.Base.BaseFragment;
import com.pinkycindy.emas_student.R;
import com.pinkycindy.emas_student.data.model.Student;
import com.pinkycindy.emas_student.data.source.local.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Pinky Cindy
 */
public class ProfilFragment extends BaseFragment implements ProfilContract.view {

    private ProfilContract.presenter presenter;
    SessionManager sessionManager;
    private ImageView ivAvatar;
    private TextView tvName, tvEmail, tvUsername, tvAddress, tvPhone;
    private String empId;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(getContext());
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetails();
        empId = user.get(SessionManager.KEY_ID);
        presenter = new ProfilPresenter(this);


    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        tvName = view.findViewById(R.id.tv_name);
        tvEmail = view.findViewById(R.id.tv_email);
        ivAvatar = view.findViewById(R.id.iv_avatar);
        tvUsername = view.findViewById(R.id.tv_username);
        tvAddress = view.findViewById(R.id.tv_address);
        tvPhone = view.findViewById(R.id.tv_phone);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getProfil(empId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showProfil(ArrayList<Student> employees) {
        if(employees.isEmpty()){

            tvName.setText("-");
            tvEmail.setText("-");
            tvUsername.setText("-");
            tvAddress.setText("-");
            tvPhone.setText("-");
        }
        else{
           tvName.setText(employees.get(0).getName());
            tvEmail.setText(employees.get(0).getEmail());
            tvUsername.setText(employees.get(0).getEmail());
            tvAddress.setText(employees.get(0).getAddress());
            tvPhone.setText(employees.get(0).getPhone());

        }

    }
}