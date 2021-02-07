package com.mkyong.helloworld.service;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public class ObjectBrowser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5766256589960477549L;
	
	private int objectType;
	private String objectTitle;
	private String buttonTitle;
	private String objectIcon;
	
	private ObjectBrowser() {
	    	
    }
	
	private ObjectBrowser(String objectTitle, String buttonTitle, String objectIcon, int objectType) {
    	this.objectIcon = objectIcon;
    	this.buttonTitle = buttonTitle;
    	this.objectTitle = objectTitle;
    	this.setObjectType(objectType);
    }
	
	public static ObjectBrowser setHeaderInformation(final HttpServletRequest request, int id) {
		ObjectBrowser ob = null;
		switch(id) {
			case 39:
				ob = new ObjectBrowser("Nachrichten", "Neue Nachricht", "fas fa-newspaper", id);
				request.getSession().setAttribute("Objectbrowser", ob);
				break;
			case 40: 
				ob = new ObjectBrowser("Termine", "Neuer Termin", "far fa-calendar-alt", id);
				request.getSession().setAttribute("Objectbrowser", ob);
				break;
		}
		
		return ob;
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

}
