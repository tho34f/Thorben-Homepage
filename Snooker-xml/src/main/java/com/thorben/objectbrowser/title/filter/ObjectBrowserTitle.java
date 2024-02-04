package com.thorben.objectbrowser.title.filter;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ObjectBrowserTitle implements Serializable{

	private static final long serialVersionUID = -4257073666485844729L;
	
	private int id;
	private String name;
	private String description;

}
