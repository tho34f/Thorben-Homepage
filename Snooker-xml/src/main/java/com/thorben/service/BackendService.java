package com.thorben.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.thorben.objects.News;
import com.thorben.objects.Termin;
import com.thorben.objects.snooker.Spieler;
import com.thorben.objects.snooker.Tournament;
import com.thorben.objects.snooker.TournamentSeason;
import com.thorben.queries.MySql;
import com.thorben.web.SnookerController;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BackendService {

	private static final ThorbenDierkesLogger LOOGER = new ThorbenDierkesLogger();
	public static final Random GENERATOR = new Random();
	
	public int generateId() {
		
		return (1 + GENERATOR.nextInt(500));
	}
	
	
	public static Spieler simulation(Tournament tournament, String participationPlayer) {
		
		String[] arrayPlayer = participationPlayer.split(",");
		
		return new Spieler(arrayPlayer[0], arrayPlayer[1],0,0,0,0,0, 0);
	
	}
	
	public static TournamentSeason creatSeason(int year) {
		
		TournamentSeason season = new TournamentSeason(year);
		
        // Ergebnisse anzeigen.
		if(MySql.getInstance().getSnookerQueries() != null) {
			MySql.getInstance().getSnookerQueries().creatTournamentList("tournament", season);
			MySql.getInstance().getSnookerQueries().creatPlayerList("snookerplayers", season);
		}

		return season;
	}
	
	public static boolean setSeason(String number, final HttpServletRequest request) {
		
		TournamentSeason season = null;
		boolean isSeasonOk = false;
		
		if(!number.isEmpty()) {
			int number2 = TypeConverter.string2int(number, 0);
			season =  creatSeason(number2);
			SnookerController.getSeasons().add(season);
			isSeasonOk = true;
		}
		
		LOOGER.infoLog("Saision erfolgreich erzeugt.");
		request.getSession().setAttribute("seasions", SnookerController.getSeasons());	
		
		return isSeasonOk;

	}
	
	public static boolean errorMessage(final HttpServletRequest request) {
		
		String errorMessageSaison = "Es konnte keine Saison erzeugt werden";
		request.getSession().setAttribute("errormassage", errorMessageSaison);
		
		return true;
		
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
	
	public static String errorUserLogin(HttpServletRequest request) {
		
		request.getSession().setAttribute(ThorbenDierkes.IS_LOGIN_OK, false);
		request.setAttribute(ThorbenDierkes.ERROR_MASSAGE, ThorbenDierkes.USER_NOT_LOGIN );
		return ThorbenDierkes.LOGIN;

	}
	
}