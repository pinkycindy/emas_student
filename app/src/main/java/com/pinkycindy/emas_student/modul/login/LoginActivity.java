package com.pinkycindy.emas_student.modul.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pinkycindy.emas_student.Base.BaseActivity;
import com.pinkycindy.emas_student.R;
import com.pinkycindy.emas_student.modul.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Pinky Cindy
 */
public class LoginActivity extends BaseActivity implements LoginContract.view {

    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPass;

    private LoginContract.presenter presenter;
    private ProgressDialog progressDialog;
//    SessionManager session;
//    SessionAttendances

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        presenter = new LoginPresenter(this, getApplicationContext());
        progressDialog = new ProgressDialog(LoginActivity.this);
    }
    @OnClick(R.id.btn_login)
    public void onClick(View view){
        String email= etEmail.getText().toString();
        String pass = etPass.getText().toString();
        presenter.requestLogin(email, pass);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showProgressbar() {
        progressDialog.setMessage("Please wait .....");
        progressDialog.show();

    }

    @Override
    public void hideProgressbar() {
        progressDialog.dismiss();

    }

    @Override
    public void respond(String id, String nama, String email) {
        Intent i= new Intent(LoginActivity.this, MainActivity.class);
        LoginActivity.this.startActivity(i);

    }
}
