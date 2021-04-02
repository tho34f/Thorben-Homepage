package com.thorben.helloworld.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thorben.helloworld.snooker.Spieler;
import com.thorben.helloworld.snooker.Tournament;
import com.thorben.helloworld.snooker.TournamentSeason;

public class SnookerQueries {
	
private static final Logger logger = LoggerFactory.getLogger(SnookerQueries.class);
	
    private SnookerQueries() {
    	
    	throw new IllegalStateException("Utility Class");
    	
    }
    
    public static void creatTournamentList(String nameOfTable, TournamentSeason tournamentSeason) {
    	try {
        	//Verbindung Datenbank erzeugen
			MySqlConnection.createConnection();
			
	        // Statement mit Benennung der Tablle
	        String query = "SELECT * FROM " + nameOfTable;
	        try(Statement stmt = MySqlConnection.getConnectionSnooker().createStatement()){
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
		        
	            // Ich schlieﬂe die Streams wieder und gebe die Tabelle wieder frei.
	            rs.close();

	        }
	        
	        MySqlConnection.getConnectionSnooker().close();
	        
		} catch (ClassNotFoundException e) {
			((ServletContext) logger).log("Der Datenbank treiber wurde nicht gefunden. - "
                    + e.getLocalizedMessage());
            e.printStackTrace();
		} catch (SQLException e) {
			((ServletContext) logger).log("SQL Fehler - " + e.getLocalizedMessage());
            e.printStackTrace();
		} 
        
    }
    
    public static void creatPlayerList(String nameOfTable, TournamentSeason tournamentSeason) {
    	
    	try {
	    	//Verbindung Datenbank erzeugen
    		MySqlConnection.createConnection();
	        // Statement mit Benennung der Tablle
	        String query = "SELECT * FROM " + nameOfTable;
	        
	        try(Statement stmt = MySqlConnection.getConnectionSnooker().createStatement()){
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
	        
	        // Ich schlieﬂe die Streams wieder und gebe die Tabelle wieder frei.
	        rs.close();
	        }
	        
	        MySqlConnection.getConnectionSnooker().close();
    	} catch (ClassNotFoundException e) {
			((ServletContext) logger).log("Der Datenbank treiber wurde nicht gefunden. - "
                    + e.getLocalizedMessage());
            e.printStackTrace();
		} catch (SQLException e) {
			((ServletContext) logger).log("SQL Fehler - " + e.getLocalizedMessage());
            e.printStackTrace();
		} 
        
        
    }

}
