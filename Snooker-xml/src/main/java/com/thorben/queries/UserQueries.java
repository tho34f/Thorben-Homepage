package com.thorben.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.thorben.objects.User;
import com.thorben.service.DateConverter;

public class UserQueries extends AbstractQuerries {
	
	private static final String FIRST_NAME = "user_firstname";
	private static final String LAST_NAME = "user_lastname";
	
    public UserQueries(MySql sql) {
    	
    	super(sql);
    	
    }
    
	public Boolean createUser(String firstName, String lastName, String password, String loginName) {
		
		Boolean isCreate = false;
		
		try(Connection con = getSql().getDs().getConnection()){
			
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
		
		} catch (SQLException e) {
			handleSqlException(e);
		} 
		
		return isCreate;
	}
	
	public Boolean checkLogin(String username, String password) {
		
		Boolean isLoginOk = false;
		
		try(Connection con = getSql().getDs().getConnection()){
			con.setAutoCommit(false);
		
			String queryUser = "SELECT * FROM user where user_login = '" + username + "' and user_password = SHA2('" + password + "',224)";
			try(Statement stmt = con.createStatement()){
		        try(ResultSet rs = stmt.executeQuery(queryUser)){
			        if(rs.next()) {
			        	isLoginOk = true;
			        } 
		        }
			}
		} catch (SQLException e) {
			handleSqlException(e);
		}  
		
		return isLoginOk;
	}
	
	public User loadUser(int userId) {
		
		User user = new User();
		
		try(Connection con = getSql().getDs().getConnection()){
			con.setAutoCommit(false);
		
			String queryUser = "SELECT * FROM user where user_id = " + userId;

		
			try(Statement stmt = con.createStatement()){
		        try(ResultSet rs = stmt.executeQuery(queryUser)){
			        while(rs.next()) {
			        	user.setId(userId);
			        	user.setFirstName(rs.getString(FIRST_NAME));
			        	user.setLastName(rs.getString(LAST_NAME));
			        	user.setUserLogin(rs.getString("user_login"));
			        	user.setCreationDate(rs.getLong("creation_date"));
			        	user.setCreationDateAsString(DateConverter.long2Date(user.getCreationDate(),1));
			        } 
		        }
		        
			}
		
		} catch (SQLException e) {
			handleSqlException(e);
		}  
		
		return user;
	}
	
	public Set<User> loadUserList() {
		
		Set<User> userList = new HashSet<>();
		
		try(Connection con = getSql().getDs().getConnection()){
			
			con.setAutoCommit(false);
			
			String queryNews = "SELECT * FROM USER"; 
		
			try(PreparedStatement stmt = con.prepareStatement(queryNews)){
		        try(ResultSet rs = stmt.executeQuery()){
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
		        }
		        
			}
		
		} catch (SQLException e) {
			handleSqlException(e);
		} 
		
		return userList;
		
	}

}
