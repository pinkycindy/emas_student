package com.pinkycindy.emas_student.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Kabupaten implements Parcelable {

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
			"Kabupaten{" +
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

	public Kabupaten() {
	}

	protected Kabupaten(Parcel in) {
		this.nama = in.readString();
		this.id = in.readInt();
	}

	public static final Creator<Kabupaten> CREATOR = new Creator<Kabupaten>() {
		@Override
		public Kabupaten createFromParcel(Parcel source) {
			return new Kabupaten(source);
		}

		@Override
		public Kabupaten[] newArray(int size) {
			return new Kabupaten[size];
		}
	};
}