package com.thorben.objects;

import java.io.Serial;
import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Getter
@Setter
public class User extends AbstractData implements Serializable{
	
	@Serial
    private static final long serialVersionUID = -8512259499299806566L;

	private String userLogin;
	private String firstName;
	private String lastName;
	private String password;
	private String language;
	
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
