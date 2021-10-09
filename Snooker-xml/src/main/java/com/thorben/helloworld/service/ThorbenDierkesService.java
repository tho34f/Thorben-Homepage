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

import com.thorben.helloworld.queries.MySql;
import com.thorben.helloworld.snooker.Tournament;
import com.thorben.helloworld.snooker.TournamentSeason;
import com.thorben.helloworld.snooker.News;
import com.thorben.helloworld.snooker.Spieler;
import com.thorben.helloworld.snooker.Termin;
import com.thorben.helloworld.web.SnookerController;

@Service
public class ThorbenDierkesService {

	private static final Logger logger = LoggerFactory.getLogger(ThorbenDierkesService.class);
	
	private Random generator;
	
	public ThorbenDierkesService() {
		this.generator = new Random();
	}
	
	public int generateId() {
		
		return (1 + getGenerator().nextInt(500));
	}
	
	
	public static Spieler simulation(Tournament tournament, String participationPlayer) {
		
		String[] arrayPlayer = participationPlayer.split(",");
		
		return new Spieler(arrayPlayer[0], arrayPlayer[1],0,0,0,0,0, 0);
	
	}
	
	public static TournamentSeason creatSeason(int year) {
		
		TournamentSeason season = new TournamentSeason(year);
		
        // Ergebnisse anzeigen.
		MySql.getInstance().getSnookerQueries().creatTournamentList("tournament", season);
		MySql.getInstance().getSnookerQueries().creatPlayerList("snookerplayers", season);

		return season;
	}
	
	public static void setSeason(String number, final HttpServletRequest request) {
		
		TournamentSeason season = null;
		
		if(!number.isEmpty()) {
			int number2 = TypeConverter.string2int(number, 0);
			season =  creatSeason(number2);
			SnookerController.getSeasons().add(season);
		}
		
		logger.info("Saision erfolgreich erzeugt.");
		request.getSession().setAttribute("seasions", SnookerController.getSeasons());	

	}
	
	public static void errorMessage(final HttpServletRequest request) {
		
		String errorMessageSaison = "Es konnte keine Saison erzeugt werden";
		
		request.getSession().setAttribute("errormassage", errorMessageSaison);
		
	}
	
	public static Map<String,Set<News>> splitNewsList(Set<News> objectList){
		
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
	
	public static Map<String,Set<Termin>> splitTerminList(Set<Termin> objectList){
		
		Map<String,Set<Termin>> splitedTerminList = new HashMap<>();
		Set<Termin> helpTerminList = new HashSet<>();
		int counter = 0;
		int terminsilderpage = 1;
		Iterator<Termin> it = objectList.iterator();
		while(it.hasNext()) {
			helpTerminList.add(it.next());
			counter++;
			if(counter == 5) {
				splitedTerminList.put("terminsilderpage" + terminsilderpage, helpTerminList);
				terminsilderpage++;
				helpTerminList = new HashSet<>();
				counter = 0;
			}
		}
		if(counter != 5) {
			splitedTerminList.put("terminsilderpage" + terminsilderpage, helpTerminList);
		}
		
		return splitedTerminList;
	}
	
	public String errorUserLogin(HttpServletRequest request, boolean isWizard) {
		
		request.getSession().setAttribute(ThorbenDierkes.IS_LOGIN_OK, false);
		request.setAttribute(ThorbenDierkes.ERROR_MASSAGE, ThorbenDierkes.USER_NOT_LOGIN );
		
		if(!isWizard) {
			return ThorbenDierkes.LOGIN;
		} else {
			return ThorbenDierkes.LOGIN_WIZARD;
		}
		
		
	}

	public Random getGenerator() {
		return generator;
	}

	public void setGenerator(Random generator) {
		this.generator = generator;
	}
	

}