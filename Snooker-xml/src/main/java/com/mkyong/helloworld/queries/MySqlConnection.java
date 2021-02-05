package com.mkyong.helloworld.queries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MySqlConnection {
	
	private static final Logger logger = LoggerFactory.getLogger(MySqlConnection.class);
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connectionSnooker = null;
    
    private MySqlConnection() {
    	
    	throw new IllegalStateException("Utility Class");
    	
    }
	
    public static void createConnection() throws SQLException, ClassNotFoundException {
        // Treiber initialisieren
        Class.forName(DRIVER);
        // Uri für die Verbindung zu der Datenbank
        String mySqlUrl = "jdbc:mysql://localhost:3306/snooker?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
        // Verbindung herstellen.
        setConnectionSnooker(DriverManager.getConnection(mySqlUrl, "root", "MaraTeske30031994!"));
        logger.info("Verbindung mit Datenbank hergestellt.");

    }
    
    public static void closeProgramm() throws SQLException {
        getConnectionSnooker().close();
    }

	public static Connection getConnectionSnooker() {
		return connectionSnooker;
	}

	public static void setConnectionSnooker(Connection connectionSnooker) {
		MySqlConnection.connectionSnooker = connectionSnooker;
	}
}
