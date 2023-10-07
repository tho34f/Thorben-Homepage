package com.thorben.objectbrowser;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjectBrowser implements Serializable{
	
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

}
