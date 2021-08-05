package com.thorben.helloworld.queries;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;


public class UpdateDB extends AbstractQuerries {
	
    public UpdateDB(MySql sql, DataSource ds) {
    	
    	super(sql, ds);
    	
    }

	public void updateDatenbank(List<String> provisionalRanking) {
		try {
			Connection con = getDataSource().getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
			
	}
	    

}
