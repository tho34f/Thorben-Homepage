package com.thorben.objects;

import java.io.Serial;
import java.io.Serializable;

import com.thorben.queries.MySql;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Getter
@Setter
public class Termin extends AbstractData implements Serializable{
	
	@Serial
    private static final long serialVersionUID = -120027534171248816L;

	private long date;
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

	public User setAuthor(int userId) {
		User user = null;
		if(MySql.getInstance().getUserQueries() != null) {
			user = MySql.getInstance().getUserQueries().loadUser(userId);
		}
		
		return user;
	}

}
