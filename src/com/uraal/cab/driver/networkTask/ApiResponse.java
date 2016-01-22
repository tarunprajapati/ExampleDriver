package com.uraal.cab.driver.networkTask;

import java.util.HashMap;


public class ApiResponse{
	private int errorCode = -1;
	private String errorMsg = null;
	private HashMap<String, String> responceMap = null;
	private String successMsg = null;
	
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public HashMap<String, String> getResponceMap() {
		return responceMap;
	}
	public void addResponce(String url, String responce) {
		if(responceMap == null)
			responceMap = new HashMap<String, String>();
		
		responceMap.put(url, responce);
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	
}
