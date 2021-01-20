package com.mkyong.helloworld.service;

public class TypeConverter {
	
	public static int string2int(String string, int defaultNumber) {
		
		int value;
		
		if(string == null) {
			return defaultNumber;
		} else {
			value = Integer.parseInt(string);
		}
		
		return value;
		
	}
	
	public static long string2long(String string, long defaultNumber) {
		
		long value;
		
		if(string == null) {
			return defaultNumber;
		} else {
			value = Long.parseLong(string);
		}
		
		return value;
		
	}
	
	public static float string2float(String string, float defaultNumber) {
		
		float value;
		
		if(string == null) {
			return defaultNumber;
		} else {
			value = Float.parseFloat(string);
		}
		
		return value;
		
	}

	public static double string2double(String string, double defaultNumber) {
	
	double value;
	
	if(string == null) {
		return defaultNumber;
	} else {
		value = Double.parseDouble(string);
	}
	
	return value;
	
	}

}
