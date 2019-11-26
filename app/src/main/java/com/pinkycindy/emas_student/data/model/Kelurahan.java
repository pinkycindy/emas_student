package com.pinkycindy.emas_student.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Kelurahan implements Parcelable {

	@SerializedName("nama")
	private String nama;

	@SerializedName("id")
	private int id;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Kelurahan{" +
			"nama = '" + nama + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.nama);
		dest.writeInt(this.id);
	}

	public Kelurahan() {
	}

	protected Kelurahan(Parcel in) {
		this.nama = in.readString();
		this.id = in.readInt();
	}

	public static final Creator<Kelurahan> CREATOR = new Creator<Kelurahan>() {
		@Override
		public Kelurahan createFromParcel(Parcel source) {
			return new Kelurahan(source);
		}

		@Override
		public Kelurahan[] newArray(int size) {
			return new Kelurahan[size];
		}
	};
}