package com.thorben.helloworld.snooker;

import java.io.Serializable;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8512259499299806566L;
	private int userId;
	private String userLogin;
	private String firstName;
	private String lastName;
	private String password;
	
	public User() {
		
	}
	
	public User(String firstName, String lastName, String password, String userLogin, int userId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.userLogin = userLogin;
		this.userId = userId;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

}
