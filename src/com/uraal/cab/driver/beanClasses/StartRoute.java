package com.uraal.cab.driver.beanClasses;

import java.io.Serializable;

	public class StartRoute implements Serializable{
		
		
		private static final long serialVersionUID = 14444L;
		private String address = "";
		private double lat=0.0;
		private double lng=0.0;
		private boolean  is_completed = false;
		private long   createdDate = 0;
		private long   updatedDate = 0;
		private int   rowId = 0;
		 
		
		public int getRowId() {
			return rowId;
		}

		public void setRowId(int rowId) {
			this.rowId = rowId;
		}
		public boolean is_completed() {
			return is_completed;
		}
		public void setIs_completed(boolean is_completed) {
			this.is_completed = is_completed;
		}
		public long getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(long createdDate) {
			this.createdDate = createdDate;
		}
		public long getUpdatedDate() {
			return updatedDate;
		}
		public void setUpdatedDate(long updatedDate) {
			this.updatedDate = updatedDate;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public double getLat() {
			return lat;
		}
		public void setLat(double lat) {
			this.lat = lat;
		}
		public double getLng() {
			return lng;
		}
		public void setLng(double lng) {
			this.lng = lng;
		}
		
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		

	}