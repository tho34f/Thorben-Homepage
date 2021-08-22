package com.thorben.helloworld.service;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;

public class ObjectBrowser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5766256589960477549L;
	
	private static ThorbenDierkesLogger logger = new ThorbenDierkesLogger();
	
	private static ObjectBrowserTitle[] newsObjectTitle = {new ObjectBrowserTitle(ThorbenDierkes.TITLE_TITLE, ThorbenDierkes.TITLE), 
			new ObjectBrowserTitle(ThorbenDierkes.TEASER, ThorbenDierkes.TEASER_TITLE), new ObjectBrowserTitle(ThorbenDierkes.CREATION_DATE,ThorbenDierkes.CREATION_DATE_TITLE), 
			new ObjectBrowserTitle(ThorbenDierkes.CHANGE_DATE,ThorbenDierkes.CHANGE_DATE_TITLE)};
	
	private static ObjectBrowserTitle[] terminObjectTitle = {new ObjectBrowserTitle(ThorbenDierkes.TITLE_TITLE, ThorbenDierkes.TITLE), 
			new ObjectBrowserTitle(ThorbenDierkes.DESCRIPTION, ThorbenDierkes.DESCRIPTION_TITLE), new ObjectBrowserTitle(ThorbenDierkes.TEASER, ThorbenDierkes.TEASER_TITLE), 
			new ObjectBrowserTitle(ThorbenDierkes.CREATION_DATE,ThorbenDierkes.CREATION_DATE_TITLE), new ObjectBrowserTitle(ThorbenDierkes.CHANGE_DATE,ThorbenDierkes.CHANGE_DATE_TITLE)};
	
	private static ObjectBrowserTitle[] userObjectTitle = {new ObjectBrowserTitle("userLogin", "userLogin"), new ObjectBrowserTitle("firstName", "firstName"), 
			new ObjectBrowserTitle("lastName", "lastName"), new ObjectBrowserTitle(ThorbenDierkes.CREATION_DATE,ThorbenDierkes.CREATION_DATE_TITLE)};
	
	private static ObjectBrowserTitle[] errorLogObjectTitle = {new ObjectBrowserTitle(ThorbenDierkes.TITLE_TITLE, ThorbenDierkes.TITLE), 
			new ObjectBrowserTitle(ThorbenDierkes.DESCRIPTION, ThorbenDierkes.DESCRIPTION_TITLE), new ObjectBrowserTitle(ThorbenDierkes.CREATION_DATE,ThorbenDierkes.CREATION_DATE_TITLE)};
	
	private int objectType;
	private String objectTitle;
	private String buttonTitle;
	private String objectIcon;
	private ObjectBrowserTitle[] tableTitle;
	
	private ObjectBrowser(String objectTitle, String objectIcon, int objectType, ObjectBrowserTitle[] tableTitle) {
    	this.objectIcon = objectIcon;
    	this.buttonTitle = "";
    	this.objectTitle = objectTitle;
    	this.objectType = objectType;	
    	this.tableTitle = tableTitle;
    }
	
	private ObjectBrowser(String objectTitle, String buttonTitle, String objectIcon, int objectType, ObjectBrowserTitle[] tableTitle) {
    	this.objectIcon = objectIcon;
    	this.buttonTitle = buttonTitle;
    	this.objectTitle = objectTitle;
    	this.objectType = objectType;
    	this.tableTitle = tableTitle;
    }
	
	public static ObjectBrowser setHeaderInformation(final HttpServletRequest request, int id) {
		ObjectBrowser ob = null;
		switch(id) {
			case ThorbenDierkes.USER:
				ob = new ObjectBrowser("Benutzer", "Neuer Benutzer", "fas fa-user", id, userObjectTitle);
				request.getSession().setAttribute(ThorbenDierkes.OBJEKT_BROWSER, ob);
				break;
			case ThorbenDierkes.NEWS:
				ob = new ObjectBrowser("Nachrichten", "Neue Nachricht", "fas fa-newspaper", id, newsObjectTitle);
				request.getSession().setAttribute(ThorbenDierkes.OBJEKT_BROWSER, ob);
				break;
			case ThorbenDierkes.CALENDAR: 
				ob = new ObjectBrowser("Termine", "Neuer Termin", "far fa-calendar-alt", id, terminObjectTitle);
				request.getSession().setAttribute(ThorbenDierkes.OBJEKT_BROWSER, ob);
				break;
			case ThorbenDierkes.ERROR_LOG_MASSAGE:
				ob = new ObjectBrowser("Fehler-Log", "fas fa-exclamation-triangle", id, errorLogObjectTitle);
				request.getSession().setAttribute(ThorbenDierkes.OBJEKT_BROWSER, ob);
				break;
			default:
				String errorMassage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_OB).toString();
				logger.errorLog("Fehlende OB Elemente",errorMassage);
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

	public ObjectBrowserTitle[] getTableTitle() {
		return tableTitle;
	}

	public void setTableTitle(ObjectBrowserTitle[] tableTitle) {
		this.tableTitle = tableTitle;
	}

}
