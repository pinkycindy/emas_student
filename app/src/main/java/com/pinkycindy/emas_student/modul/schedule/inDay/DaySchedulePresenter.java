package com.pinkycindy.emas_student.modul.schedule.inDay;

import android.annotation.SuppressLint;
import android.util.Log;

import com.pinkycindy.emas_student.data.model.Classroom;
import com.pinkycindy.emas_student.data.model.Spot;
import com.pinkycindy.emas_student.data.source.remote.ApiConnection;
import com.pinkycindy.emas_student.data.source.remote.ApiInterface;

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
@SuppressLint("ValidFragment")
public class DaySchedulePresenter implements DayScheduleContract.presenter {
    private DayScheduleContract.view view;
    ArrayList<Classroom> classroom;

    public DaySchedulePresenter(DayScheduleContract.view view) {
        this.view = view;
    }

    @Override
    public void getSchedule(String id, int day) {

        Log.d("presenter", "run");
        Log.d("day", String.valueOf(day));
        Log.d("empid", id);

        ApiInterface apiService = ApiConnection.getClient();
        apiService.getScheduleinDay(id, day)
                .enqueue(new Callback<ResponseBody>() {

                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Log.d("responses", String.valueOf(response));
                            try {
                                classroom = new ArrayList<Classroom>();
                                Log.d("classroom", String.valueOf(classroom));

                                JSONObject jsonObject = new JSONObject(response.body().string());

                                JSONArray jsonArray = jsonObject.getJSONArray("data");


                                Spot spotList = new Spot();

                                for (int i = 0; i < jsonArray.length(); i++)
                                {
                                    JSONObject json = jsonArray.getJSONObject(i);
                                    Log.d("name",json.getString("name"));

//
                                    Classroom classroomList = new Classroom();
                                    classroomList.setId(json.getInt("id"));
                                    classroomList.setName(json.getString("name"));
                                    classroomList.setSpotId(json.getInt("spot_id"));
                                    classroomList.setDescription(json.getString("description"));
                                    classroomList.setDayFirst(json.getInt("day_first"));
                                    classroomList.setHourFirst(json.getString("hour_first"));
                                    classroomList.setHourSecond(json.getString("hour_second"));
                                    classroomList.setTypeClass(json.getString("type_class"));
//                                    if(String.valueOf(json.getInt("capacity"))== null){
//                                        classroomList.setCapacity(0);
//
//                                    }else{
//                                        classroomList.setCapacity(json.getInt("capacity"));
//                                    }

                                    classroomList.setNameSpots(json.getString("spot_name"));
                                    classroomList.setAddress(json.getString("address"));
                                    classroomList.setLat(json.getDouble("lat"));
                                    classroomList.setLng(json.getDouble("lng"));

                                    classroom.add(classroomList);
//
//
                                }


                                Log.d("classroom", String.valueOf(classroom));

                                view.showSchedule(classroom);


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
