package com.thorben.objects;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ErrorMassage implements Serializable{
	
	private static final long serialVersionUID = 4381698871841092465L;
	
	private int id;
	private String title;
	private String description;
	private long creationDate;
	private String creationDateAsString;
	
	public ErrorMassage(int id, String title, long creationDate, String description) {
		this.id = id;
		this.title = title;
		this.creationDate = creationDate;
		this.description = description;
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

}
