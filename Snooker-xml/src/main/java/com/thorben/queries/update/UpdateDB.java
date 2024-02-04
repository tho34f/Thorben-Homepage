package com.thorben.queries.update;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.thorben.queries.AbstractQuerries;
import com.thorben.queries.MySql;


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
