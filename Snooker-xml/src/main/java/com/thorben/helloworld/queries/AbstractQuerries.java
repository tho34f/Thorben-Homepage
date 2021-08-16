package com.thorben.helloworld.queries;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thorben.helloworld.service.ThorbenDierkes;
import com.thorben.helloworld.service.ThorbenDierkesLogger;

public class AbstractQuerries {
	
	private DataSource ds;
	private MySql sql;
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractQuerries.class);
	private ThorbenDierkesLogger log = new ThorbenDierkesLogger();
	
	public AbstractQuerries(MySql sql, DataSource ds) {
		this.ds = ds;
		this.sql = sql;
	}
	
	public DataSource getDataSource() throws NamingException {
    	
        try {
        	Context ctx = new InitialContext();
        	ds = ((DataSource)ctx.lookup("java:comp/env/jdbc/thorbenDB"));
        } catch (NamingException e) {
        	String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_DATA_SOURCE).append(e.getLocalizedMessage()).toString();
  			log.errorLogWithTrace(ThorbenDierkes.DATA_SOURCE, erroeMessage, e);
       }
        
        logger.info("Verbindung mit Datenbank hergestellt.");
        
        return ds;

    }

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public MySql getSql() {
		return sql;
	}

	public void setSql(MySql sql) {
		this.sql = sql;
	}

}
