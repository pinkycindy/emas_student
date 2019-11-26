package com.pinkycindy.emas_student.data.source.remote;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Pinky Cindy on 12/26/18.
 */

public interface ApiInterface {

//    @GET("class")
//    Call<ClassroomResponse> getJSON();

    @FormUrlEncoded
    @POST("loginstudent")
    Call<ResponseBody> login(
            @Field("email") String email,
            @Field("password") String pass
    );

    @FormUrlEncoded
    @POST("absenStudent")
    Call<ResponseBody> addAttendaces(
            @Field("student_id") String emp_id,
            @Field("classroom_id") Integer spot_id,
            @Field("checkin_lat") Double lat,
            @Field("checkin_lng") Double lng,
            @Field("attendances_id") Integer id
    );

    @FormUrlEncoded
    @POST("updateAbsenStudent")
    Call<ResponseBody> updateAttendaces(
            @Field("id") Integer id,
            @Field("checkout_lat") Double lat,
            @Field("checkout_lng") Double lng,
            @Field("checkin") String checkin
    );

    @FormUrlEncoded
    @POST("cekAbsensiTutor")
    Call<ResponseBody> cekAbsensiTutor(
            @Field("id_class") Integer id
    );
    @FormUrlEncoded
    @POST("getScheduleInDayStudent")
    Call<ResponseBody> getScheduleinDay(
            @Field("student_id") String student_id,
            @Field("day") Integer day
    );


    @FormUrlEncoded
    @POST("getProfilStudent")
    Call<ResponseBody> getProfil(
            @Field("student_id") String student_id
    );




//    @FormUrlEncoded
//    @POST("getClass")
//    Call<ClassroomResponse> myclass(
//            @Field("id") String id);
//
//    @GET("ClassKosong")
//    Call<ClassroomResponse> dataClass();
//
//    @FormUrlEncoded
//    @POST("pilihclass")
//    Call<ClassroomResponse> pilih(
//            @Field("class_id") String id,
//            @Field("emp_id") String emp);

}
