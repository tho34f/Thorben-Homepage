package com.mkyong.helloworld.queries;

import java.awt.Image;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mkyong.helloworld.service.DateConverter;
import com.mkyong.helloworld.service.HelloWorldService;
import com.mkyong.helloworld.snooker.News;

public class NewsQueries {
	
private static final Logger logger = LoggerFactory.getLogger(NewsQueries.class);
	
    private NewsQueries() {
    	
    	throw new IllegalStateException("Utility Class");
    	
    }
    
	public static Set<News> loadNewsList() {
		
		Set<News> newsList = new HashSet<>();
		
		try{
			
			MySqlConnection.createConnection();
			
			News massage = new News();
			
			String queryNews = "select nw.news_id, nw.news_title, nw.news_teaser, nw.news_image, nwt.news_text, nw.change_date, nw.creation_date" 
					+ " from news nw left join news_text nwt on nw.news_id = nwt.news_id";
		
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryNews)){
		        ResultSet rs = stmt.executeQuery();
		        
		        if(rs.next()) {
		        	massage.setId(rs.getInt("nw.news_id"));
		        	massage.setTeaser(rs.getString("nw.news_teaser"));
		        	massage.setText(rs.getString("nwt.news_text"));
		        	massage.setTitle(rs.getString("nw.news_title"));
		        	massage.setChangeDate(rs.getLong("nw.change_date"));
		        	massage.setCreationDate(rs.getLong("nw.creation_date"));
		        	massage.setCreationDateAsString(DateConverter.long2Date(massage.getCreationDate(),1));
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
			logger.info("Der Datenbank treiber wurde nicht gefunden. - " + e.getLocalizedMessage());
            e.printStackTrace();
		} catch (SQLException e) {
			logger.info("SQL Fehler - " + e.getLocalizedMessage());
            e.printStackTrace();
		} 
		
		return newsList;
		
	}
	
	public static News loadNews(int newsId) {
		
		News massage = new News();
		
		try{
			
			MySqlConnection.createConnection();
			
			String queryNews = "select nw.news_id, nw.news_title, nw.news_teaser, nw.news_image, nwt.news_text, nw.change_date, nw.creation_date" 
					+ " from news nw left join news_text nwt on nw.news_id = nwt.news_id"
					+ " where nw.news_id = ?";
		
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
			logger.info("Der Datenbank treiber wurde nicht gefunden. - " + e.getLocalizedMessage());
            e.printStackTrace();
		} catch (SQLException e) {
			logger.info("SQL Fehler - " + e.getLocalizedMessage());
            e.printStackTrace();
		} 
		
		return massage;
		
	}
	
	public static void newNewsEntry(String title, String text, String teaser, Image img) {
		
		try{
			
			MySqlConnection.createConnection();
		
			String queryNews = "Insert into news (news_id, news_title, news_teaser, news_image, change_date, creation_date) values (?, ?, ?, ?, ?, ?)";
			String queryNewsText = "Insert into news_text (news_id, news_text) values (?, ?)";
			
			int id = HelloWorldService.generateId();
			
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryNews)){
				int counter = 1;
				stmt.setInt(counter++, id);
				stmt.setString(counter++, title);
				stmt.setString(counter++, teaser);
				stmt.setBlob(counter++, (Blob) img);
				stmt.setLong(counter++, System.currentTimeMillis());
				stmt.setLong(counter++, 0);
				
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
			logger.info("Der Datenbank treiber wurde nicht gefunden. - " + e.getLocalizedMessage());
            e.printStackTrace();
		} catch (SQLException e) {
			logger.info("SQL Fehler - " + e.getLocalizedMessage());
            e.printStackTrace();
		} 
		
	}
	
	public static void updateNewsEntry(String title, String text, String teaser, Image img) {
		
		try{
			
			MySqlConnection.createConnection();
		
			String queryNews = "Insert into news (news_id, news_title, news_teaser, news_image, change_date, creation_date) values (?, ?, ?, ?, ?, ?)";
			String queryNewsText = "Insert into news_text (news_id, news_text) values (?, ?)";
			
			int id = HelloWorldService.generateId();
			
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryNews)){
				int counter = 1;
				stmt.setInt(counter++, id);
				stmt.setString(counter++, title);
				stmt.setString(counter++, teaser);
				stmt.setBlob(counter++, (Blob) img);
				stmt.setLong(counter++, System.currentTimeMillis());
				stmt.setLong(counter++, 0);
				
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
			logger.info("Der Datenbank treiber wurde nicht gefunden. - " + e.getLocalizedMessage());
            e.printStackTrace();
		} catch (SQLException e) {
			logger.info("SQL Fehler - " + e.getLocalizedMessage());
            e.printStackTrace();
		} 
		
	}

}
