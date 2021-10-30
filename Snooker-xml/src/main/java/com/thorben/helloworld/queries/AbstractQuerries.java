package com.thorben.helloworld.queries;

import java.sql.SQLException;

import com.thorben.helloworld.service.ThorbenDierkes;
import com.thorben.helloworld.service.ThorbenDierkesLogger;

public class AbstractQuerries {
	
	private MySql sql;
	private ThorbenDierkesLogger logger = new ThorbenDierkesLogger();
	
	public AbstractQuerries(MySql sql) {
		this.sql = sql;
	}
	
	public void handleSqlException(SQLException e) {
		String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
		logger.errorLogWithTrace(ThorbenDierkes.SQL_FEHLER, erroeMessage, e);
	}

	public MySql getSql() {
		return sql;
	}

	public void setSql(MySql sql) {
		this.sql = sql;
	}

}
