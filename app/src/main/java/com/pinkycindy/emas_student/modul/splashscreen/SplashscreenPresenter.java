package com.pinkycindy.emas_student.modul.splashscreen;

import android.content.Context;

import com.pinkycindy.emas_student.data.source.local.SessionManager;

/**
 * Created by Pinky Cindy
 */
public class SplashscreenPresenter implements SplashscreenContract.presenter {

    private SplashscreenContract.view view;
    SessionManager session ;
    Context con;
    int cek;

    public SplashscreenPresenter(SplashscreenContract.view view, Context con) {

        this.view = view;
        this.con = con;
        session = new SessionManager(con);

    }
    @Override
    public void cekSession() {
        if(session.isLoggedIn()) {
            cek = 1;
        }else{
           cek = 0;
        }
        view.movectivity(cek);
    }
}
