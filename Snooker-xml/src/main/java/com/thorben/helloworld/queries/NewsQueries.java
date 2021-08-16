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
import com.thorben.helloworld.snooker.News;

public class NewsQueries extends AbstractQuerries {
	
	private ThorbenDierkesLogger logger = new ThorbenDierkesLogger();
	
    public NewsQueries(MySql sql, DataSource ds) {
    	
    	super(sql, ds);
    	
    }
    
	public Set<News> loadNewsList() {
		
		Set<News> newsList = new HashSet<>();
		
		try(Connection con = getDataSource().getConnection()){
			
			con.setAutoCommit(false);
			
			String queryNews = "SELECT nw.news_id, nw.news_title, nw.news_teaser, nw.news_image, nwt.news_text, nw.change_date, nw.creation_date" 
					+ " FROM news nw LEFT JOIN news_text nwt ON nw.news_id = nwt.news_id ORDER BY nw.creation_date DESC";
		
			try(PreparedStatement stmt = con.prepareStatement(queryNews)){
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
		
		} catch (NamingException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_DB_TREIBER).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace(ThorbenDierkes.TREIBER, erroeMessage, e);
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace(ThorbenDierkes.SQL_FEHLER, erroeMessage, e);
		} 
		
		return newsList;
		
	}
	
	public News loadNews(int newsId) {
		
		News massage = new News();
		
		try(Connection con = getDataSource().getConnection()){
			
			con.setAutoCommit(false);
			
			String queryNews = "SELECT nw.news_id, nw.news_title, nw.news_teaser, nw.news_image, nwt.news_text, nw.change_date, nw.creation_date" 
					+ " FROM news nw LEFT JOIN news_text nwt ON nw.news_id = nwt.news_id"
					+ " WHERE nw.news_id = ?";
		
			try(PreparedStatement stmt = con.prepareStatement(queryNews)){
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
		
		} catch (NamingException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_DB_TREIBER).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace(ThorbenDierkes.TREIBER, erroeMessage, e);
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace(ThorbenDierkes.SQL_FEHLER, erroeMessage, e);
		} 
		
		return massage;
		
	}
	
	public boolean newNewsEntry(String title, String text, String teaser, Image img) {
		
		boolean isSave = false;
		
		try(Connection con = getDataSource().getConnection()){
			
			con.setAutoCommit(false);
		
			String queryNews = "INSERT INTO news (news_id, news_title, news_teaser, news_image, change_date, creation_date) VALUES (?, ?, ?, ?, ?, ?)";
			String queryNewsText = "INSERT INTO news_text (news_id, news_text) VALUES (?, ?)";
			
			ThorbenDierkesService tds = new ThorbenDierkesService();
			int id = tds.generateId();
			
			try(PreparedStatement stmt = con.prepareStatement(queryNews)){
				int counter = 1;
				stmt.setInt(counter++, id);
				stmt.setString(counter++, title);
				stmt.setString(counter++, teaser);
				stmt.setBlob(counter++, (Blob) img);
				stmt.setLong(counter++, 0);
				stmt.setLong(counter++, System.currentTimeMillis());
				
		        stmt.execute();
		        
			}
			
			try(PreparedStatement stmt = con.prepareStatement(queryNewsText)){
				int counter = 1;
				stmt.setInt(counter++, id);
				stmt.setString(counter++, text);
				
		        stmt.execute();
		        
			}
			
			isSave = true;
		} catch (NamingException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_DB_TREIBER).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace(ThorbenDierkes.TREIBER, erroeMessage, e);
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace(ThorbenDierkes.SQL_FEHLER, erroeMessage, e);
		} 
		
		return isSave;
		
	}
	
	public boolean updateNewsEntry(int newsId, String title, String text, String teaser, Image img) {
		
		boolean isUpdate = false;
		
		try(Connection con = getDataSource().getConnection()){
			
			con.setAutoCommit(false);
		
			String updateQuerry = "Update news AS nw LEFT JOIN news_text AS nwt ON nw.news_id = nwt.news_id" + 
					" SET nw.news_title=?, nw.news_teaser=?, nw.news_image=?, nw.change_date=?, nwt.news_text=?" + 
					" WHERE nw.news_id=?";
			
			try(PreparedStatement stmt = con.prepareStatement(updateQuerry)){
				int counter = 1;
				stmt.setString(counter++, title);
				stmt.setString(counter++, teaser);
				stmt.setBlob(counter++, (Blob) img);
				stmt.setLong(counter++, System.currentTimeMillis());
				stmt.setString(counter++, text);
				stmt.setInt(counter++, newsId);
				
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
