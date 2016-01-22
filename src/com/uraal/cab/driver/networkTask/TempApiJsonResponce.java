package com.uraal.cab.driver.networkTask;
/*package com.wms.noto.main.networkTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TempApiJsonResponce {
	public static JSONArray clientActivation(){
		String j ="[{\"clientUUID\":\"8b29f5a0-758d-4ed3-87b3-641aeec0d0aa\",\"farmUUID\":\"ACTIVATE\",\"serviceRequest\":\"requestAck\",\"serviceRequestID\":\"1\",\"serviceRequestDetail\":{\"request\":\"clientActivation\",\"status\":\"accepted\",\"reason\":\"Success\",\"dataToFollow\":\"true\"}},{\"clientUUID\":\"8b29f5a0-758d-4ed3-87b3-641aeec0d0aa\",\"farmUUID\":\"ACTIVATE\",\"serviceRequest\":\"clientActivationResult\",\"serviceRequestID\":\"1\",\"serviceRequestDetail\":{\"clientUUID\":\"8b29f5a0-758d-4ed3-87b3-641aeec0d0aa\",\"farmUUID\":\"2e6fabac-518a-4c40-93b3-9e201ccd9398\"}}]";
		try {
			JSONArray jObj = new JSONArray(j);
			return jObj;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static JSONArray reasonList(){
		//String j ="{\"rangeFrom\":\"1\",\"rangeTo\":\"10\",\"totalSize\":\"20\",\"list\":[{\"row\":1,\"reason\":1,\"description\":\"Calving\"},{\"row\":2,\"reason\":4,\"description\":\"Calf Due 6+ Weeks\"},{\"row\":3,\"reason\":8,\"description\":\"Calf Due 3-6 Weeks\"},{\"row\":4,\"reason\":34,\"description\":\"Calf Due 2-3 Weeks\"},{\"row\":5,\"reason\":18,\"description\":\"Calf Due 1-2 Weeks\"},{\"row\":6,\"reason\":21,\"description\":\"Calf Due 2-3 Weeks\"},{\"row\":7,\"reason\":2,\"description\":\"Calved\"},{\"row\":8,\"reason\":3,\"description\":\"Colostrum\"},{\"row\":9,\"reason\":5,\"description\":\"Mating\"},{\"row\":10,\"reason\":9,\"description\":\"Mating\"}]}";
		String j = "[{\"clientUUID\":\"8b29f5a0-758d-4ed3-87b3-641aeec0d0aa\",\"farmUUID\":\"2e6fabac-518a-4c40-93b3-9e201ccd9398\",\"serviceRequest\":\"requestAck\",\"serviceRequestID\":\"1\",\"serviceRequestDetail\":{\"request\":\"reasonList\",\"status\":\"accepted\",\"reason\":\"Success\",\"dataToFollow\":\"true\"}},{\"clientUUID\":\"8b29f5a0-758d-4ed3-87b3-641aeec0d0aa\",\"farmUUID\":\"2e6fabac-518a-4c40-93b3-9e201ccd9398\",\"serviceRequest\":\"reasonListResult\",\"serviceRequestID\":\"1\",\"serviceRequestDetail\":{\"rangeFrom\":1,\"rangeTo\":8,\"totalSize\":8,\"list\":[{\"row\":1,\"reason\":5,\"description\":\"Dry-Off\"},{\"row\":2,\"reason\":6,\"description\":\"Group Change\"},{\"row\":3,\"reason\":7,\"description\":\"Lameness\"},{\"row\":4,\"reason\":8,\"description\":\"Pregnancy Diagnosis\"},{\"row\":5,\"reason\":9,\"description\":\"Vaccination\"},{\"row\":6,\"reason\":10,\"description\":\"Vet Visit\"},{\"row\":7,\"reason\":11,\"description\":\"To be sold\"},{\"row\":8,\"reason\":12,\"description\":\"To be culled\"}]}}]";
		try {
			JSONArray jObj = new JSONArray(j);
			return jObj;
		} catch (JSONException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static JSONArray sessionList(){
		//String j = "{\"rangeFrom\":\"1\",\"rangeTo\":\"3\",\"totalSize\":\"3\",\"list\":[{\"row\":1,\"session\":1,\"sessionName\":\"AM\"},{\"row\":2,\"session\":2, \"sessionName\":\"MID\"},{\"row\":3,\"session\":3, \"sessionName\":\"PM\"}]}";
		String j = "[{\"clientUUID\":\"8b29f5a0-758d-4ed3-87b3-641aeec0d0aa\",\"farmUUID\":\"2e6fabac-518a-4c40-93b3-9e201ccd9398\",\"serviceRequest\":\"requestAck\",\"serviceRequestID\":\"1\",\"serviceRequestDetail\":{\"request\":\"sessionList\",\"status\":\"accepted\",\"reason\":\"Success\",\"dataToFollow\":\"true\"}},{\"clientUUID\":\"8b29f5a0-758d-4ed3-87b3-641aeec0d0aa\",\"farmUUID\":\"2e6fabac-518a-4c40-93b3-9e201ccd9398\",\"serviceRequest\":\"sessionListResult\",\"serviceRequestID\":\"1\",\"serviceRequestDetail\":{\"rangeFrom\":1,\"rangeTo\":3,\"totalSize\":3,\"list\":[{\"row\":1,\"session\":5,\"sessionName\":\"AM\"},{\"row\":2,\"session\":6,\"sessionName\":\"MID\"},{\"row\":3,\"session\":7,\"sessionName\":\"PM\"}]}}]";
		try {
			JSONArray jObj = new JSONArray(j);
			return jObj;
		} catch (JSONException e) {			
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static JSONObject sortResultsForMonth(){
		String j = "{\"totalSize\":\"3\",\"list\":[{\"row\":\"1\",\"sortDateResult\":\"04-Sep-2015\"},{\"row\":\"2\",\"sortDateResult\":\"08-Sep-2015\"},{\"row\":\"3\",\"sortDateResult\":\"22-Sep-2015\"}]}";
		try {
			JSONObject jObj = new JSONObject(j);
			return jObj;
		} catch (JSONException e) {			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static JSONObject sortResultsList(){
		String j = "{\"rangeFrom\":\"1\",\"rangeTo\":\"10\",\"totalSize\":\"27\",\"sortDate\":\"03-Sep-2015\",\"list\":[{\"row\":1,\"animalNumber\":\"12\",\"reason\":1,\"description\":\"Mating\",\"session\":1,\"sessionName\":\"AM\",\"state\":\"OK\"},{\"row\":2,\"animalNumber\":\"17\",\"reason\":1,\"description\":\"Mating\",\"session\":1,\"sessionName\":\"AM\",\"state\":\"Failed\"}]}";
		try {
			JSONObject jObj = new JSONObject(j);
			return jObj;
		} catch (JSONException e) {			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static JSONObject exportReportList(){
		String j = "{\"rangeFrom\":\"1\",\"rangeTo\":\"5\",\"totalSize\":\"5\",\"list\":[{\"row\":\"1\",\"exportReportName\":\"Sort Today AM\"},{\"row\":\"2\",\"exportReportName\":\"Sort Today MID\"},{\"row\":\"3\",\"exportReportName\":\"Sort Today PM\"},{\"row\":\"4\",\"exportReportName\":\"Sort Today All\"},{\"row\":\"5\",\"exportReportName\":\"Sorts This Week\"}]}";
		try {
			JSONObject jObj = new JSONObject(j);
			return jObj;
		} catch (JSONException e) {			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static JSONObject exportReportFileTypeList(){
		String j = "{\"rangeFrom\":\"1\",\"rangeTo\":\"4\",\"totalSize\":\"4\",\"list\":[{\"row\":\"1\",\"exportReportFileType\":\"PDF\"},{\"row\":\"2\",\"exportReportFileType\":\"CSV\"},{\"row\":\"3\",\"exportReportFileType\":\"XLS\"},{\"row\":\"4\",\"exportReportFileType\":\"XLSX\"}]}";
		try {
			JSONObject jObj = new JSONObject(j);
			return jObj;
		} catch (JSONException e) {			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static JSONObject searchAnimalBy(){
		String j = "{\"rangeFrom\":\"1\",\"rangeTo\":\"2\",\"totalSize\":\"2\",\"list\":[{\"row\":\"1\",\"animalNumber\":\"1234\",\"groupName\":\"2 Year Old \"},{\"row\":\"2\",\"animalNumber\":\"1237\",\"groupName\":\"Dry Cows\"},]}";
		try {
			JSONObject jObj = new JSONObject(j);
			return jObj;
		} catch (JSONException e) {			
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static JSONObject searchAnimal(){
		String j = "{\"animalNumber\":\"1234\",\"birthID\":\"PPPP-YY-NNNN\",\"RFID\":\"0000 09875 000123456789\",\"birthDate\":\"03-SEP-2015\",\"groupName\":\"Dry Cow\"}";
		try {
			JSONObject jObj = new JSONObject(j);
			return jObj;
		} catch (JSONException e) {			
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static JSONObject animalGroupList(){
		String j = "{\"rangeFrom\":\"1\",\"rangeTo\":\"10\",\"totalSize\":\"27\",\"list\":[{\"row\":\"1\",\"groupName\":\"Heifer\"},{\"row\":\"2\",\"groupName\":\"2 year old\"},{\"row\":\"3\",\"groupName\":\"3 year old\"},{\"row\":\"4\",\"groupName\":\"4+ year old\"},{\"row\":\"5\",\"groupName\":\"Sick Cows\"}]}";
		try {
			JSONObject jObj = new JSONObject(j);
			return jObj;
		} catch (JSONException e) {			
			e.printStackTrace();
		}
		
		return null;
	}
 }
*/