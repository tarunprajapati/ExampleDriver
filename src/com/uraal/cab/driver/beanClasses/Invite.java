package com.uraal.cab.driver.beanClasses;

public class Invite {

	
	private String message = "";
	private int status = 0;
	private String id = "";
	private String name = "";
	private String mobileNo = "";
	private boolean isChecked = false;
	
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	@Override
	public String toString() {
		return "Invite [message=" + message + ", status=" + status + ", id="
				+ id + ", name=" + name + ", mobileNo=" + mobileNo
				+ ", isChecked=" + isChecked + "]";
	}
	
	

	
	
}
