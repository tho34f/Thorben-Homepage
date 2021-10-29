package com.thorben.helloworld.service;

import java.io.Serializable;

public class ObjectBrowser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5766256589960477549L;
	
	private int objectType;
	private String objectTitle;
	private String buttonTitle;
	private String objectIcon;
	private ObjectBrowserTitle[] tableTitle;
	
	public ObjectBrowser(String objectTitle, String objectIcon, int objectType, ObjectBrowserTitle[] tableTitle) {
    	this.objectIcon = objectIcon;
    	this.buttonTitle = "";
    	this.objectTitle = objectTitle;
    	this.objectType = objectType;	
    	this.tableTitle = tableTitle;
    }
	
	public ObjectBrowser(String objectTitle, String buttonTitle, String objectIcon, int objectType, ObjectBrowserTitle[] tableTitle) {
    	this.objectIcon = objectIcon;
    	this.buttonTitle = buttonTitle;
    	this.objectTitle = objectTitle;
    	this.objectType = objectType;
    	this.tableTitle = tableTitle;
    }

	public String getObjectTitle() {
		return objectTitle;
	}

	public void setObjectTitle(String objectTitle) {
		this.objectTitle = objectTitle;
	}

	public String getButtonTitle() {
		return buttonTitle;
	}

	public void setButtonTitle(String buttonTitle) {
		this.buttonTitle = buttonTitle;
	}

	public String getObjectIcon() {
		return objectIcon;
	}

	public void setObjectIcon(String objectIcon) {
		this.objectIcon = objectIcon;
	}

	public int getObjectType() {
		return objectType;
	}

	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}

	public ObjectBrowserTitle[] getTableTitle() {
		return tableTitle;
	}

	public void setTableTitle(ObjectBrowserTitle[] tableTitle) {
		this.tableTitle = tableTitle;
	}

}
