package com.thorben.objects;

import java.io.Serializable;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8512259499299806566L;
	private int id;
	private String userLogin;
	private String firstName;
	private String lastName;
	private String password;
	private long creationDate;
	private String creationDateAsString;
	
	public User() {
		
	}
	
	public User(String firstName, String lastName, String password, String userLogin, int userId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.userLogin = userLogin;
		this.id = userId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int userId) {
		this.id = userId;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
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

}
