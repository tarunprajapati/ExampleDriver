package com.uraal.cab.driver.beanClasses;

public class Drawer {
	private String title = "";
	private int notifyMessage = 0;
	private int textColorSelect ;
	private int textColorUnselect;
	private int iconSelect;
	private int iconUnselect;
	private boolean isChecked = false;
	public boolean haveUnread = false;
	
	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIconSelect() {
		return iconSelect;
	}

	public void setIconSelect(int iconSelect) {
		this.iconSelect = iconSelect;
	}

	public int getIconUnselect() {
		return iconUnselect;
	}

	public void setIconUnselect(int iconUnselect) {
		this.iconUnselect = iconUnselect;
	}

	public int getTextColorSelect() {
		return textColorSelect;
	}

	public void setTextColorSelect(int textColorSelect) {
		this.textColorSelect = textColorSelect;
	}

	public int getTextColorUnselect() {
		return textColorUnselect;
	}

	public void setTextColorUnselect(int textColorUnselect) {
		this.textColorUnselect = textColorUnselect;
	}

	public int getNotifyMessage() {
		return notifyMessage;
	}

	public void setNotifyMessage(int notifyMessage, boolean haveUnread) {
		this.notifyMessage = notifyMessage;
		this.haveUnread  = haveUnread;
	}
}
