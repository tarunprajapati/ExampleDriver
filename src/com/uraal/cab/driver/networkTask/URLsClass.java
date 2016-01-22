package com.uraal.cab.driver.networkTask;

public interface URLsClass {
	public final String reverseGeoCodingApi = "http://maps.googleapis.com/maps/api/geocode/json";
	public final String address = "address";
	public final String sensor= "sensor";

	public final static String baseUrl = "http://resellersquare.com/uraal/webservices/";


	public final static String googleAppUrl = "https://play.google.com/store/apps/details?hl=en&id=";

	//Redirect url required userId,packageName
	public final static String redirectUrl = "192.168.1.186/tisaadmingapps/UserWebservices/playStoreLink/packageName:7d69536ebd893f7202b9664d3ae348cc30777e99e03c17611e0917850ee88d2c";

	//Tearm and condition url
	public final static String tearmAndCondition = "http://resellersquare.com/uraal/terms-and-conditions";
	public final static String privacyPolicy = "http://resellersquare.com/uraal/privacy-policy";
	public final static String openSourceLicenses = "http://resellersquare.com/uraal/open-source-licenses";

	// Service Type
	public final static String service_type_isValidUser = baseUrl+"isValidUser";
	public final static String service_type_register = baseUrl+"user_signup";
	public final static String service_type_updateProfile = baseUrl+"updateProfile";
	
	public final static String service_type_login = baseUrl+"user_login";
	public final static String service_type_getHistory = baseUrl+"getHistory";
	public final String service_type_getPayroll = baseUrl+"getPayroll";
	public final static String service_type_forget_pass = "forgotpassword";
	public final String service_type_logout = baseUrl+"logout";

	//API KEY
	public final static String API_KEY_VALUE = "EFH127FL";

	//Device Type


	public final String and = "&";	

	public final static int POST = 1;
	public final static int GET = 2;

	public final static String status = "status";
	public final static String results = "response";
	public final static int OK = 1;
	public final static int NOTOK = 0;

	public final int fromAddressToLatLng = 101;
	



	public final static String p_action = "action";
	public final static String p_flag = "flag";
	public final static String p_user_name = "user_name";
	public final static String p_flag_profile_image_url = "profile_image_url";
	public final static String p_flag_gid = "gid";
	public final static String p_deviceid_uper = "deviceId";
	public final static String p_deviceType = "deviceType";
	public final static String p_email = "email";
	public final static String p_flag_GPLUS = "GPLUS";
	public final static String p_flag_TWITTER = "TW";
}
