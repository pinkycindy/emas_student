package com.pinkycindy.emas_student.data.source.local;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 */

public class SessionAttendances {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "PlanningPref";

    public static final String KEY_ID_ATTENDANCES = "ID";

    public static final String KEY_NAMA_SPOT = "nama";

    public static final String KEY_LOKASI = "lokasi";

    public static final String KEY_CHECKIN = "checkin";

    // Constructor
    public SessionAttendances(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createSessionAttendances(String id, String nama, String lokasi, String checkin) {

        editor.putString(KEY_ID_ATTENDANCES, id);
        editor.putString(KEY_NAMA_SPOT, nama);
        editor.putString(KEY_LOKASI, lokasi);
        editor.putString(KEY_CHECKIN, checkin);
        editor.commit();
    }

    public HashMap<String, String> getStatusDetail() {
        HashMap<String, String> attendances = new HashMap<String, String>();
        attendances.put(KEY_ID_ATTENDANCES, pref.getString(KEY_ID_ATTENDANCES, null));
        attendances.put(KEY_NAMA_SPOT, pref.getString(KEY_NAMA_SPOT, null));
        attendances.put(KEY_LOKASI, pref.getString(KEY_LOKASI, null));
        attendances.put(KEY_CHECKIN, pref.getString(KEY_CHECKIN, null));

        return attendances;
    }

    public void clearSession() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }
}
