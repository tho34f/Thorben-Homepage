package com.thorben.helloworld.queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thorben.helloworld.service.ThorbenDierkes;
import com.thorben.helloworld.snooker.User;

public class UserQueries {
	
	private static final Logger logger = LoggerFactory.getLogger(UserQueries.class);
	
    private UserQueries() {
    	
    	throw new IllegalStateException("Utility Class");
    	
    }
    
	public static Boolean createUser(String firstName, String lastName, String password, String loginName) {
		
		Boolean isCreate = false;
		
		try{
			
			MySqlConnection.createConnection();
		
			String queryUser = "ISERT INTO user (user_firstname, user_lastname, user_password, user_login) "
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
			((ServletContext) logger).log(ThorbenDierkes.ERROR_MESSAGE
                    + e.getLocalizedMessage());
            e.printStackTrace();
		} catch (SQLException e) {
			((ServletContext) logger).log(ThorbenDierkes.ERROR_MESSAGE_SQL + e.getLocalizedMessage());
            e.printStackTrace();
		} 
		
		return isCreate;
	}
	
	public static Boolean checkLogin(User loginUser) {
		
		Boolean isLoginOk = false;
		
		try{
			
			MySqlConnection.createConnection();
		
			String queryUser = "SELECT * FROM user where user_login = ? and user_password = SHA2(" + loginUser.getPassword() + ",224)";
		
			try(PreparedStatement stmt = MySqlConnection.getConnectionSnooker().prepareStatement(queryUser)){
				stmt.setString(1, loginUser.getUserLogin());
		        ResultSet rs = stmt.executeQuery();
		        
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
			((ServletContext) logger).log(ThorbenDierkes.ERROR_MESSAGE
                    + e.getLocalizedMessage());
            e.printStackTrace();
		} catch (SQLException e) {
			((ServletContext) logger).log(ThorbenDierkes.ERROR_MESSAGE_SQL + e.getLocalizedMessage());
            e.printStackTrace();
		} 
		
		return isLoginOk;
	}

}
