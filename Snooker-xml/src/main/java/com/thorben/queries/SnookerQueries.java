package com.thorben.queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.thorben.objects.snooker.Spieler;
import com.thorben.objects.snooker.Tournament;
import com.thorben.objects.snooker.TournamentSeason;

public class SnookerQueries extends AbstractQuerries {
	
    public SnookerQueries(MySql sql) {
    	
    	super(sql);
    	
    }
    
    public void creatTournamentList(String nameOfTable, TournamentSeason tournamentSeason){
    	try(Connection con = getSql().getDs().getConnection()) {
        	
			con.setAutoCommit(false);
			
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
		        
	            // Ich schlie�e die Streams wieder und gebe die Tabelle wieder frei.
	            rs.close();

	        }
	        
		} catch (SQLException e) {
			handleSqlException(e);
		} 
        
    }
    
    public void creatPlayerList(String nameOfTable, TournamentSeason tournamentSeason)  {
    	
    	try(Connection con = getSql().getDs().getConnection()) {

    		con.setAutoCommit(false);
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
	        
	        // Ich schlie�e die Streams wieder und gebe die Tabelle wieder frei.
	        rs.close();
	        }
    	} catch (SQLException e) {
    		handleSqlException(e);
		} 

    }

}
