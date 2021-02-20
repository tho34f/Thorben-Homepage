package com.mkyong.helloworld.queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mkyong.helloworld.service.DateConverter;
import com.mkyong.helloworld.service.HelloWorldService;
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
		        	tm.setDate(rs.getLong("date"));
		        	tm.setChangeDate(rs.getLong("change_date"));
		        	tm.setCreationDate(rs.getLong("creation_date"));
		        	tm.setCreationDateAsString(DateConverter.long2Date(tm.getCreationDate(),1));
		        	if(tm.getChangeDate() != 0) {
		        		tm.setChangeDateAsString(DateConverter.long2Date(tm.getChangeDate(),1));
		        	} else {
		        		tm.setChangeDateAsString("-");
		        	}
		        	
		        	tm.setTitle(rs.getString("title"));
		        	tm.setDescription(rs.getString("description"));
		        	tm.setId(rs.getInt("id"));
		        	tm.setTeaser(rs.getString("teaser"));
		        	calendarList.add(tm);
		        	
		        } 
		        
		        rs.close();
		        
			}
		 
			MySqlConnection.getConnectionSnooker().close();
		
		} catch (ClassNotFoundException e) {
			logger.info("Der Datenbank treiber wurde nicht gefunden. - " + e.getLocalizedMessage());
            e.printStackTrace();
		} catch (SQLException e) {
			logger.info("SQL Fehler - " + e.getLocalizedMessage());
            e.printStackTrace();
		} 
		
		return calendarList;
		
	}
	
	public static void newCalendarEntry(String title, String description, String teaser) {
		
		try{
			
			MySqlConnection.createConnection();
		
			String queryNews = "Insert into termine (id, title, description, date, creation_date, change_date, teaser) values (?, ?, ?, ?, ?, ?, ?)";
			
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryNews)){
				int counter = 1;
				stmt.setInt(counter++, HelloWorldService.generateId());
				stmt.setString(counter++, title);
				stmt.setString(counter++, description);
				stmt.setLong(counter++, System.currentTimeMillis());
				stmt.setLong(counter++, System.currentTimeMillis());
				stmt.setLong(counter++, 0);
				stmt.setString(counter++, teaser);
				
		        stmt.execute();
		        
			}
			
			MySqlConnection.getConnectionSnooker().close();
		} catch (ClassNotFoundException e) {
			logger.info("Der Datenbank treiber wurde nicht gefunden. - " + e.getLocalizedMessage());
            e.printStackTrace();
		} catch (SQLException e) {
			logger.info("SQL Fehler - " + e.getLocalizedMessage());
            e.printStackTrace();
		} 
		
	}

}
