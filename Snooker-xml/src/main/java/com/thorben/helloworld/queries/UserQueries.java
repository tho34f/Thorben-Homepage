package com.thorben.helloworld.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.thorben.helloworld.service.ThorbenDierkes;
import com.thorben.helloworld.service.ThorbenDierkesLogger;
import com.thorben.helloworld.snooker.User;

public class UserQueries extends AbstractQuerries {
	
	private ThorbenDierkesLogger logger = new ThorbenDierkesLogger();
	
    public UserQueries(MySql sql, DataSource ds) {
    	
    	super(sql, ds);
    	
    }
    
	public Boolean createUser(String firstName, String lastName, String password, String loginName) throws SQLException, NamingException {
		
		Boolean isCreate = false;
		
		try{
			
			Connection con = getDataSource().getConnection();
		
			String queryUser = "INSERT INTO user (user_firstname, user_lastname, user_password, user_login) "
					+ "VALUES (?,?,SHA2(" + password + ",224) ,?)";
		
			try(PreparedStatement stmt = con.prepareStatement(queryUser)){
				int counter = 1;
				stmt.setString(counter++, firstName);
				stmt.setString(counter++, lastName);
				stmt.setString(counter++, loginName);
				isCreate = stmt.execute();
		        
			}
		 
			con.close();
		
		} catch (NamingException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace("Datenbanktreiber", erroeMessage, e);
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace("SQL - Fehler", erroeMessage, e);
		} 
		
		return isCreate;
	}
	
	public Boolean checkLogin(User loginUser) throws SQLException, NamingException {
		
		Boolean isLoginOk = false;
		
		try{
			
			Connection con = getDataSource().getConnection();
		
			String queryUser = "SELECT * FROM user where user_login = '" + loginUser.getUserLogin() + "' and user_password = SHA2('" + loginUser.getPassword() + "',224)";
		
			try(Statement stmt = con.createStatement()){
		        ResultSet rs = stmt.executeQuery(queryUser);
		        
		        while(rs.next()) {
		        	isLoginOk = true;
		        	loginUser.setUserId(rs.getInt("user_id"));
		        	loginUser.setFirstName(rs.getString("user_firstname"));
		        	loginUser.setLastName(rs.getString("user_lastname"));
		        } 
		        
		        rs.close();
		        
			}
		 
			con.close();
		
		} catch (NamingException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace("Datenbanktreiber", erroeMessage, e);
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace("SQL - Fehler", erroeMessage, e);
		}  
		
		return isLoginOk;
	}

}
