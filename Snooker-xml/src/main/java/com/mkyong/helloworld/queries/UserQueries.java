package com.mkyong.helloworld.queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mkyong.helloworld.snooker.User;

public class UserQueries {
	
	private static final Logger logger = LoggerFactory.getLogger(UserQueries.class);
	
    private UserQueries() {
    	
    	throw new IllegalStateException("Utility Class");
    	
    }
	
	public static Boolean checkLogin(User loginUser) {
		
		Boolean isLoginOk = false;
		
		try{
			
			MySqlConnection.createConnection();
		
			String queryUser = "SELECT * FROM user where user_login = ? and user_password = ?";
		
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryUser)){
				int counter = 1;
				stmt.setString(counter++, loginUser.getUserLogin());
				stmt.setString(counter++, loginUser.getPassword());
		        ResultSet rs = stmt.executeQuery();
		        
		        if(rs.next()) {
		        	isLoginOk = true;
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
		
		return isLoginOk;
	}

}
