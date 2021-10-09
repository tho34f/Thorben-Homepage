package com.thorben.helloworld.queries;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;


public class UpdateDB extends AbstractQuerries {
	
    public UpdateDB(MySql sql) {
    	
    	super(sql);
    	
    }

	public void updateDatenbank(List<String> provisionalRanking) {
		try (Connection con = getSql().getDs().getConnection()){
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	    

}
