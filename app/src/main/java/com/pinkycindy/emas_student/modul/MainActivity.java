package com.pinkycindy.emas_student.modul;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.pinkycindy.emas_student.R;
import com.pinkycindy.emas_student.data.source.local.SessionAttendances;
import com.pinkycindy.emas_student.data.source.local.SessionManager;
import com.pinkycindy.emas_student.modul.main.HomeFragment;
import com.pinkycindy.emas_student.modul.profil.ProfilFragment;
import com.pinkycindy.emas_student.modul.schedule.ScheduleFragment;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    SessionManager session;
    SessionAttendances sessionAttendances;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loadFragment(new HomeFragment());
        // inisialisasi BottomNavigaionView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bn_main);
        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        session = new SessionManager(getApplicationContext());
        sessionAttendances = new SessionAttendances(getApplicationContext());
        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();


        String name = user.get(SessionManager.KEY_NAMA);
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.home_menu:
                fragment = new HomeFragment();
                break;
            case R.id.schedule_menu:
                fragment = new ScheduleFragment();
                break;
            case R.id.account_menu:
                fragment = new ProfilFragment();
                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            logout();
        }

        return true;
    }

    private void setting() {
    }

    private void logout() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Logout")
                .setMessage("Ingin Keluar Dari Aplikasi Ini?")
                .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getActivity(), "Kamu Memilih YES", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                        session.logoutUser();
                        sessionAttendances.clearSession();
                        finish();
                    }
                })
                .setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getActivity(), "Kamu Memilih TIDAK Ingin Keluar", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                }).show();
    }


}
