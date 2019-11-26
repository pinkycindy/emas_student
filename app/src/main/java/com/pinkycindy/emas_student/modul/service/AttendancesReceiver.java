package com.pinkycindy.emas_student.modul.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import com.pinkycindy.emas_student.data.model.Attendance;
import com.pinkycindy.emas_student.data.model.Spot;
import com.pinkycindy.emas_student.data.source.local.SessionAttendances;
import com.pinkycindy.emas_student.data.source.local.SessionManager;
import com.pinkycindy.emas_student.data.source.remote.ApiConnection;
import com.pinkycindy.emas_student.data.source.remote.ApiInterface;
import com.pinkycindy.emas_student.modul.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pinky Cindy
 */
public class AttendancesReceiver extends BroadcastReceiver {
    MediaPlayer mp;

    private static final int REQUEST_PERMISSIONS = 100;
    boolean boolean_permission;
    SharedPreferences mPref;
    SharedPreferences.Editor medit;
    Double latitude,longitude, lat, lng, distance;
    String student_id, checkin;
    int spot_id, up, id_class;
    ScheduledExecutorService exec ;
    SessionManager session;
    ArrayList<Attendance> attendances ;
    ArrayList<Spot> spot;

    SessionAttendances sessionAttendances;

    int attendances_id, employee_id, classroom_id, spots_id, cek, cek_hasil, attendances_student_id, class_id, studentid;
    Double lat_spot, lng_spot, checkinLat;
    String address, name_class, desc;



    @Override
    public void onReceive(final Context context, Intent intent){

        session = new SessionManager(context);
        sessionAttendances = new SessionAttendances(context);
        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        student_id = user.get(SessionManager.KEY_ID);
        //Create New Intent
        //Log.d("cek every 5 menit", "cek");

        //get location user
        LocationService locationService = new LocationService(context);
        latitude = locationService.getLatitude();
        longitude = locationService.getLongitude();
//        Log.d("latitude", String.valueOf(latitude));
//        Log.d("longitude", String.valueOf(longitude));

        //get location class
        id_class = intent.getIntExtra("id_class", id_class);
        spot_id = intent.getIntExtra("spot_id", 0);
        lat = intent.getDoubleExtra("lat",0);
        lng = intent.getDoubleExtra("lng",0);
//        Log.d("ID class 2 ", id);
//        Log.d("lat 2", String.valueOf(lat));


        Log.d("id_class", String.valueOf(id_class));
        Log.d("spot", String.valueOf(spot_id));
        Log.d("jarak : ", String.valueOf(distance));
        Log.d("absensi tutor", String.valueOf(cek_hasil));
        Log.d("id student",student_id);
        Log.d("attendances_id", String.valueOf(attendances_id));


        //cek absen tutor
        cekTutor(context, id_class);


        //count distance

        //stop when 20 minutes
        exec = Executors.newScheduledThreadPool(1);
        exec.schedule(new Runnable(){
            @Override
            public void run(){

                stop(context);
                Intent i = new Intent(context, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //i.putExtra("up", up);
                context.startActivity(i);

            }
        }, 10, TimeUnit.MINUTES);

    }

    private void cekTutor(final Context con, final int id_class){
        Log.d("id_class", String.valueOf(id_class));
        ApiInterface apiService = ApiConnection.getClient();
        apiService.cekAbsensiTutor(id_class)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Log.d("responses", String.valueOf(response));
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                //attendances tutor
                                attendances_id = jsonRESULTS.getJSONObject("data").getInt("id");
                                Log.d("attendances_id", String.valueOf(attendances_id));


                                distance = countDistance(latitude, longitude, lat, lng);
                                getJarak(latitude, longitude,lat, lng);


                                //cek
                                if(attendances_id>0){
                                    Toast.makeText(con, "Absen sudah dibuka ! Mencari Lokasi!", Toast.LENGTH_SHORT).show();
                                    //stop(context);
                                    // addAttendances(context, emp_id, id, spot_id, lat, lng);
                                    if(distance<15){
                                        Log.d("in radius", "true");
                                        stop(con);
                                        addAttendances(con, student_id, id_class, lat, lng, attendances_id);

                                    }
                                    else{
                                        Log.d("in radius", "false");
//                Toast.makeText(context, "Anda belum di lokasi !", Toast.LENGTH_SHORT).show();
                                    }

                                }
                                else{
                                    Log.d("absensi", "close");
                                    Toast.makeText(con, "Absen tutor belum dibuka !", Toast.LENGTH_SHORT).show();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        else{
                            Log.d("respon", "failed");

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });






    }

    private void addAttendances(final Context con, String student_id, int classroom_id, Double lat, Double lng, int attendances_id) {

        ApiInterface apiService = ApiConnection.getClient();
        apiService.addAttendaces(student_id, classroom_id, lat, lng, attendances_id)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Log.d("responses", String.valueOf(response));
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());

                                attendances = new ArrayList<Attendance>();
                                spot = new ArrayList<Spot>();
                                attendances_student_id = jsonRESULTS.getJSONObject("data").getInt("id");
                                studentid = jsonRESULTS.getJSONObject("data").getInt("student_id");
                                class_id = jsonRESULTS.getJSONObject("data").getInt("classroom_id");
                                checkin = jsonRESULTS.getJSONObject("data").getString("checkin");
                                name_class = jsonRESULTS.getJSONObject("data").getString("class_name");
                                desc = jsonRESULTS.getJSONObject("data").getString("description");


                                Log.d("attendances_student", String.valueOf(attendances_student_id));
                                sessionAttendances.createSessionAttendances(String.valueOf(attendances_student_id),name_class , desc,checkin);


                                Intent in = new Intent(con, MainActivity.class);
                                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                //i.putExtra("up", up);
                                con.startActivity(in);

                                Intent i= new Intent(con, CloseAttendancesService.class);
                                i.putExtra("attendances_id",attendances_student_id);
                                i.putExtra("lat", lat_spot);
                                i.putExtra("lng", lng_spot);
                                con.startService(i);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("debug", "onFailure: ERROR > " + t.toString());
                        }


                });
    }


    private Double countDistance(Double latitude, Double longitude, Double lat, Double lng) {
        Location locationA = new Location("point A");
        //Convert from string to double and then process
        locationA.setLatitude(latitude);
        locationA.setLongitude(longitude);
        Location locationB = new Location("point B");

        locationB.setLatitude(lat);
        locationB.setLongitude(lng);

        //jarak dalam meter
        return Double.valueOf(locationA.distanceTo(locationB)/1000000);

    }

    private void stop(Context context){
        Log.d("stop","yes");
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(context.getApplicationContext(), AttendancesReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context.getApplicationContext(), 12345678, myIntent, 0);
        alarmManager.cancel(pendingIntent);
        exec.shutdownNow();



    }

    public void getJarak(double lat1, double lon1, double lat2, double lon2) {

        final int R = 6371; // Radious of the earth

        Double latDistance = toRad(lat2-lat1);

        Double lonDistance = toRad(lon2-lon1);

        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +

                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *

                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        Double distance = R * c;

        distance=distance/1000;

        Log.d("The distance :", String.valueOf(distance));



    }



    private static Double toRad(Double value) {

        return value * Math.PI / 180;

    }




}