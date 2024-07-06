package com.thorben.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.thorben.objects.snooker.Spieler;
import com.thorben.objects.snooker.TournamentSeason;
import com.thorben.queries.MySql;
import com.thorben.service.ThorbenDierkesLogger;
import com.thorben.service.TypeConverter;
import com.thorben.web.SnookerController;
import com.thorben.web.data.SnookerControllerData;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class SnookerService {
	
	private static final ThorbenDierkesLogger LOOGER = new ThorbenDierkesLogger();
	
	public static List<String> getData(String url) {
		List<String> data = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements player = doc.select("tr[class]");
			for (Element elem : player) {
				Elements element = elem.children();
				String textData = element.text();
				if(!textData.equals("")) {
					String[] splitTextData = textData.split(" ");
					data.add(splitTextData[0]);
					data.add(splitTextData[1]);
					data.add(splitTextData[2]);
				}
			}
			
			return data;
			
		} catch (IOException e) {
			e.printStackTrace();
			return data;
		}
	}
	
	public static Spieler simulation(String participationPlayer) {
		
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
	
	public static boolean setSeason(String number, SnookerControllerData controllerData, final HttpServletRequest request) {
		
		TournamentSeason season = null;
		boolean isSeasonOk = false;
		
		if(!number.isEmpty()) {
			int number2 = TypeConverter.string2int(number, 0);
			season =  creatSeason(number2);
			controllerData.getSeasons().add(season);
			isSeasonOk = true;
		}
		
		LOOGER.infoLog("Saision erfolgreich erzeugt.");
		request.getSession().setAttribute("seasions", controllerData.getSeasons());	
		
		return isSeasonOk;

	}
	
	public static boolean errorMessage(final HttpServletRequest request) {
		
		String errorMessageSaison = "Es konnte keine Saison erzeugt werden";
		request.getSession().setAttribute("errormassage", errorMessageSaison);
		
		return true;
		
	}

}
