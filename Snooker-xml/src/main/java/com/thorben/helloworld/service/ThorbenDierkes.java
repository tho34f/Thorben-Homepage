package com.thorben.helloworld.service;

public class ThorbenDierkes {
	
	private ThorbenDierkes() {
	   	
		throw new IllegalStateException("Utility Class");
	    	
    }
	
	//Objekttypen
	public static final int USER = 38;
	public static final int NEWS = 39;
	public static final int CALENDAR = 40;
	
	//Snooker
	public static final int SNOOKER_BREAK = 20;
	
	//Error-Messages
    public static final String ERROR_MESSAGE = "Der Datenbank treiber wurde nicht gefunden. -";
    public static final String ERROR_MESSAGE_SQL = "SQL Fehler - ";
    
    //Länge Datenbanken
    public static final int VARCHAR = 255;
    public static final int TEXT = 65535;
    public static final long MEDIUMTEXT = 16777215;

}
