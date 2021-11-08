package com.thorben.helloworld.service;

import java.io.Serializable;

public class ObjectBrowserTitle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4257073666485844729L;
	
	private String name;
	private String description;
	
	
	public ObjectBrowserTitle(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
