package com.thorben.helloworld.snooker;

import java.awt.Image;
import java.io.Serializable;

import com.thorben.helloworld.queries.MySql;

public class News implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4381698871841092465L;
	private int id;
	private String teaser;
	private String title;
	private Image img;
	private String text;
	private long changeDate;
	private long creationDate;
	private String changeDateAsString;
	private String creationDateAsString;
	private String creationDateForSlider;
	private User author;
	
	public News() {
		
	}
	
	public News(int id, String teaser, String title, Image img, String text, long creationDate, long changeDate, int userId) {
		this.id = id;
		this.teaser = teaser;
		this.text = text;
		this.title = title;
		this.img = img;
		this.creationDate = creationDate;
		this.changeDate = changeDate;
		this.author = setAuthor(userId);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeaser() {
		return teaser;
	}

	public void setTeaser(String teaser) {
		this.teaser = teaser;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
		News other = (News) obj;
		if (id != other.id)
			return false;
		return true;
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
