package com.thorben.helloworld.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.thorben.helloworld.service.DateConverter;
import com.thorben.helloworld.service.ThorbenDierkes;
import com.thorben.helloworld.service.ThorbenDierkesLogger;
import com.thorben.helloworld.snooker.User;

public class UserQueries extends AbstractQuerries {
	
	private ThorbenDierkesLogger logger = new ThorbenDierkesLogger();
	private static final String FIRST_NAME = "user_firstname";
	private static final String LAST_NAME = "user_lastname";
	
    public UserQueries(MySql sql, DataSource ds) {
    	
    	super(sql, ds);
    	
    }
    
	public Boolean createUser(String firstName, String lastName, String password, String loginName) {
		
		Boolean isCreate = false;
		
		try(Connection con = getDataSource().getConnection()){
			
			con.setAutoCommit(false);
		
			String queryUser = "INSERT INTO user (user_firstname, user_lastname, user_password, user_login, creation_date) "
					+ "VALUES (?,?,SHA2(" + password + ",224) ,?,?)";
		
			try(PreparedStatement stmt = con.prepareStatement(queryUser)){
				int counter = 1;
				stmt.setString(counter++, firstName);
				stmt.setString(counter++, lastName);
				stmt.setString(counter++, loginName);
				stmt.setLong(counter++, System.currentTimeMillis());
				isCreate = stmt.execute();
		        
			}
		
		} catch (NamingException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_DB_TREIBER).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace(ThorbenDierkes.TREIBER, erroeMessage, e);
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace(ThorbenDierkes.SQL_FEHLER, erroeMessage, e);
		} 
		
		return isCreate;
	}
	
	public Boolean checkLogin(User loginUser) {
		
		Boolean isLoginOk = false;
		
		try(Connection con = getDataSource().getConnection()){
			con.setAutoCommit(false);
		
			String queryUser = "SELECT * FROM user where user_login = '" + loginUser.getUserLogin() + "' and user_password = SHA2('" + loginUser.getPassword() + "',224)";
		
			try(Statement stmt = con.createStatement()){
		        ResultSet rs = stmt.executeQuery(queryUser);
		        
		        while(rs.next()) {
		        	isLoginOk = true;
		        	loginUser.setId(rs.getInt("user_id"));
		        	loginUser.setFirstName(rs.getString(FIRST_NAME));
		        	loginUser.setLastName(rs.getString(LAST_NAME));
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
		
		return isLoginOk;
	}
	
	public User loadUser(int userId) {
		
		User user = new User();
		
		try(Connection con = getDataSource().getConnection()){
			con.setAutoCommit(false);
		
			String queryUser = "SELECT * FROM user where user_id = '" + userId;

		
			try(Statement stmt = con.createStatement()){
		        ResultSet rs = stmt.executeQuery(queryUser);
		        
		        while(rs.next()) {
		        	user.setId(userId);
		        	user.setFirstName(rs.getString(FIRST_NAME));
		        	user.setLastName(rs.getString(LAST_NAME));
		        	user.setUserLogin(rs.getString("user_login"));
		        	user.setCreationDate(rs.getLong("creation_date"));
		        	user.setCreationDateAsString(DateConverter.long2Date(user.getCreationDate(),1));
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
		
		return user;
	}
	
	public Set<User> loadUserList() {
		
		Set<User> userList = new HashSet<>();
		
		try(Connection con = getDataSource().getConnection()){
			
			con.setAutoCommit(false);
			
			String queryNews = "SELECT * FROM USER"; 
		
			try(PreparedStatement stmt = con.prepareStatement(queryNews)){
		        ResultSet rs = stmt.executeQuery();
		        
		        while(rs.next()) {
					User user = new User();
					
					user.setId(rs.getInt("user_id"));
		        	user.setFirstName(rs.getString(FIRST_NAME));
		        	user.setLastName(rs.getString(LAST_NAME));
		        	user.setUserLogin(rs.getString("user_login"));
		        	user.setCreationDate(rs.getLong("creation_date"));
		        	user.setCreationDateAsString(DateConverter.long2Date(user.getCreationDate(),1));
					
		        	userList.add(user);
		        	
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
		
		return userList;
		
	}

}
