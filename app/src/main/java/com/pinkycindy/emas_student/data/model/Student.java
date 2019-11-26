package com.pinkycindy.emas_student.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Pinky Cindy
 */
public class Student implements Parcelable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("address")
    private String address;

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("phone")
    private String phone;

    @SerializedName("gender")
    private String gender;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("propinsi_id")
    private int propinsiId;

    @SerializedName("kabupaten_id")
    private  int kabupatenId;

    @SerializedName("kecamatan_id")
    private int kecamatanId;

    @SerializedName("kelurahan_id")
    private int kelurahanId;

    @SerializedName("cretaed_at")
    private String created_at;

    @SerializedName("update_at")
    private String update_at;

    @SerializedName("propinsi")
    private ArrayList<Propinsi> propinsi;

    @SerializedName("kabupaten")
    private ArrayList<Kabupaten> kabupaten;

    @SerializedName("kelurahan")
    private ArrayList<Kelurahan> kelurahan;

    @SerializedName("kecamatan")
    private ArrayList<Kecamatan> kecamatan;

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(ArrayList<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public static Creator<Student> getCREATOR() {
        return CREATOR;
    }

    @SerializedName("student_classrooms")
    private ArrayList<Classroom> classrooms;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPropinsiId() {
        return propinsiId;
    }

    public void setPropinsiId(int propinsiId) {
        this.propinsiId = propinsiId;
    }

    public int getKabupatenId() {
        return kabupatenId;
    }

    public void setKabupatenId(int kabupatenId) {
        this.kabupatenId = kabupatenId;
    }

    public int getKecamatanId() {
        return kecamatanId;
    }

    public void setKecamatanId(int kecamatanId) {
        this.kecamatanId = kecamatanId;
    }

    public int getKelurahanId() {
        return kelurahanId;
    }

    public void setKelurahanId(int kelurahanId) {
        this.kelurahanId = kelurahanId;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public ArrayList<Propinsi> getPropinsi() {
        return propinsi;
    }

    public void setPropinsi(ArrayList<Propinsi> propinsi) {
        this.propinsi = propinsi;
    }

    public ArrayList<Kabupaten> getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(ArrayList<Kabupaten> kabupaten) {
        this.kabupaten = kabupaten;
    }

    public ArrayList<Kelurahan> getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(ArrayList<Kelurahan> kelurahan) {
        this.kelurahan = kelurahan;
    }

    public ArrayList<Kecamatan> getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(ArrayList<Kecamatan> kecamatanItems) {
        this.kecamatan = kecamatanItems;
    }

    public Student() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeString(this.birthday);
        dest.writeString(this.phone);
        dest.writeString(this.gender);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeInt(this.propinsiId);
        dest.writeInt(this.kabupatenId);
        dest.writeInt(this.kecamatanId);
        dest.writeInt(this.kelurahanId);
        dest.writeString(this.created_at);
        dest.writeString(this.update_at);
        dest.writeTypedList(this.propinsi);
        dest.writeTypedList(this.kabupaten);
        dest.writeTypedList(this.kelurahan);
        dest.writeTypedList(this.kecamatan);
        dest.writeTypedList(this.classrooms);
    }

    protected Student(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.address = in.readString();
        this.birthday = in.readString();
        this.phone = in.readString();
        this.gender = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.propinsiId = in.readInt();
        this.kabupatenId = in.readInt();
        this.kecamatanId = in.readInt();
        this.kelurahanId = in.readInt();
        this.created_at = in.readString();
        this.update_at = in.readString();
        this.propinsi = in.createTypedArrayList(Propinsi.CREATOR);
        this.kabupaten = in.createTypedArrayList(Kabupaten.CREATOR);
        this.kelurahan = in.createTypedArrayList(Kelurahan.CREATOR);
        this.kecamatan = in.createTypedArrayList(Kecamatan.CREATOR);
        this.classrooms = in.createTypedArrayList(Classroom.CREATOR);
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
