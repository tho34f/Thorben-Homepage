package com.mkyong.helloworld.queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mkyong.helloworld.snooker.News;
import com.mkyong.helloworld.snooker.Termin;

public class CalendarQueries {
	
private static final Logger logger = LoggerFactory.getLogger(CalendarQueries.class);
	
    private CalendarQueries() {
    	
    	throw new IllegalStateException("Utility Class");
    	
    }
    
	public static Set<Termin> loadCalendarList() {
		
		Set<Termin> calendarList = new HashSet<>();
		
		try{
			
			MySqlConnection.createConnection();
			
			Termin tm = new Termin();
			
			String queryNews = "select * from termine";
		
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryNews)){
		        ResultSet rs = stmt.executeQuery();
		        
		        if(rs.next()) {
		        	tm.setDate(rs.getInt("date"));
		        	tm.setTitle(rs.getString("title"));
		        	tm.setDescription(rs.getString("description"));
		        	calendarList.add(tm);
		        	
		        } 
		        
		        rs.close();
		        
			}
		 
			MySqlConnection.getConnectionSnooker().close();
		
		} catch (ClassNotFoundException e) {
			((ServletContext) logger).log("Der Datenbank treiber wurde nicht gefunden. - " + e.getLocalizedMessage());
            e.printStackTrace();
		} catch (SQLException e) {
			((ServletContext) logger).log("SQL Fehler - " + e.getLocalizedMessage());
            e.printStackTrace();
		} 
		
		return calendarList;
		
	}

}
