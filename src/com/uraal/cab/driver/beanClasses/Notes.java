package com.uraal.cab.driver.beanClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class Notes implements Serializable {

	private static final long serialVersionUID = 16666L;
	private int noteId = 0;
	private String address = "";
	private String imageUrl = "";
	private long createdDate = 0;
	private long updatedDate = 0;
	private String notesOf = "";
	private int notesOfId = 0;
	private String caption = "";
	private int imgCount = 0;
	private String noteText = "";
	private int isImg = 0;
	private ArrayList<Notes> notesArray = null;
	
	
	
	public ArrayList<Notes> getNotesArray() {
		return notesArray;
	}

	public void setNotesArray(ArrayList<Notes> notesArray) {
		this.notesArray = notesArray;
	}

	public int isImg() {
		return isImg;
	}

	public void setIsImg(int isImg) {
		this.isImg = isImg;
	}

	public String getNoteText() {
		return noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

	private ArrayList<String> urlArray = null;
	private ArrayList<String> captionArray = null;

	public ArrayList<String> getUrlArray() {
		return urlArray;
	}

	public void setUrlArray(ArrayList<String> urlArray) {
		this.urlArray = urlArray;
	}

	public ArrayList<String> getCaptionArray() {
		return captionArray;
	}

	public void setCaptionArray(ArrayList<String> captionArray) {
		this.captionArray = captionArray;
	}

	public int getImgCount() {
		return imgCount;
	}

	public void setImgCount(int imgCount) {
		this.imgCount = imgCount;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getNotesOf() {
		return notesOf;
	}

	public void setNotesOf(String notesOf) {
		this.notesOf = notesOf;
	}

	public int getNotesOfId() {
		return notesOfId;
	}

	public void setNotesOfId(int notesOfId) {
		this.notesOfId = notesOfId;
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

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Notes [noteId=" + noteId + ", address=" + address
				+ ", imageUrl=" + imageUrl + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + ", notesOf=" + notesOf
				+ ", notesOfId=" + notesOfId + ", caption=" + caption
				+ ", imgCount=" + imgCount + ", noteText=" + noteText
				+ ", isImg=" + isImg + ", urlArray=" + urlArray
				+ ", captionArray=" + captionArray + "]";
	}
	
	

}