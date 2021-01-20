package com.mkyong.helloworld.service;

import java.sql.SQLException;
import java.util.List;


public class UpdateDB {
	
	private UpdateDB() {
	   	
		throw new IllegalStateException("Utility Class");
		
	}
	

	public static void updateDatenbank(List<String> provisionalRanking) {
		try {
			MySqlConnection.createConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
			
	}
	    

}
