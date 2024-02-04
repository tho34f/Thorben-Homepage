package com.thorben.objects;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable{
	
	private static final long serialVersionUID = -8512259499299806566L;
	private int id;
	private String userLogin;
	private String firstName;
	private String lastName;
	private String password;
	private String language;
	private long creationDate;
	private String creationDateAsString;
	
	public User(String firstName, String lastName, String userLogin, int userId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userLogin = userLogin;
		this.id = userId;
	}
	
	public User(String firstName, String lastName, String password, String userLogin, int userId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.userLogin = userLogin;
		this.id = userId;
	}

}
