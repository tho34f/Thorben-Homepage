package com.mkyong.helloworld.service;

import javax.servlet.http.HttpServletRequest;

public class ObjectBrowser {
	
	private ObjectBrowser() {
	   	
		throw new IllegalStateException("Utility Class");
	    	
    }
	
	
	private static String objectTitle = "objectTitle";
	private static String buttonTitle = "buttonTitle";
	private static String objectIcon = "objectIcon";
	
	public static void setHeaderInformation(final HttpServletRequest request, int id) {
		switch(id) {
			case 39:
				request.getSession().setAttribute(objectTitle, "Nachrichten");
				request.getSession().setAttribute(buttonTitle, "Neue Nachricht");
				request.getSession().setAttribute(objectIcon, "fas fa-newspaper");
				break;
			case 40: 
				request.getSession().setAttribute(objectTitle, "Termine");
				request.getSession().setAttribute(buttonTitle, "Neuer Termin");
				request.getSession().setAttribute(objectIcon, "far fa-calendar-alt");
				break;
		}
	}

}
