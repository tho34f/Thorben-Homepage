package com.thorben.helloworld.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thorben.helloworld.queries.SnookerQueries;
import com.thorben.helloworld.snooker.Tournament;
import com.thorben.helloworld.snooker.TournamentSeason;
import com.thorben.helloworld.snooker.News;
import com.thorben.helloworld.snooker.Spieler;
import com.thorben.helloworld.web.StandardController;

@Service
public class ThorbenDierkesService {

	private static final Logger logger = LoggerFactory.getLogger(ThorbenDierkesService.class);
	
	private static Random generator = new Random();
	
	public static int generateId() {
		
		return (1 + generator.nextInt(500));
	}
	
	
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
	
	public static void setSeason(String number, final HttpServletRequest request) {
		
		TournamentSeason season = null;
		
		if(!number.isEmpty()) {
			int number2 = TypeConverter.string2int(number, 0);
			season =  creatSeason(number2);
			StandardController.getSeasons().add(season);
		}
		
		logger.info("Saision erfolgreich erzeugt.");
		request.getSession().setAttribute("seasions", StandardController.getSeasons());	

	}
	
	public static void errorMessage(final HttpServletRequest request) {
		
		String errorMessageSaison = "Es konnte keine Saison erzeugt werden";
		
		request.getSession().setAttribute("errormassage", errorMessageSaison);
		
	}
	
	public static Map<String,Set<News>> splitNewsandTerminList(Set<News> objectList){
		
		Map<String,Set<News>> splitedNewsList = new HashMap<>();
		Set<News> helpNewsList = new HashSet<>();
		int counter = 0;
		int newssilderpage = 1;
		Iterator<News> it = objectList.iterator();
		while(it.hasNext()) {
			helpNewsList.add(it.next());
			counter++;
			if(counter == 5) {
				splitedNewsList.put("newssilderpage" + newssilderpage, helpNewsList);
				newssilderpage++;
				helpNewsList = new HashSet<>();
				counter = 0;
			}
		}
		if(counter != 5) {
			splitedNewsList.put("newssilderpage" + newssilderpage, helpNewsList);
		}
		
		return splitedNewsList;
	}
	

}