
package com.uraal.cab.driver.networkTask;

import java.util.HashMap;

import com.uraal.cab.driver.baseClasses.BaseFragment;
import com.uraal.cab.driver.baseClasses.BaseFragmentActivity;
import com.uraal.cab.driver.baseClasses.Constants;
import com.uraal.cab.driver.beanClasses.User;
import com.uraal.cab.driver.preference.MySharedPreferences;
import com.uraal.cab.driver.utilities.UtilsClass;


public class ApiManager implements URLsClass {

	private static ApiManager apiManager = null;
	public static ApiManager getInstance(){
		if(apiManager == null)
			apiManager = new ApiManager();

		return apiManager;
	}

	public void isValidUser(BaseFragmentActivity activity){
		if(UtilsClass.isConnectingToInternet(activity)){	
			String urlsArr[] = {URLsClass.service_type_isValidUser};
			HashMap<String, String> params = new HashMap<String, String>();
			params.put(Constants.API_KEY, Constants.API_KEY_VALUE);
			params.put(Constants.userId, MySharedPreferences.getInstance().getString(activity, Constants.userId, ""));
			params.put(Constants.deviceId, UtilsClass.getDeviceId(activity));
			activity.serviceCaller(activity, urlsArr, params, true, false, Constants.isValid, true);
		}else {
			UtilsClass.plsStartInternet(activity);			
		}
	}

	/*	public void login(BaseFragmentActivity activity, String email, String pass, String registrationId, String loginType){
		String[] url = {URLsClass.service_type_login};
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(Constants.API_KEY, Constants.API_KEY_VALUE);
		params.put(Constants.EMAIL, email);
		params.put(Constants.password, pass);		
		params.put(Constants.deviceId, UtilsClass.getDeviceId(activity));
		//params.put(Constants.p_device_brand, UtilsClass.getDeviceModel(activity));
		//params.put(Constants.p_device_type, Constants.android);
		//params.put(Constants.p_device_os, UtilsClass.getDeviceSDKVersion(activity));//
		params.put(Constants.gcmId, registrationId);
		params.put(Constants.LOGIN_TYPE, loginType);
		activity.serviceCaller(activity, url, params, true, false, Constants.login, true);
	}*/

	/*public void login(BaseFragmentActivity activity, String email, String  socialId, String pass, String loginType, String gcmId){
		String[] url = {URLsClass.service_type_login};
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(Constants.API_KEY, Constants.API_KEY_VALUE);
		params.put(Constants.emailId, email);
		params.put(Constants.socialId, socialId);		
		params.put(Constants.password, pass);
		params.put(Constants.loginType, loginType);
		params.put(Constants.deviceId, UtilsClass.getDeviceId(activity));
		params.put(Constants.deviceType, Constants.android);
		params.put(Constants.gcmId, gcmId);
		//params.put(Constants.p_device_brand, UtilsClass.getDeviceModel(activity));
		//params.put(Constants.p_device_os, UtilsClass.getDeviceSDKVersion(activity));//
		activity.serviceCaller(activity, url, params, true, false, Constants.login, true);
	}*/

	public void login(BaseFragmentActivity activity, User user){
		String[] url = {URLsClass.service_type_login};
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(Constants.API_KEY, Constants.API_KEY_VALUE);
		params.put(Constants.emailId, user.getEmail());
		params.put(Constants.socialId, user.getSocialId());		
		params.put(Constants.password, user.getPassword());
		params.put(Constants.loginType, user.getLoginType());
		params.put(Constants.deviceId, UtilsClass.getDeviceId(activity));
		params.put(Constants.deviceType, Constants.androidOS);
		params.put(Constants.gcmId, user.getGcmId());
		//params.put(Constants.p_device_brand, UtilsClass.getDeviceModel(activity));
		//params.put(Constants.p_device_os, UtilsClass.getDeviceSDKVersion(activity));//
		activity.serviceCaller(activity, url, params, true, false, Constants.login, true);
	}

