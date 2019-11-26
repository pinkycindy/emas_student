package com.pinkycindy.emas_student.modul.profil;

import android.util.Log;

import com.pinkycindy.emas_student.data.model.Student;
import com.pinkycindy.emas_student.data.source.remote.ApiConnection;
import com.pinkycindy.emas_student.data.source.remote.ApiInterface;

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
public class ProfilPresenter implements ProfilContract.presenter {

    ProfilContract.view view;
    ArrayList<Student> employees;


    public ProfilPresenter(ProfilContract.view view) {
        this.view = view;
    }

    @Override
    public void getProfil(String emp_id) {
        Log.d("empId", emp_id);
        ApiInterface apiService = ApiConnection.getClient();
        apiService.getProfil(emp_id)
                .enqueue(new Callback<ResponseBody>() {

                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Log.d("responses", String.valueOf(response));
                            try {
                                employees = new ArrayList<Student>();
                                Log.d("employee", String.valueOf(employees));

                                JSONObject jsonObject = new JSONObject(response.body().string());

                                    Student employeelist = new Student();
                                    employeelist.setId(jsonObject.getJSONObject("data").getInt("id"));
                                    employeelist.setName(jsonObject.getJSONObject("data").getString("name"));
                                    employeelist.setEmail(jsonObject.getJSONObject("data").getString("email"));
                                    employeelist.setAddress(jsonObject.getJSONObject("data").getString("address"));
                                    employeelist.setPhone(jsonObject.getJSONObject("data").getString("phone"));
                                    employees.add(employeelist);



                                Log.d("attendances", String.valueOf(employees));

                                view.showProfil(employees);


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
}
