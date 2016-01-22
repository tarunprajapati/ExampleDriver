package com.uraal.cab.driver.utilities;

import java.util.ArrayList;

import com.uraal.cab.driver.beanClasses.EndRoute;



public class TestClass {
	
	public static ArrayList<EndRoute> getLagLongList(){
		ArrayList<EndRoute> arrayList = new ArrayList<EndRoute>();
		EndRoute endRoute1 = new EndRoute();
		endRoute1.setLat(26.891798);
		endRoute1.setLng(75.759658);
		endRoute1.setAddress("Sethias Dana Pani");
		arrayList.add(endRoute1);
		
		EndRoute endRoute2 = new EndRoute();
		endRoute2.setLat(26.8878854);
		endRoute2.setLng(75.80327819999999);
		endRoute1.setAddress("Doorbeen Restaurant");
		arrayList.add(endRoute2);
		
		EndRoute endRoute3 = new EndRoute();
		endRoute3.setLat(26.9080452);
		endRoute3.setLng(75.8127075);
		endRoute1.setAddress("V2 Fine Dining Restaurant");
		arrayList.add(endRoute3);
		
		return arrayList;
		
	}

}
