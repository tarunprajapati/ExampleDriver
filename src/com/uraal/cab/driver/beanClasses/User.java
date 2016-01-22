package com.uraal.cab.driver.beanClasses;

import java.io.Serializable;

import android.graphics.Bitmap;

public class User implements Serializable {
	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 8338790464032823428L;
	private String userId="-1";
	private String email = "";
	private String fname = "";
	private String lname = "";
	private String mobileNo = "";
	private String password = "";	
	private int accountType = 0; 
	private String accountTypeStr = "personal"; 
	private String registrationType = "";
	private String gcmId = "";	
	private String loginType = "";
	private String socialId = "";
	private String imgUrl = "";
	private Bitmap bitmap = null;


	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public String getSocialId() {
		return socialId;
	}
	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountTypeStr() {
		return accountTypeStr;
	}
	public void setAccountTypeStr(String accountTypeStr) {
		this.accountTypeStr = accountTypeStr;
	}
	public String getRegistrationType() {
		return registrationType;
	}
	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}
	public String getGcmId() {
		return gcmId;
	}
	public void setGcmId(String gcmId) {
		this.gcmId = gcmId;
	}
	public void setFirstName(String name) {
		this.fname = name;
	}
	public String getPhone() {
		return mobileNo;
	}
	public void setPhone(String phone) {
		this.mobileNo = phone;
	}

	public String getFullName(){
		return (fname+" "+lname).trim();
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public void setFullName(String fullName){
		String[] spl = fullName.split(" ");
		setFirstName(spl[0]);
		if(spl.length > 1)
			setLname(spl[1]);
	}

}
