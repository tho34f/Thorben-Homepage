package com.thorben.objects;

import java.awt.Image;
import java.io.Serializable;

import com.thorben.queries.MySql;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class News implements Serializable{
	
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


	public User setAuthor(int userId) {
		User user = null;
		if(MySql.getInstance().getUserQueries() != null) {
			user = MySql.getInstance().getUserQueries().loadUser(userId);
		}
		
		return user;
	}

}
