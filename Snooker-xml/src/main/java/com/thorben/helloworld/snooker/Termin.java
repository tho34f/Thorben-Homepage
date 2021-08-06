package com.thorben.helloworld.snooker;

import java.io.Serializable;

public class Termin implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -120027534171248816L;
	private long id;
	private long date;
	private long changeDate;
	private long creationDate;
	private String changeDateAsString;
	private String creationDateAsString;
	private String creationDateForSlider;
	private String title;
	private String description;
	private String teaser;
	
	public Termin() {
		
	}
	
	public Termin (String title, String description, long date, long id, String teaser, long creationDate, long changeDate) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.id = id;
		this.teaser = teaser;
		this.changeDate = changeDate;
		this.creationDate = creationDate;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + date);
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
		Termin other = (Termin) obj;
		if (date != other.date)
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTeaser() {
		return teaser;
	}

	public void setTeaser(String teaser) {
		this.teaser = teaser;
	}

	public long getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(long changeDate) {
		this.changeDate = changeDate;
	}

	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	public String getChangeDateAsString() {
		return changeDateAsString;
	}

	public void setChangeDateAsString(String changeDateAsString) {
		this.changeDateAsString = changeDateAsString;
	}

	public String getCreationDateAsString() {
		return creationDateAsString;
	}

	public void setCreationDateAsString(String creationDateAsString) {
		this.creationDateAsString = creationDateAsString;
	}

	public String getCreationDateForSlider() {
		return creationDateForSlider;
	}

	public void setCreationDateForSlider(String creationDateForSlider) {
		this.creationDateForSlider = creationDateForSlider;
	}

}
