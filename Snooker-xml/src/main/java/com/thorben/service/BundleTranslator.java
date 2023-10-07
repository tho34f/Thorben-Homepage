package com.thorben.service;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BundleTranslator {
	
	private static final String LANGV3 = "langv3";
	private static ThorbenDierkesLogger logger = new ThorbenDierkesLogger();
	
	private String language = "de";
	
	public String translatBundle(String bundleKey) {
		logger.infoLog("BundleTranslator: translatBundle " + bundleKey);
		ResourceBundle resourceBundle = null;
		if(this.language.equals("de")) {
			resourceBundle = ResourceBundle.getBundle(LANGV3, Locale.GERMAN);
		} else {
			resourceBundle = ResourceBundle.getBundle(LANGV3, Locale.ENGLISH);
		}
		
		return resourceBundle.getString(bundleKey);
	}
}
