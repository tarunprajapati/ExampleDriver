package com.uraal.cab.driver.beanClasses;

import org.json.JSONObject;

import com.uraal.cab.driver.baseClasses.Constants;



public class RydeHistorys {
	
	private String id = "";
	private String time = "";
	private String date = "";
	private String source = "";
	private String destination = "";
	private String status = "";
	private String carType = "";
	private String carName = "";
	private String price = "";

	
	
	public RydeHistorys(JSONObject jsonObject){
		setId(jsonObject.optString(Constants.rydeId));
		setPrice(jsonObject.optString(Constants.billAmout));
		setDate(jsonObject.optString(Constants.date));
		setTime(jsonObject.optString(Constants.time));
		setStatus(jsonObject.optString(Constants.status));
		setCarType(jsonObject.optString(Constants.carType));
		setCarName(jsonObject.optString(Constants.carName));
		
		
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	
	
	


}
