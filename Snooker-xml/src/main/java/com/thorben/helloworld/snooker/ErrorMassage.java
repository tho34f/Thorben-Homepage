package com.thorben.helloworld.snooker;

import java.io.Serializable;

public class ErrorMassage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4381698871841092465L;
	private int id;
	private String title;
	private String description;
	private long creationDate;
	private String creationDateAsString;
	
	public ErrorMassage() {
		
	}
	
	public ErrorMassage(int id, String title, long creationDate, String description) {
		this.id = id;
		this.title = title;
		this.creationDate = creationDate;
		this.setDescription(description);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorMassage other = (ErrorMassage) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationDateAsString() {
		return creationDateAsString;
	}

	public void setCreationDateAsString(String creationDateAsString) {
		this.creationDateAsString = creationDateAsString;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
