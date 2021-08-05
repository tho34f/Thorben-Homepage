package com.thorben.helloworld.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thorben.helloworld.service.DateConverter;
import com.thorben.helloworld.service.ThorbenDierkesService;
import com.thorben.helloworld.service.ThorbenDierkes;
import com.thorben.helloworld.service.ThorbenDierkesLogger;
import com.thorben.helloworld.snooker.ErrorMassage;
import com.thorben.helloworld.web.BackendController;

public class ErrorLoggQueries extends AbstractQuerries {
	
	private final Logger logger = LoggerFactory.getLogger(BackendController.class);
	
	 public ErrorLoggQueries(MySql sql, DataSource ds) {
	    	
	    	super(sql, ds);
	    	
	    }
    
	public Set<ErrorMassage> loadErrorLogList() throws SQLException, NamingException {
		
		Set<ErrorMassage> errorList = new HashSet<>();
		
		try{
			
			Connection con = getDataSource().getConnection();
			
			String queryErrorLog = "SELECT * FROM error_log";
		
			try(PreparedStatement stmt = con.prepareStatement(queryErrorLog)){
		        ResultSet rs = stmt.executeQuery();
		        
		        while(rs.next()) {
					ErrorMassage massage = new ErrorMassage();
		        	massage.setId(rs.getInt("error_id"));
		        	massage.setTitle(rs.getString("error_title"));
		        	massage.setCreationDate(rs.getLong("creation_date"));
		        	massage.setCreationDateAsString(DateConverter.long2Date(massage.getCreationDate(),1));
		        	massage.setDescription("error_description");
		        	
		        	errorList.add(massage);
		        	
		        } 
		        
		        rs.close();
		        
			}
		 
			con.close();
		
		} catch (NamingException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE).append(e.getLocalizedMessage()).toString();
			logger.error(erroeMessage, e);
			e.printStackTrace();
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.error(erroeMessage, e);
			e.printStackTrace();
		} 
		
		return errorList;
		
	}
	
	public ErrorMassage loadError(int newsId) throws SQLException, NamingException {
		
		ErrorMassage massage = new ErrorMassage();
		
		try{
			
			Connection con = getDataSource().getConnection();
			
			String queryError = "SELECT * FROM error_log"
					+ " WHERE error_id = ?";
		
			try(PreparedStatement stmt = con.prepareStatement(queryError)){
				stmt.setInt(1, newsId);
		        ResultSet rs = stmt.executeQuery();
		        
		        if(rs.next()) {
		        	massage.setId(rs.getInt("error_id"));
		        	massage.setTitle(rs.getString("error_title"));
		        	massage.setCreationDate(rs.getLong("creation_date"));
		        	massage.setCreationDateAsString(DateConverter.long2Date(massage.getCreationDate(),1));
		        	massage.setDescription(rs.getString("error_description"));
		        	
		        } 
		        
		        rs.close();
		        
			}
		 
			con.close();
		
		} catch (NamingException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE).append(e.getLocalizedMessage()).toString();
			logger.error(erroeMessage, e);
			e.printStackTrace();
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.error(erroeMessage, e);
			e.printStackTrace();
		}  
		
		return massage;
		
	}
	
	public void newErrorLogEntry(String title, String description) throws SQLException, NamingException {
		
		try{
			
			Connection con = getDataSource().getConnection();
		
			String queryError = "INSERT INTO error_log VALUES (?, ?, ?, ?)";
			
			int id = ThorbenDierkesService.generateId();
			
			try(PreparedStatement stmt = con.prepareStatement(queryError)){
				int counter = 1;
				stmt.setInt(counter++, id);
				stmt.setString(counter++, title);
				stmt.setString(counter++, description);
				stmt.setLong(counter++, System.currentTimeMillis());
				
		        stmt.execute();
		        
			}
			
			con.close();
		} catch (NamingException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE).append(e.getLocalizedMessage()).toString();
			logger.error(erroeMessage, e);
			e.printStackTrace();
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.error(erroeMessage, e);
			e.printStackTrace();
		} 
		
	}

}