	/*public void signUp(BaseFragmentActivity activity, HashMap<String, String> params, String fullName, String phone, String email, String loginTypeFb, String accounType, String registrationId, String socialId, String password, String registrationType){
		String[] sptName = fullName.split(" ");
		String[] url = {URLsClass.service_type_register};	
		//HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(Constants.API_KEY, Constants.API_KEY_VALUE);
		params.put(Constants.emailId,  email);
		params.put(Constants.socialId,  socialId);
		params.put(Constants.p_first_name, sptName[0]);
		if(sptName.length > 1)
			params.put(Constants.p_last_name, sptName[1]);
		else
			params.put(Constants.p_last_name, "");
		params.put(Constants.mobileNo,  phone);
		params.put(Constants.password,  password);
		params.put(Constants.accountType, accounType);
		params.put(Constants.registrationType, registrationType);
		params.put(Constants.deviceId, UtilsClass.getDeviceId(activity));
		params.put(Constants.deviceType, Constants.android);
		params.put(Constants.gcmId, registrationId);		
		params.put(Constants.COUNTRY_CODE,  MySharedPreferences.getInstance().getString(activity, Constants.COUNTRY_CODE, ""));





		//params.put(Constants.p_device_brand, UtilsClass.getDeviceModel(this));
		//params.put(Constants.p_device_type, Constants.android);
		//params.put(Constants.p_device_os, UtilsClass.getDeviceSDKVersion(this));


		activity.serviceCaller(activity, url, params, true, false, Constants.signup, true);
	}*/
	public void signUp(BaseFragmentActivity activity, User user){
		String[] url = {URLsClass.service_type_register};	
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(Constants.API_KEY, Constants.API_KEY_VALUE);
		params.put(Constants.emailId,  user.getEmail());
		params.put(Constants.socialId,  user.getSocialId());
		params.put(Constants.firstName, user.getFirstName());
		params.put(Constants.lastname, user.getLname());

		params.put(Constants.mobileNo,  user.getMobileNo());
		params.put(Constants.password,  user.getPassword());
		params.put(Constants.accountType, user.getAccountTypeStr());
		params.put(Constants.registrationType, user.getRegistrationType());
		params.put(Constants.deviceId, UtilsClass.getDeviceId(activity));
		params.put(Constants.deviceType, Constants.androidOS);
		params.put(Constants.gcmId, user.getGcmId());		
		params.put(Constants.countryCode,  MySharedPreferences.getInstance().getString(activity, Constants.countryCode, ""));
		activity.serviceCaller(activity, url, params, true, false, Constants.signup, true);
	}


	public void editProfile(BaseFragmentActivity activity, User user){
		String[] url = {URLsClass.service_type_updateProfile};	
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(Constants.API_KEY, Constants.API_KEY_VALUE);		
		params.put(Constants.firstName, user.getFirstName());
		params.put(Constants.lastname, user.getFirstName());
		params.put("userId",  MySharedPreferences.getInstance().getUserId(activity));
		params.put(Constants.countryCode,  MySharedPreferences.getInstance().getString(activity, Constants.countryCode, ""));
		params.put(Constants.mobileNo,  user.getMobileNo());			
		params.put(Constants.accountType, user.getAccountTypeStr());
		params.put(Constants.deviceId, UtilsClass.getDeviceId(activity));
		//params.put(Constants.p_device_brand, UtilsClass.getDeviceModel(this));
		//params.put(Constants.p_device_type, Constants.android);
		//params.put(Constants.p_device_os, UtilsClass.getDeviceSDKVersion(this));
		params.put(Constants.gcmId, user.getGcmId());

		activity.serviceCaller(activity, url, params, true, false, Constants.signup, true);
	}

	public void logout(BaseFragment context) {
		if(UtilsClass.isConnectingToInternet(context.getActivity())){	
			String urlsArr[] = {URLsClass.service_type_logout};
			HashMap<String, String> params = new HashMap<String, String>();
			params.put(Constants.API_KEY, Constants.API_KEY_VALUE);
			params.put(Constants.userId, MySharedPreferences.getInstance().getString(context.getActivity(), Constants.userId, ""));
			context.serviceCaller(context, urlsArr, params, true, false, Constants.logout, true);
		}else {
			UtilsClass.plsStartInternet(context.getActivity());
		}
	}
	
	public void getHistory(BaseFragment context) {
		if(UtilsClass.isConnectingToInternet(context.getActivity())){	
			String urlsArr[] = {URLsClass.service_type_getHistory};
			HashMap<String, String> params = new HashMap<String, String>();
			params.put(Constants.API_KEY, Constants.API_KEY_VALUE);
			params.put(Constants.userId, "29");//MySharedPreferences.getInstance().getString(context.getActivity(), Constants.userId, ""));
			context.serviceCaller(context, urlsArr, params, true, false, Constants.getHistory, true);
		}else {
			UtilsClass.plsStartInternet(context.getActivity());
		}
	}

	public void getPayroll(BaseFragmentActivity context, String rydeId) {
		// TODO Auto-generated method stub
		if(UtilsClass.isConnectingToInternet(context)){	
			String urlsArr[] = {URLsClass.service_type_getPayroll};
			HashMap<String, String> params = new HashMap<String, String>();
			params.put(Constants.API_KEY, Constants.API_KEY_VALUE);
			params.put(Constants.userId, "29");//MySharedPreferences.getInstance().getString(context.getActivity(), Constants.userId, ""));
			params.put(Constants.rydeId, rydeId);
			context.serviceCaller(context, urlsArr, params, true, false, Constants.getPayroll, true);
		}else {
			UtilsClass.plsStartInternet(context);
		}
		
	}

	/*public void setNotificationStatus(BaseFragment context,String status) {
		// TODO Auto-generated method stub
		if(UtilsClass.isConnectingToInternet(context.getActivity())){	
			String urlsArr[] = {URLsClass.service_type_setNotificationStatus};
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put(Constants.API_KEY, Constants.API_KEY_VALUE);
			params.put(Constants.USER_ID, MySharedPreferences.getInstance().getString(context.getActivity(), Constants.USER_ID, ""));
			params.put(Constants.status, status);
			context.serviceCaller(context, urlsArr, params, true, false, Constants.setNotificationStatus, true);
		}else {
			UtilsClass.plsStartInternet(context.getActivity());
		}
	}*/



}
