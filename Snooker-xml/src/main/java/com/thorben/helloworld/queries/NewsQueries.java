package com.thorben.helloworld.queries;

import java.awt.Image;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.thorben.helloworld.service.DateConverter;
import com.thorben.helloworld.service.ThorbenDierkesService;
import com.thorben.helloworld.service.ThorbenDierkes;
import com.thorben.helloworld.service.ThorbenDierkesLogger;
import com.thorben.helloworld.snooker.News;

public class NewsQueries {
	
    private NewsQueries() {
    	
    	throw new IllegalStateException("Utility Class");
    	
    }
    
	public static Set<News> loadNewsList() {
		
		Set<News> newsList = new HashSet<>();
		
		try{
			
			MySqlConnection.createConnection();
			
			String queryNews = "SELECT nw.news_id, nw.news_title, nw.news_teaser, nw.news_image, nwt.news_text, nw.change_date, nw.creation_date" 
					+ " FROM news nw LEFT JOIN news_text nwt ON nw.news_id = nwt.news_id ORDER BY nw.creation_date DESC";
		
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryNews)){
		        ResultSet rs = stmt.executeQuery();
		        
		        while(rs.next()) {
					News massage = new News();
		        	massage.setId(rs.getInt("nw.news_id"));
		        	massage.setTeaser(rs.getString("nw.news_teaser"));
		        	massage.setText(rs.getString("nwt.news_text"));
		        	massage.setTitle(rs.getString("nw.news_title"));
		        	massage.setChangeDate(rs.getLong("nw.change_date"));
		        	massage.setCreationDate(rs.getLong("nw.creation_date"));
		        	massage.setCreationDateAsString(DateConverter.long2Date(massage.getCreationDate(),1));
		        	massage.setCreationDateForSlider(DateConverter.long2Date(massage.getCreationDate(),3));
		        	if(massage.getChangeDate() != 0) {
		        		massage.setChangeDateAsString(DateConverter.long2Date(massage.getChangeDate(),1));
		        	} else {
		        		massage.setChangeDateAsString("-");
		        	}
		        	
		        	massage.setImg(null);
		        	newsList.add(massage);
		        	
		        } 
		        
		        rs.close();
		        
			}
		 
			MySqlConnection.getConnectionSnooker().close();
		
		} catch (ClassNotFoundException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE).append(e.getLocalizedMessage()).toString();
			ThorbenDierkesLogger.errorLogWithTrace("Datenbanktreiber", erroeMessage, e);
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			ThorbenDierkesLogger.errorLogWithTrace("SQL - Fehler", erroeMessage, e);
		} 
		
		return newsList;
		
	}
	
	public static News loadNews(int newsId) {
		
		News massage = new News();
		
		try{
			
			MySqlConnection.createConnection();
			
			String queryNews = "SELECT nw.news_id, nw.news_title, nw.news_teaser, nw.news_image, nwt.news_text, nw.change_date, nw.creation_date" 
					+ " FROM news nw LEFT JOIN news_text nwt ON nw.news_id = nwt.news_id"
					+ " WHERE nw.news_id = ?";
		
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryNews)){
				stmt.setInt(1, newsId);
		        ResultSet rs = stmt.executeQuery();
		        
		        if(rs.next()) {
		        	massage.setId(rs.getInt("nw.news_id"));
		        	massage.setTeaser(rs.getString("nw.news_teaser"));
		        	massage.setText(rs.getString("nwt.news_text"));
		        	massage.setTitle(rs.getString("nw.news_title"));
		        	massage.setChangeDate(rs.getLong("nw.change_date"));
		        	massage.setCreationDate(rs.getLong("nw.creation_date"));
		        	massage.setCreationDateAsString(DateConverter.long2Date(massage.getCreationDate(),1));
		        	massage.setChangeDateAsString(DateConverter.long2Date(massage.getChangeDate(),1));
		        	
		        	massage.setImg(null);
		        	
		        } 
		        
		        rs.close();
		        
			}
		 
			MySqlConnection.getConnectionSnooker().close();
		
		} catch (ClassNotFoundException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE).append(e.getLocalizedMessage()).toString();
			ThorbenDierkesLogger.errorLogWithTrace("Datenbanktreiber", erroeMessage, e);
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			ThorbenDierkesLogger.errorLogWithTrace("SQL - Fehler", erroeMessage, e);
		} 
		
		return massage;
		
	}
	
	public static void newNewsEntry(String title, String text, String teaser, Image img) {
		
		try{
			
			MySqlConnection.createConnection();
		
			String queryNews = "INSERT INTO news (news_id, news_title, news_teaser, news_image, change_date, creation_date) VALUES (?, ?, ?, ?, ?, ?)";
			String queryNewsText = "INSERT INTO news_text (news_id, news_text) VALUES (?, ?)";
			
			int id = ThorbenDierkesService.generateId();
			
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryNews)){
				int counter = 1;
				stmt.setInt(counter++, id);
				stmt.setString(counter++, title);
				stmt.setString(counter++, teaser);
				stmt.setBlob(counter++, (Blob) img);
				stmt.setLong(counter++, 0);
				stmt.setLong(counter++, System.currentTimeMillis());
				
		        stmt.execute();
		        
			}
			
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryNewsText)){
				int counter = 1;
				stmt.setInt(counter++, id);
				stmt.setString(counter++, text);
				
		        stmt.execute();
		        
			}
			
			MySqlConnection.getConnectionSnooker().close();
		} catch (ClassNotFoundException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE).append(e.getLocalizedMessage()).toString();
			ThorbenDierkesLogger.errorLogWithTrace("Datenbanktreiber", erroeMessage, e);
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			ThorbenDierkesLogger.errorLogWithTrace("SQL - Fehler", erroeMessage, e);
		} 
		
	}
	
	public static void updateNewsEntry(int newsId, String title, String text, String teaser, Image img) {
		
		try{
			
			MySqlConnection.createConnection();
		
			String queryNews = "UPDATE news SET news_title=?, news_teaser=?, news_image=?, change_date=?" +
					" WHERE news_id=?";
			String queryNewsText = "UPDATE news_text SET news_text=? WHERE news_id=?";
			
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryNews)){
				int counter = 1;
				stmt.setString(counter++, title);
				stmt.setString(counter++, teaser);
				stmt.setBlob(counter++, (Blob) img);
				stmt.setLong(counter++, System.currentTimeMillis());
				stmt.setInt(counter++, newsId);
				
		        stmt.execute();
		        
			}
			
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryNewsText)){
				int counter = 1;
				stmt.setString(counter++, text);
				stmt.setInt(counter++, newsId);
				
		        stmt.execute();
		        
			}
			
			MySqlConnection.getConnectionSnooker().close();
		} catch (ClassNotFoundException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE).append(e.getLocalizedMessage()).toString();
			ThorbenDierkesLogger.errorLogWithTrace("Datenbanktreiber", erroeMessage, e);
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			ThorbenDierkesLogger.errorLogWithTrace("SQL - Fehler", erroeMessage, e);
		} 
		
	}

}
