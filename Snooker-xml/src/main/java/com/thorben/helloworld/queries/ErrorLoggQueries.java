package com.thorben.helloworld.queries;

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
import com.thorben.helloworld.snooker.ErrorMassage;

public class ErrorLoggQueries {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorLoggQueries.class);
	
    private ErrorLoggQueries() {
    	
    	throw new IllegalStateException("Utility Class");
    	
    }
    
	public static Set<ErrorMassage> loadErrorLogList() {
		
		Set<ErrorMassage> errorList = new HashSet<>();
		
		try{
			
			MySqlConnection.createConnection();
			
			String queryErrorLog = "SELECT * FROM error_log";
		
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryErrorLog)){
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
		
		return errorList;
		
	}
	
	public static ErrorMassage loadError(int newsId) {
		
		ErrorMassage massage = new ErrorMassage();
		
		try{
			
			MySqlConnection.createConnection();
			
			String queryError = "SELECT * FROM error_log"
					+ " WHERE error_id = ?";
		
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryError)){
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
		
		return massage;
		
	}
	
	public static void newErrorLogEntry(String title, String description) {
		
		try{
			
			MySqlConnection.createConnection();
		
			String queryError = "INSERT INTO error_log VALUES (?, ?, ?, ?)";
			
			int id = ThorbenDierkesService.generateId();
			
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryError)){
				int counter = 1;
				stmt.setInt(counter++, id);
				stmt.setString(counter++, title);
				stmt.setString(counter++, description);
				stmt.setLong(counter++, System.currentTimeMillis());
				
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
