package com.thorben.helloworld.service;

public class ObjectBrowserData {

	private ObjectBrowserTitle[] newsObjectTitle = {new ObjectBrowserTitle(ThorbenDierkes.TITLE_TITLE, ThorbenDierkes.TITLE), 
			new ObjectBrowserTitle(ThorbenDierkes.TEASER, ThorbenDierkes.TEASER_TITLE), new ObjectBrowserTitle(ThorbenDierkes.CREATION_DATE,ThorbenDierkes.CREATION_DATE_TITLE), 
			new ObjectBrowserTitle(ThorbenDierkes.CHANGE_DATE,ThorbenDierkes.CHANGE_DATE_TITLE)};
	
	private ObjectBrowserTitle[] terminObjectTitle = {new ObjectBrowserTitle(ThorbenDierkes.TITLE_TITLE, ThorbenDierkes.TITLE), 
			new ObjectBrowserTitle(ThorbenDierkes.DESCRIPTION, ThorbenDierkes.DESCRIPTION_TITLE), new ObjectBrowserTitle(ThorbenDierkes.TEASER, ThorbenDierkes.TEASER_TITLE), 
			new ObjectBrowserTitle(ThorbenDierkes.CREATION_DATE,ThorbenDierkes.CREATION_DATE_TITLE), new ObjectBrowserTitle(ThorbenDierkes.CHANGE_DATE,ThorbenDierkes.CHANGE_DATE_TITLE)};
	
	private  ObjectBrowserTitle[] userObjectTitle = {new ObjectBrowserTitle("userLogin", "User-Login"), new ObjectBrowserTitle("firstName", "Vorname"), 
			new ObjectBrowserTitle("lastName", "Nachnahme"), new ObjectBrowserTitle(ThorbenDierkes.CREATION_DATE,ThorbenDierkes.CREATION_DATE_TITLE)};
	
	private ObjectBrowserTitle[] errorLogObjectTitle = {new ObjectBrowserTitle(ThorbenDierkes.TITLE_TITLE, ThorbenDierkes.TITLE), 
			new ObjectBrowserTitle(ThorbenDierkes.DESCRIPTION, ThorbenDierkes.DESCRIPTION_TITLE), new ObjectBrowserTitle(ThorbenDierkes.CREATION_DATE,ThorbenDierkes.CREATION_DATE_TITLE)};
	
	public ObjectBrowserTitle[] getNewsObjectTitle() {
		return newsObjectTitle;
	}

	public void setNewsObjectTitle(ObjectBrowserTitle[] newsObjectTitle) {
		this.newsObjectTitle = newsObjectTitle;
	}

	public ObjectBrowserTitle[] getTerminObjectTitle() {
		return terminObjectTitle;
	}

	public void setTerminObjectTitle(ObjectBrowserTitle[] terminObjectTitle) {
		this.terminObjectTitle = terminObjectTitle;
	}

	public ObjectBrowserTitle[] getUserObjectTitle() {
		return userObjectTitle;
	}

	public void setUserObjectTitle(ObjectBrowserTitle[] userObjectTitle) {
		this.userObjectTitle = userObjectTitle;
	}

	public ObjectBrowserTitle[] getErrorLogObjectTitle() {
		return errorLogObjectTitle;
	}

	public void setErrorLogObjectTitle(ObjectBrowserTitle[] errorLogObjectTitle) {
		this.errorLogObjectTitle = errorLogObjectTitle;
	}

}
