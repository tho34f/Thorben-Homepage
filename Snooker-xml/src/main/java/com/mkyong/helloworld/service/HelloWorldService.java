package com.mkyong.helloworld.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mkyong.helloworld.snooker.Tournament;
import com.mkyong.helloworld.snooker.Tournament_Season;
import com.mkyong.helloworld.snooker.Spieler;
import com.mkyong.helloworld.web.WelcomeController;

@Service
public class HelloWorldService {

	private static final Logger logger = LoggerFactory.getLogger(HelloWorldService.class);
	
	
	public static Spieler simulation(Tournament tournament, String participationPlayer) {
		
		String[] arrayPlayer = participationPlayer.split(",");
		
		Spieler gewinner = null;
		
		return gewinner;
	
	}
	
	public static Tournament_Season creatSeason(int year) {
		
		Tournament_Season season = new Tournament_Season(year);
		
        // Ergebnisse anzeigen.
		MySqlConnection.creatTournamentList("tournament", season);
		MySqlConnection.creatPlayerList("snookerplayers", season);

		return season;
	}
	
	public static void setSeason(String number, Tournament_Season season, final HttpServletRequest request) {
		
		if(!number.isEmpty()) {
			int number2 = Integer.parseInt(number);
			season =  creatSeason(number2);
			WelcomeController.getSeasons().add(season);
		}
		
		request.getSession().getServletContext().setAttribute("seasions", WelcomeController.getSeasons());	

	}
	
	public static void errorMessage(final HttpServletRequest request) {
		
		String errorMessageSaison = "Es konnte keine Saison erzeugt werden";
		
		request.getSession().getServletContext().setAttribute("errormassage", errorMessageSaison);
		
	}
	

}