package com.thorben.queries;

import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.thorben.objects.Termin;
import com.thorben.service.DateConverter;
import com.thorben.service.ThorbenDierkesService;

public class CalendarQueries extends AbstractQuerries {
	
    public CalendarQueries(MySql sql) {
    	
    	super(sql);
    	
    }
    
	public Set<Termin> loadCalendarList() {
		
		Set<Termin> calendarList = new HashSet<>();
		
		try(Connection con = getSql().getDs().getConnection()){
			
			con.setAutoCommit(false);
			
			String queryNews = "SELECT * FROM event ORDER BY creation_date";
		
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
		        	tm.setAuthor(rs.getInt("author_id"));
		        	calendarList.add(tm);
		        	
		        } 
		        
		        rs.close();
		        
			}
		
		} catch (SQLException e) {
			handleSqlException(e);
		} 
		
		return calendarList;
		
	}
	
	public Termin loadCalendar(int terminId) {
		
		Termin tm = new Termin();
		
		try(Connection con = getSql().getDs().getConnection()){

			con.setAutoCommit(false);
			
			String queryNews = "SELECT * FROM event WHERE id = ?";
		
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
		        	tm.setAuthor(rs.getInt("author_id"));
		        	
		        } 
		        
		        rs.close();
		        
			}
		
		} catch (SQLException e) {
			handleSqlException(e);
		} 
		
		return tm;
		
	}
	
	public boolean newCalendarEntry(String title, String description, String teaser, String authorLogin, int authorId) {
		
		boolean isCreate = false;
		
		try(Connection con = getSql().getDs().getConnection()){

			con.setAutoCommit(false);
		
			String queryNews = "INSERT INTO event (id, title, description, date, creation_date, change_date, teaser,author_login, author_id) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
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
				stmt.setString(counter++, authorLogin);
				stmt.setInt(counter++, authorId);
				
		        isCreate = stmt.execute();
		        
			}
			
		} catch (SQLException e) {
			handleSqlException(e);
		} 
		
		return isCreate;
		
	}
	
	public boolean updateCalendarEntry(int calendarId, String title, String teaser, Image img) {
		
		boolean isUpdate = false;
		
		try(Connection con = getSql().getDs().getConnection()){
	
			con.setAutoCommit(false);
		
			String queryNews = "UPDATE event SET title=?, description=?, teaser=?, change_date=?, date=?" +
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
			
		} catch (SQLException e) {
			handleSqlException(e);
		} 
		
		return isUpdate;
		
	}


}
