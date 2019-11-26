package com.pinkycindy.emas_student.modul.login;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.pinkycindy.emas_student.data.model.Classroom;
import com.pinkycindy.emas_student.data.model.Kabupaten;
import com.pinkycindy.emas_student.data.model.Kecamatan;
import com.pinkycindy.emas_student.data.model.Kelurahan;
import com.pinkycindy.emas_student.data.model.Propinsi;
import com.pinkycindy.emas_student.data.model.Student;
import com.pinkycindy.emas_student.data.source.local.SessionAttendances;
import com.pinkycindy.emas_student.data.source.local.SessionManager;
import com.pinkycindy.emas_student.data.source.remote.ApiConnection;
import com.pinkycindy.emas_student.data.source.remote.ApiInterface;
import com.pinkycindy.emas_student.modul.reminder.ReminderActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pinky Cindy
 */
public class LoginPresenter implements LoginContract.presenter {

    private LoginContract.view view;
    SessionManager session ;
    SessionAttendances sessionAttendances;
    Context con;

    Integer id;
    Integer user_id;
    Integer prop_id;
    Integer kab_id;
    Integer kec_id;
    Integer kel_id;
    String name, address, birthday, phone, gender, email, user, avatar;
    ArrayList<Student> emp;
    ArrayList<Classroom> classroom;
    ArrayList<Propinsi> propinsi;
    ArrayList<Kabupaten> kabupaten;
    ArrayList<Kecamatan> kecamatan;
    ArrayList<Kelurahan> kelurahan;


    public LoginPresenter(LoginContract.view view, Context con) {

        this.view = view;
        this.con = con;
        session = new SessionManager(con);
        sessionAttendances = new SessionAttendances(con);

    }
    @Override
    public void requestLogin(String username, String pass) {
        view.showProgressbar();



        ApiInterface apiService = ApiConnection.getClient();
        apiService.login(username, pass)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());

                                emp = new ArrayList<Student>();
                                propinsi = new ArrayList<Propinsi>();
                                kabupaten = new ArrayList<Kabupaten>();
                                kecamatan = new ArrayList<Kecamatan>();
                                kelurahan = new ArrayList<Kelurahan>();
                                classroom = new ArrayList<Classroom>();

                                id = jsonRESULTS.getJSONObject("data").getInt("id");
                                name = jsonRESULTS.getJSONObject("data").getString("name");
                                address = jsonRESULTS.getJSONObject("data").getString("birthday");
                                phone = jsonRESULTS.getJSONObject("data").getString("phone");
                                gender = jsonRESULTS.getJSONObject("data").getString("gender");
                                prop_id = jsonRESULTS.getJSONObject("data").getInt("propinsi_id");
                                kab_id = jsonRESULTS.getJSONObject("data").getInt("kabupaten_id");
                                kec_id = jsonRESULTS.getJSONObject("data").getInt("kecamatan_id");
//                                kel_id = jsonRESULTS.getJSONObject("data").getInt("kelurahan_id");
                                email = jsonRESULTS.getJSONObject("data").getString("email");

