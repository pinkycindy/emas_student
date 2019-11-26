package com.pinkycindy.emas_student.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Classroom implements Parcelable {

	@SerializedName("day_second")
	private int daySecond;

	@SerializedName("address")
	private String address;

	@SerializedName("lng")
	private Double lng;

	@SerializedName("spot_id")
	private int spotId;

	@SerializedName("day_first")
	private int dayFirst;

	@SerializedName("hour_first")
	private String hourFirst;

	@SerializedName("description")
	private String description;

	@SerializedName("type_class")
	private String typeClass;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("name_spots")
	private String nameSpots;

	@SerializedName("capacity")
	private int capacity;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("employee_id")
	private int employeeId;

	@SerializedName("hour_second")
	private String hourSecond;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("lat")
	private Double lat;

	public void setDaySecond(int daySecond){
		this.daySecond = daySecond;
	}

	public int getDaySecond(){
		return daySecond;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setLng(Double lng){
		this.lng = lng;
	}

	public Double getLng(){
		return lng;
	}

	public void setSpotId(int spotId){
		this.spotId = spotId;
	}

	public int getSpotId(){
		return spotId;
	}

	public void setDayFirst(int dayFirst){
		this.dayFirst = dayFirst;
	}

	public int getDayFirst(){
		return dayFirst;
	}

	public void setHourFirst(String hourFirst){
		this.hourFirst = hourFirst;
	}

	public String getHourFirst(){
		return hourFirst;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setTypeClass(String typeClass){
		this.typeClass = typeClass;
	}

	public String getTypeClass(){
		return typeClass;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setNameSpots(String nameSpots){
		this.nameSpots = nameSpots;
	}

	public String getNameSpots(){
		return nameSpots;
	}

	public void setCapacity(int capacity){
		this.capacity = capacity;
	}

	public int getCapacity(){
		return capacity;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setEmployeeId(int employeeId){
		this.employeeId = employeeId;
	}

	public int getEmployeeId(){
		return employeeId;
	}

	public void setHourSecond(String hourSecond){
		this.hourSecond = hourSecond;
	}

	public String getHourSecond(){
		return hourSecond;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setLat(Double lat){
		this.lat = lat;
	}

	public Double getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"Classroom{" +
			"day_second = '" + daySecond + '\'' + 
			",address = '" + address + '\'' + 
			",lng = '" + lng + '\'' + 
			",spot_id = '" + spotId + '\'' + 
			",day_first = '" + dayFirst + '\'' + 
			",hour_first = '" + hourFirst + '\'' + 
			",description = '" + description + '\'' + 
			",type_class = '" + typeClass + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",name_spots = '" + nameSpots + '\'' + 
			",capacity = '" + capacity + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",employee_id = '" + employeeId + '\'' + 
			",hour_second = '" + hourSecond + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",lat = '" + lat + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.daySecond);
		dest.writeString(this.address);
		dest.writeDouble(this.lng);
		dest.writeInt(this.spotId);
		dest.writeInt(this.dayFirst);
		dest.writeString(this.hourFirst);
		dest.writeString(this.description);
		dest.writeString(this.typeClass);
		dest.writeString(this.createdAt);
		dest.writeString(this.nameSpots);
		dest.writeInt(this.capacity);
		dest.writeString(this.updatedAt);
		dest.writeInt(this.employeeId);
		dest.writeString(this.hourSecond);
		dest.writeString(this.name);
		dest.writeInt(this.id);
		dest.writeDouble(this.lat);
	}

	public Classroom() {
	}

	protected Classroom(Parcel in) {
		this.daySecond = in.readInt();
		this.address = in.readString();
		this.lng = in.readDouble();
		this.spotId = in.readInt();
		this.dayFirst = in.readInt();
		this.hourFirst = in.readString();
		this.description = in.readString();
		this.typeClass = in.readString();
		this.createdAt = in.readString();
		this.nameSpots = in.readString();
		this.capacity = in.readInt();
		this.updatedAt = in.readString();
		this.employeeId = in.readInt();
		this.hourSecond = in.readString();
		this.name = in.readString();
		this.id = in.readInt();
		this.lat = in.readDouble();
	}

	public static final Creator<Classroom> CREATOR = new Creator<Classroom>() {
		@Override
		public Classroom createFromParcel(Parcel source) {
			return new Classroom(source);
		}

		@Override
		public Classroom[] newArray(int size) {
			return new Classroom[size];
		}
	};
}