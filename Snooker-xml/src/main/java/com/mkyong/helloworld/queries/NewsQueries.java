package com.mkyong.helloworld.queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mkyong.helloworld.snooker.News;

public class NewsQueries {
	
private static final Logger logger = LoggerFactory.getLogger(NewsQueries.class);
	
    private NewsQueries() {
    	
    	throw new IllegalStateException("Utility Class");
    	
    }
    
	public static void loadNews(Set<News> newsList) {
		
		try{
			
			MySqlConnection.createConnection();
			
			News massage = new News();
			
			String queryNews = "select nw.news_id, nw.news_title, nw.news_teaser, nw.news_image, nwt.news_text from news nw left join  news_text nwt on nw.news_id = nwt.news_id";
		
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryNews)){
		        ResultSet rs = stmt.executeQuery();
		        
		        if(rs.next()) {
		        	massage.setId(rs.getInt("nw.news_id"));
		        	massage.setTeaser(rs.getString("nw.news_teaser"));
		        	massage.setText(rs.getString("nwt.news_text"));
		        	massage.setTitle(rs.getString("nw.news_title"));
		        	massage.setImg(null);
		        	newsList.add(massage);
		        	
		        } 
		        
		        rs.close();
		        
			}
		 
			MySqlConnection.getConnectionSnooker().close();
		
		} catch (ClassNotFoundException e) {
			((ServletContext) logger).log("Der Datenbank treiber wurde nicht gefunden. - "
                    + e.getLocalizedMessage());
            e.printStackTrace();
		} catch (SQLException e) {
			((ServletContext) logger).log("SQL Fehler - " + e.getLocalizedMessage());
            e.printStackTrace();
		} 
		
	}

}
