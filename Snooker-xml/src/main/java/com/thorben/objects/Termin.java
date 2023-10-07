package com.thorben.objects;

import java.io.Serializable;

import com.thorben.queries.MySql;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Termin implements Serializable{
	
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
	private User author;
	
	public Termin (String title, String description, long date, long id, String teaser, long creationDate, long changeDate, int userId) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.id = id;
		this.teaser = teaser;
		this.changeDate = changeDate;
		this.creationDate = creationDate;
		this.author = setAuthor(userId);
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

	public User getAuthor() {
		return author;
	}

	public User setAuthor(int userId) {
		User user = null;
		if(MySql.getInstance().getUserQueries() != null) {
			user = MySql.getInstance().getUserQueries().loadUser(userId);
		}
		
		return user;
	}

}
