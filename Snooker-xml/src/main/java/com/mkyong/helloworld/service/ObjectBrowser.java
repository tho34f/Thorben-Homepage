package com.mkyong.helloworld.service;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public class ObjectBrowser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5766256589960477549L;
	private String objectTitle;
	private String buttonTitle;
	private String objectIcon;
	
	private ObjectBrowser() {
	    	
    }
	
	private ObjectBrowser(String objectTitle, String buttonTitle, String objectIcon) {
    	this.objectIcon = objectIcon;
    	this.buttonTitle = buttonTitle;
    	this.objectTitle = objectTitle;
    }
	
	public static void setHeaderInformation(final HttpServletRequest request, int id) {
		ObjectBrowser ob = null;
		switch(id) {
			case 39:
				ob = new ObjectBrowser("Nachrichten", "Neue Nachricht", "fas fa-newspaper");
				request.getSession().setAttribute("Objectbrowser", ob);
				break;
			case 40: 
				ob = new ObjectBrowser("Termine", "Neuer Termin", "far fa-calendar-alt");
				request.getSession().setAttribute("Objectbrowser", ob);
				break;
		}
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

}
