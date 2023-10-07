package com.thorben.queries;

import java.sql.SQLException;

import com.thorben.service.ThorbenDierkes;
import com.thorben.service.ThorbenDierkesLogger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AbstractQuerries {
	
	private static final ThorbenDierkesLogger logger = new ThorbenDierkesLogger();
	
	private MySql sql;
	
	public void handleSqlException(SQLException e) {
		String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
		logger.errorLogWithTrace(ThorbenDierkes.SQL_FEHLER, erroeMessage, e);
	}

}
