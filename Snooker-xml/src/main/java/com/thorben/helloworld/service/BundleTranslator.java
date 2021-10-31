package com.thorben.helloworld.service;

import java.util.Locale;
import java.util.ResourceBundle;

public class BundleTranslator {
	
	private String language = "de";
	private static final String LANGV3 = "langv3";
	
	public BundleTranslator(String language) {
		this.language = language;
	}
	
	public String translatBundle(String bundleKey) {
		ResourceBundle resourceBundle = null;
		if(this.language.equals("de")) {
			resourceBundle = ResourceBundle.getBundle(getPropertieBundle(), Locale.GERMAN);
		} else {
			resourceBundle = ResourceBundle.getBundle(getPropertieBundle(), Locale.ENGLISH);
		}
		
		return resourceBundle.getString(bundleKey);
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPropertieBundle() {
		return LANGV3;
	}

}
