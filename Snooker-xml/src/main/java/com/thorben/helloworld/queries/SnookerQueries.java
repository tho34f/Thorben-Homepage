package com.thorben.helloworld.queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.thorben.helloworld.service.ThorbenDierkes;
import com.thorben.helloworld.service.ThorbenDierkesLogger;
import com.thorben.helloworld.snooker.Spieler;
import com.thorben.helloworld.snooker.Tournament;
import com.thorben.helloworld.snooker.TournamentSeason;

public class SnookerQueries extends AbstractQuerries {
	
	private ThorbenDierkesLogger logger = new ThorbenDierkesLogger();
	
    public SnookerQueries(MySql sql, DataSource ds) {
    	
    	super(sql, ds);
    	
    }
    
    public void creatTournamentList(String nameOfTable, TournamentSeason tournamentSeason) throws SQLException, NamingException{
    	try {
        	//Verbindung Datenbank erzeugen
			Connection con = getDataSource().getConnection();
			
	        // Statement mit Benennung der Tablle
	        String query = "SELECT * FROM " + nameOfTable;
	        try(Statement stmt = con.createStatement()){
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
	        
	        con.close();
	        
		} catch (NamingException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace("Datenbanktreiber", erroeMessage, e);
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace("SQL - Fehler", erroeMessage, e);
		} 
        
    }
    
    public void creatPlayerList(String nameOfTable, TournamentSeason tournamentSeason) throws SQLException, NamingException  {
    	
    	try {
	    	//Verbindung Datenbank erzeugen
    		Connection con = getDataSource().getConnection();
	        // Statement mit Benennung der Tablle
	        String query = "SELECT * FROM " + nameOfTable;
	        
	        try(Statement stmt = con.createStatement()){
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
	        
	        con.close();
    	} catch (NamingException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace("Datenbanktreiber", erroeMessage, e);
		} catch (SQLException e) {
			String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_SQL).append(e.getLocalizedMessage()).toString();
			logger.errorLogWithTrace("SQL - Fehler", erroeMessage, e);
		} 

    }

}
