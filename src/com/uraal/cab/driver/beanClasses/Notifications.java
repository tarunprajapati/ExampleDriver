package com.uraal.cab.driver.beanClasses;

public class Notifications {
	
	private String id = "";
	private String type = "";
	private String icon = "";
	private String message = "";
	private String time = "";
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Notifications [id=" + id + ", type=" + type + ", icon=" + icon
				+ ", message=" + message + ", time=" + time + "]";
	}
	
	

}
