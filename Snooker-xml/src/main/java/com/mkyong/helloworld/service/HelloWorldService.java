package com.mkyong.helloworld.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mkyong.helloworld.queries.SnookerQueries;
import com.mkyong.helloworld.snooker.Tournament;
import com.mkyong.helloworld.snooker.TournamentSeason;
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
	
	public static TournamentSeason creatSeason(int year) {
		
		TournamentSeason season = new TournamentSeason(year);
		
        // Ergebnisse anzeigen.
		SnookerQueries.creatTournamentList("tournament", season);
		SnookerQueries.creatPlayerList("snookerplayers", season);

		return season;
	}
	
	public static void setSeason(String number, TournamentSeason season, final HttpServletRequest request) {
		
		if(!number.isEmpty()) {
			int number2 = Integer.parseInt(number);
			season =  creatSeason(number2);
			WelcomeController.getSeasons().add(season);
		}
		
		logger.info("Saision erfolgreich erzeugt.");
		request.getSession().setAttribute("seasions", WelcomeController.getSeasons());	

	}
	
	public static void errorMessage(final HttpServletRequest request) {
		
		String errorMessageSaison = "Es konnte keine Saison erzeugt werden";
		
		request.getSession().setAttribute("errormassage", errorMessageSaison);
		
	}
	

}