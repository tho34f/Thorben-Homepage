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
	
	private MySql sql;
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractQuerries.class);
	private ThorbenDierkesLogger log = new ThorbenDierkesLogger();
	
	public AbstractQuerries(MySql sql) {
		this.sql = sql;
	}

	public MySql getSql() {
		return sql;
	}

	public void setSql(MySql sql) {
		this.sql = sql;
	}

}
