package com.thorben.helloworld.queries;

import java.awt.Image;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thorben.helloworld.service.DateConverter;
import com.thorben.helloworld.service.ThorbenDierkesService;
import com.thorben.helloworld.service.ThorbenDierkes;
import com.thorben.helloworld.snooker.Termin;

public class CalendarQueries {
	
private static final Logger logger = LoggerFactory.getLogger(CalendarQueries.class);
	
    private CalendarQueries() {
    	
    	throw new IllegalStateException("Utility Class");
    	
    }
    
	public static Set<Termin> loadCalendarList() {
		
		Set<Termin> calendarList = new HashSet<>();
		
		try{
			
			MySqlConnection.createConnection();
			
			String queryNews = "SELECT * FROM termine ORDER BY creation_date";
		
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryNews)){
		        ResultSet rs = stmt.executeQuery();
		        
		        while(rs.next()) {
					Termin tm = new Termin();
		        	tm.setDate(rs.getLong("date"));
		        	tm.setChangeDate(rs.getLong("change_date"));
		        	tm.setCreationDate(rs.getLong("creation_date"));
		        	tm.setCreationDateAsString(DateConverter.long2Date(tm.getCreationDate(),1));
		        	tm.setCreationDateForSlider(DateConverter.long2Date(tm.getDate(),3));
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
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE).append(e.getLocalizedMessage()).toString();
			logger.info(erroeMessage);
            e.printStackTrace();
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.info(erroeMessage);
            e.printStackTrace();
		} 
		
		return calendarList;
		
	}
	
	public static Termin loadCalendar(int terminId) {
		
		Termin tm = new Termin();
		
		try{
			
			MySqlConnection.createConnection();
			
			String queryNews = "SELECT * FROM termine WHERE id = ?";
		
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryNews)){
				stmt.setInt(1, terminId);
		        ResultSet rs = stmt.executeQuery();
		        
		        if(rs.next()) {
		        	tm.setDate(rs.getLong("date"));
		        	tm.setChangeDate(rs.getLong("change_date"));
		        	tm.setCreationDate(rs.getLong("creation_date"));
		        	tm.setCreationDateAsString(DateConverter.long2Date(tm.getCreationDate(),1));
		        	tm.setChangeDateAsString(DateConverter.long2Date(tm.getChangeDate(),1));
		        	
		        	tm.setTitle(rs.getString("title"));
		        	tm.setDescription(rs.getString("description"));
		        	tm.setId(rs.getInt("id"));
		        	tm.setTeaser(rs.getString("teaser"));
		        	
		        } 
		        
		        rs.close();
		        
			}
		 
			MySqlConnection.getConnectionSnooker().close();
		
		} catch (ClassNotFoundException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE).append(e.getLocalizedMessage()).toString();
			logger.info(erroeMessage);
            e.printStackTrace();
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.info(erroeMessage);
            e.printStackTrace();
		} 
		
		return tm;
		
	}
	
	public static void newCalendarEntry(String title, String description, String teaser) {
		
		try{
			
			MySqlConnection.createConnection();
		
			String queryNews = "INSERT INTO termine (id, title, description, date, creation_date, change_date, teaser) VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryNews)){
				int counter = 1;
				stmt.setInt(counter++, ThorbenDierkesService.generateId());
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
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE).append(e.getLocalizedMessage()).toString();
			logger.info(erroeMessage);
            e.printStackTrace();
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.info(erroeMessage);
            e.printStackTrace();
		} 
		
	}
	
	public static void updateCalendarEntry(int calendarId, String title, String text, String teaser, Image img) {
		
		try{
			
			MySqlConnection.createConnection();
		
			String queryNews = "UPDATE termine SET title=?, description=?, teaser=?, change_date=?, date=?" +
					" WHERE id=?";
			
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryNews)){
				int counter = 1;
				stmt.setString(counter++, title);
				stmt.setString(counter++, teaser);
				stmt.setBlob(counter++, (Blob) img);
				stmt.setLong(counter++, System.currentTimeMillis());
				stmt.setInt(counter++, calendarId);
				
		        stmt.execute();
		        
			}
			
			MySqlConnection.getConnectionSnooker().close();
		} catch (ClassNotFoundException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.info(erroeMessage);
            e.printStackTrace();
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE).append(e.getLocalizedMessage()).toString();
			logger.info(erroeMessage);
            e.printStackTrace();
		} 
		
	}


}
