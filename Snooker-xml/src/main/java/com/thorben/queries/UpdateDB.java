package com.thorben.queries;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class UpdateDB extends AbstractQuerries {
	
    public UpdateDB(MySql sql) {
    	
    	super(sql);
    	
    }

	public void updateDatenbank(List<String> provisionalRanking) {
		try (Connection con = getSql().getDs().getConnection()){
			con.setAutoCommit(false);
		} catch (SQLException e) {
			handleSqlException(e);
		}
			
	}
	    

}