                                JSONArray jsProp = jsonRESULTS.getJSONObject("data").getJSONArray("propinsi");
                                int len_jsProp = jsProp.length();
                                for (int i=0; i<len_jsProp; i++){
                                    JSONObject jsonProp = jsProp.getJSONObject(i);
                                    Propinsi propinsiItem = new Propinsi();
                                    propinsiItem.setId(jsonProp.getInt("id"));
                                    propinsiItem.setNama(jsonProp.getString("nama"));
                                    propinsi.add(propinsiItem);

                                }
                                JSONArray jsKab = jsonRESULTS.getJSONObject("data").getJSONArray("kabupaten");
                                int len_jsKab = jsKab.length();
                                for (int i=0; i<len_jsKab; i++){
                                    JSONObject jsonKab = jsKab.getJSONObject(i);
                                    Kabupaten kabupatenItem = new Kabupaten();
                                    kabupatenItem.setId(jsonKab.getInt("id"));
                                    kabupatenItem.setNama(jsonKab.getString("nama"));
                                    kabupaten.add(kabupatenItem);

                                }
                                JSONArray jsKec = jsonRESULTS.getJSONObject("data").getJSONArray("kecamatan");
                                int len_jsKec = jsKec.length();
                                for (int i=0; i<len_jsKec; i++){
                                    JSONObject jsonKec = jsKec.getJSONObject(i);
                                    Kecamatan kecamatanItem = new Kecamatan();
                                    kecamatanItem.setId(jsonKec.getInt("id"));
                                    kecamatanItem.setNama(jsonKec.getString("nama"));
                                    kecamatan.add(kecamatanItem);

                                }
//                                JSONArray jsKel = jsonRESULTS.getJSONObject("data").getJSONArray("kelurahan");
//                                int len_jsKel = jsKel.length();
//                                for (int i=0; i<len_jsKel; i++){
//                                    JSONObject jsonKel = jsKel.getJSONObject(i);
//                                    Kelurahan kelurahanItem = new Kelurahan();
//                                    kelurahanItem.setId(jsonKel.getInt("id"));
//                                    kelurahanItem.setNama(jsonKel.getString("nama"));
//                                    kelurahan.add(kelurahanItem);
//
//                                }
                                JSONArray ja = jsonRESULTS.getJSONObject("data").getJSONArray("student_classrooms");
                                int len = ja.length();
                                for(int j=0; j<len; j++)
                                {
                                    JSONObject json = ja.getJSONObject(j);
                                    Classroom modelClass = new Classroom();
                                    modelClass.setId(json.getInt("id"));
                                    modelClass.setName(json.getString("name"));
                                    modelClass.setEmployeeId(json.getInt("employee_id"));
                                    modelClass.setSpotId(json.getInt("spot_id"));
                                    modelClass.setDescription(json.getString("description"));
                                    modelClass.setDayFirst(json.getInt("day_first"));
                                    modelClass.setHourFirst(json.getString("hour_first"));
                                    modelClass.setDaySecond(json.getInt("day_second"));
                                    modelClass.setHourSecond(json.getString("hour_second"));
                                    modelClass.setTypeClass(json.getString("type_class"));
                                    modelClass.setCapacity(json.getInt("capacity"));
                                    modelClass.setNameSpots(json.getString("name_spots"));
                                    modelClass.setAddress(json.getString("address"));
                                    modelClass.setLat(json.getDouble("lat"));
                                    modelClass.setLng(json.getDouble("lng"));
                                    classroom.add(modelClass);
                                    Log.d("p",json.getString("id").toString());
                                }


                                Student model = new Student();
                                model.setId(id);
                                model.setName(name);
                                model.setAddress(address);
                                model.setBirthday(birthday);
                                model.setPhone(phone);
                                model.setGender(gender);
                                model.setPropinsiId(prop_id);
                                model.setKabupatenId(kab_id);
                                model.setKecamatanId(kec_id);
//                                model.setKelurahanId(kel_id);
                                model.setEmail(email);
                                model.setPropinsi(propinsi);
                                model.setKabupaten(kabupaten);
                                model.setKecamatan(kecamatan);
                                model.setKelurahan(kelurahan);
                                model.setClassrooms(classroom);
                                emp.add(model);
                                Log.d("Login Berhasil :",name);

                                session.createLoginSession(String.valueOf(id), name, user, email);
                                sessionAttendances.createSessionAttendances("0","0" , "0","0");
                                view.hideProgressbar();

                                Intent intent = new Intent(con, ReminderActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("emp", emp);
                                con.startActivity(intent);


                                //Toast.makeText(LoginActivity.this, "Alarm set in " + 15 + " seconds",Toast.LENGTH_LONG).show();


//                                session = new SessionManager(getClass());
//                                session.createLoginSession(id, user, email);
                                //view.respondRequest(id, user, email);
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
                        view.hideProgressbar();
                    }
                });
    }

    private void write(String response) {
        try {

            //getting the whole json object from the response
            JSONObject obj = new JSONObject(response);
//            if(obj.optString("status").equals("true")){

                ArrayList<Student> retroModelArrayList = new ArrayList<>();
                JSONArray dataArray  = obj.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++) {

                    JSONObject dataobj = dataArray.getJSONObject(i);
                    Log.d("id", dataobj.getString("id"));

                }
            view.hideProgressbar();

//            }else {
//                Toast.makeText(MainActivity.this, obj.optString("message")+"", Toast.LENGTH_SHORT).show();
//            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void createSession() {

    }

    @Override
    public void createReminder() {

    }
}
