package com.thorben.helloworld.queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.thorben.helloworld.service.ThorbenDierkes;
import com.thorben.helloworld.service.ThorbenDierkesLogger;
import com.thorben.helloworld.snooker.User;

public class UserQueries {
	
    private UserQueries() {
    	
    	throw new IllegalStateException("Utility Class");
    	
    }
    
	public static Boolean createUser(String firstName, String lastName, String password, String loginName) {
		
		Boolean isCreate = false;
		
		try{
			
			MySqlConnection.createConnection();
		
			String queryUser = "INSERT INTO user (user_firstname, user_lastname, user_password, user_login) "
					+ "VALUES (?,?,SHA2(" + password + ",224) ,?)";
		
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryUser)){
				int counter = 1;
				stmt.setString(counter++, firstName);
				stmt.setString(counter++, lastName);
				stmt.setString(counter++, loginName);
				isCreate = stmt.execute();
		        
			}
		 
			MySqlConnection.getConnectionSnooker().close();
		
		} catch (ClassNotFoundException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE).append(e.getLocalizedMessage()).toString();
			ThorbenDierkesLogger.errorLogWithTrace("Datenbanktreiber", erroeMessage, e);
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			ThorbenDierkesLogger.errorLogWithTrace("SQL - Fehler", erroeMessage, e);
		} 
		
		return isCreate;
	}
	
	public static Boolean checkLogin(User loginUser) {
		
		Boolean isLoginOk = false;
		
		try{
			
			MySqlConnection.createConnection();
		
			String queryUser = "SELECT * FROM user where user_login = '" + loginUser.getUserLogin() + "' and user_password = SHA2('" + loginUser.getPassword() + "',224)";
		
			try(Statement stmt = MySqlConnection.getConnectionSnooker().createStatement()){
		        ResultSet rs = stmt.executeQuery(queryUser);
		        
		        while(rs.next()) {
		        	isLoginOk = true;
		        	loginUser.setUserId(rs.getInt("user_id"));
		        	loginUser.setFirstName(rs.getString("user_firstname"));
		        	loginUser.setLastName(rs.getString("user_lastname"));
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
		
		return isLoginOk;
	}

}
