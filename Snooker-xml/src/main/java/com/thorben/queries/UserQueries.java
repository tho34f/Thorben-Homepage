package com.thorben.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.thorben.objects.User;
import com.thorben.service.DateConverter;

public class UserQueries extends AbstractQuerries {
	
	private static final String FIRST_NAME = "user_firstname";
	private static final String LAST_NAME = "user_lastname";
	private static final String LOGIN = "user_login";
	
    public UserQueries(MySql sql) {
    	
    	super(sql);
    	
    }
    
	public Boolean createUser(String firstName, String lastName, String password, String loginName) {
		
		Boolean isCreate = false;
		
		try(Connection con = getSql().getDs().getConnection()){
			
			con.setAutoCommit(false);
		
			String queryUser = "INSERT INTO user (user_firstname, user_lastname, user_password, user_login, creation_date) "
					+ "VALUES (?,?,SHA2(?,224),?,?)";
		
			try(PreparedStatement pstmt = con.prepareStatement(queryUser)){
				int counter = 1;
				pstmt.setString(counter++, firstName);
				pstmt.setString(counter++, lastName);
				pstmt.setString(counter++, password);
				pstmt.setString(counter++, loginName);
				pstmt.setLong(counter++, System.currentTimeMillis());
				isCreate = pstmt.execute();
		        
			}
		} catch (SQLException e) {
			handleSqlException(e);
		} 
		
		return isCreate;
	}
	
	public User checkLogin(String username, String password) {
		
		User user = null;
		
		try(Connection con = getSql().getDs().getConnection()){
			con.setAutoCommit(false);
		
			String queryUser = "SELECT * FROM user where user_login = ? and user_password = SHA2(?,224)";
			try(PreparedStatement pstmt = con.prepareStatement(queryUser)){
				pstmt.setString(1, username);
				pstmt.setString(2, password);
		        try(ResultSet rs = pstmt.executeQuery()){
			        if(rs.next()) {
			        	user = new User(rs.getString(FIRST_NAME), rs.getString(LAST_NAME), rs.getString(LOGIN), rs.getInt("user_id"));
			        } 
		        }
			}
		} catch (SQLException e) {
			handleSqlException(e);
		}  
		
		return user;
	}
	
	public User loadUser(int userId) {
		
		User user = new User();
		
		try(Connection con = getSql().getDs().getConnection()){
			con.setAutoCommit(false);
		
			String queryUser = "SELECT * FROM user where user_id = ?";
			try(PreparedStatement pstmt = con.prepareStatement(queryUser)){
				pstmt.setInt(1, userId);
		        try(ResultSet rs = pstmt.executeQuery()){
			        while(rs.next()) {
			        	user.setId(userId);
			        	user.setFirstName(rs.getString(FIRST_NAME));
			        	user.setLastName(rs.getString(LAST_NAME));
			        	user.setUserLogin(rs.getString(LOGIN));
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
			        	user.setUserLogin(rs.getString(LOGIN));
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
