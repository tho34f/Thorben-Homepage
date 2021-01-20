package com.mkyong.helloworld.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mkyong.helloworld.snooker.Tournament;
import com.mkyong.helloworld.snooker.Tournament_Season;
import com.mkyong.helloworld.snooker.Spieler;

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

    }
    
    public static void creatTournamentList(String nameOfTable, Tournament_Season tournamentSeason) {
    	try {
        	//Verbindung Datenbank erzeugen
			createConnection();
			
	        // Statement mit Benennung der Tablle
	        String query = "SELECT * FROM " + nameOfTable;
	        try(Statement stmt = getConnectionSnooker().createStatement()){
		        ResultSet rs = stmt.executeQuery(query);
		        
		        // Tournement Erzeugen und in einer Liste speichern
		        while (rs.next()) {
		        	rs.toString();
		        	
		        	String tournamentname = rs.getString(2);
		        	int playernumber = Integer.parseInt(rs.getString(3));
		        	int roundnumber = Integer.parseInt(rs.getString(4));
		        	float weight = Float.parseFloat(rs.getString(5));
		        	
		        	Tournament tournament = new Tournament(tournamentname,weight, playernumber, roundnumber);
		        	tournamentSeason.registerTournament(tournament);
	
		        }
		        
	            // Ich schließe die Streams wieder und gebe die Tabelle wieder frei.
	            rs.close();

	        }
	        
            getConnectionSnooker().close();
	        
		} catch (ClassNotFoundException e) {
			((ServletContext) logger).log("Der Datenbank treiber wurde nicht gefunden. - "
                    + e.getLocalizedMessage());
            e.printStackTrace();
		} catch (SQLException e) {
			((ServletContext) logger).log("SQL Fehler - " + e.getLocalizedMessage());
            e.printStackTrace();
		} 
        
    }
    
    public static void creatPlayerList(String nameOfTable, Tournament_Season tournamentSeason) {
    	
    	try {
	    	//Verbindung Datenbank erzeugen
	    	createConnection();
	        // Statement mit Benennung der Tablle
	        String query = "SELECT * FROM " + nameOfTable;
	        
	        try(Statement stmt = getConnectionSnooker().createStatement()){
	        ResultSet rs = stmt.executeQuery(query);
	        // Spieler Erzeugen und in einer Liste speichern
	        while (rs.next()) {
	        	rs.toString();
	        	
	        	String firstName= rs.getString(2);
	        	String lastName = rs.getString(3);
	        	int worldranking = Integer.parseInt(rs.getString(4));
	        	int provisionalranking = Integer.parseInt(rs.getString(5));
	        	float winPercentage = Float.parseFloat(rs.getString(6));
	        	int centuryBreaks = Integer.parseInt(rs.getString(7));
	        	int age = Integer.parseInt(rs.getString(8));
	        	int profiYears = Integer.parseInt(rs.getString(9));
	        	
	        	Spieler player = new Spieler(firstName,lastName,worldranking,provisionalranking, winPercentage, centuryBreaks, age, profiYears);
	        	tournamentSeason.registerPlayer(player);
	
	        }
	        
	        // Ich schließe die Streams wieder und gebe die Tabelle wieder frei.
	        rs.close();
	        }
	        
	        getConnectionSnooker().close();
    	} catch (ClassNotFoundException e) {
			((ServletContext) logger).log("Der Datenbank treiber wurde nicht gefunden. - "
                    + e.getLocalizedMessage());
            e.printStackTrace();
		} catch (SQLException e) {
			((ServletContext) logger).log("SQL Fehler - " + e.getLocalizedMessage());
            e.printStackTrace();
		} 
        
        
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
