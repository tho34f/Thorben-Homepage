package com.thorben.helloworld.queries;

import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.thorben.helloworld.service.DateConverter;
import com.thorben.helloworld.service.ThorbenDierkesService;
import com.thorben.helloworld.service.ThorbenDierkes;
import com.thorben.helloworld.service.ThorbenDierkesLogger;
import com.thorben.helloworld.snooker.Termin;

public class CalendarQueries extends AbstractQuerries {
	
	private ThorbenDierkesLogger logger = new ThorbenDierkesLogger();
	
    public CalendarQueries(MySql sql, DataSource ds) {
    	
    	super(sql, ds);
    	
    }
    
	public Set<Termin> loadCalendarList() {
		
		Set<Termin> calendarList = new HashSet<>();
		
		try(Connection con = getDataSource().getConnection()){
			
			con.setAutoCommit(false);
			
			String queryNews = "SELECT * FROM termine ORDER BY creation_date";
		
			try(PreparedStatement stmt = con.prepareStatement(queryNews)){
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
		
		} catch (NamingException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_DB_TREIBER).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace(ThorbenDierkes.TREIBER, erroeMessage, e);
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace(ThorbenDierkes.SQL_FEHLER, erroeMessage, e);
		} 
		
		return calendarList;
		
	}
	
	public Termin loadCalendar(int terminId) {
		
		Termin tm = new Termin();
		
		try(Connection con = getDataSource().getConnection()){

			con.setAutoCommit(false);
			
			String queryNews = "SELECT * FROM termine WHERE id = ?";
		
			try(PreparedStatement stmt = con.prepareStatement(queryNews)){
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
		
		} catch (NamingException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_DB_TREIBER).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace(ThorbenDierkes.TREIBER, erroeMessage, e);
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace(ThorbenDierkes.SQL_FEHLER, erroeMessage, e);
		} 
		
		return tm;
		
	}
	
	public boolean newCalendarEntry(String title, String description, String teaser) {
		
		boolean isCreate = false;
		
		try(Connection con = getDataSource().getConnection()){

			con.setAutoCommit(false);
		
			String queryNews = "INSERT INTO termine (id, title, description, date, creation_date, change_date, teaser) VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			ThorbenDierkesService tds = new ThorbenDierkesService();
			
			try(PreparedStatement stmt = con.prepareStatement(queryNews)){
				int counter = 1;
				stmt.setInt(counter++, tds.generateId());
				stmt.setString(counter++, title);
				stmt.setString(counter++, description);
				stmt.setLong(counter++, System.currentTimeMillis());
				stmt.setLong(counter++, System.currentTimeMillis());
				stmt.setLong(counter++, 0);
				stmt.setString(counter++, teaser);
				
		        isCreate = stmt.execute();
		        
			}
			
		} catch (NamingException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_DB_TREIBER).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace(ThorbenDierkes.TREIBER, erroeMessage, e);
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace(ThorbenDierkes.SQL_FEHLER, erroeMessage, e);
		} 
		
		return isCreate;
		
	}
	
	public boolean updateCalendarEntry(int calendarId, String title, String text, String teaser, Image img) {
		
		boolean isUpdate = false;
		
		try(Connection con = getDataSource().getConnection()){
	
			con.setAutoCommit(false);
		
			String queryNews = "UPDATE termine SET title=?, description=?, teaser=?, change_date=?, date=?" +
					" WHERE id=?";
			
			try(PreparedStatement stmt = con.prepareStatement(queryNews)){
				int counter = 1;
				stmt.setString(counter++, title);
				stmt.setString(counter++, teaser);
				stmt.setBlob(counter++, (Blob) img);
				stmt.setLong(counter++, System.currentTimeMillis());
				stmt.setInt(counter++, calendarId);
				
		        isUpdate = stmt.execute();
		        
			}
			
		} catch (NamingException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_DB_TREIBER).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace(ThorbenDierkes.TREIBER, erroeMessage, e);
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace(ThorbenDierkes.SQL_FEHLER, erroeMessage, e);
		} 
		
		return isUpdate;
		
	}


}
