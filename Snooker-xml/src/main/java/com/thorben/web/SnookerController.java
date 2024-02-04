package com.thorben.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.thorben.objects.snooker.Spieler;
import com.thorben.objects.snooker.Tournament;
import com.thorben.objects.snooker.TournamentSeason;
import com.thorben.queries.MySql;
import com.thorben.service.BackendService;
import com.thorben.service.DateConverter;
import com.thorben.service.ThorbenDierkes;
import com.thorben.service.TypeConverter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SnookerController extends HttpServlet {
	
	private static final long serialVersionUID = -9071950597448957942L;
	private static final Logger logger = LoggerFactory.getLogger(SnookerController.class);
	private static final String SEASION = "seasion";
	private static final String SEASON = "season";
	
	private static Date indexDate = new Date();
	private static Set<TournamentSeason> seasons = new HashSet<>();
	
	
	@GetMapping(value = "/snooker")
	public String snooker(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		
		List<String> provisionalRanking = getData(ThorbenDierkes.PROVISIONAL_RANKING);
		MySql.getInstance().getUpdateDB().updateDatenbank(provisionalRanking);
				
		return "snooker/snooker";
	}
	
	@GetMapping(value = "/simulation")
	public String simulation(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);

		String name = request.getParameter("name");
		float weight = TypeConverter.string2float(request.getParameter("weight"), 0);
		double roundnumber = TypeConverter.string2double(request.getParameter("number1"), 0);
		double playernumber = TypeConverter.string2double(request.getParameter("number2"), 0);
		Tournament tournament = null;
		
		if(name == null || request.getSession().getServletContext().getAttribute(SEASION) == null) {
			BackendService.errorMessage(request);
		}else if(request.getSession().getAttribute(SEASION) !=null) {
			tournament = new Tournament(name, weight, playernumber, roundnumber);
		
			request.getSession().setAttribute("tournament", tournament);
			
		}
		
		return "snooker/simulation";
		
	}
	
	@PostMapping(value = "/simulation")
	public String simulationT(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		
		String participationPlayer = request.getParameter("participationPlayer");
		
		if(participationPlayer == null || request.getSession().getAttribute(SEASION) == null) {
			BackendService.errorMessage(request);
		}else if(request.getSession().getServletContext().getAttribute(SEASION) !=null) {
			Tournament tournament = (Tournament) request.getSession().getAttribute("tournament");
			
			Spieler gewinner = BackendService.simulation(tournament, participationPlayer);
			
			request.getSession().setAttribute("gewinner", gewinner);
			
		}
		
		logger.info("Simulation erfolgrecih durchgef�hrt.");
		return "snooker/simulation";
		
	}
	
	@GetMapping(value = "/saisonOverwiev")
	public String overviewget(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		
		return "snooker/saisonOverwiev";
	}
	
	@PostMapping(value = "/saisonOverwiev")
	public String overviewpost(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
			
		String number = request.getParameter(SEASON);
		BackendService.setSeason(number, request);
		
		return "snooker/saisonOverwiev";
	}
	
	@GetMapping(value = "/saison")
	public String saison(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		int yearint = TypeConverter.string2int(request.getParameter("id"), 0);
		
		DateConverter.setDateFooter(indexDate, request);
		
		TournamentSeason season = null;
		TournamentSeason testSaison = (TournamentSeason) request.getAttribute(SEASION);
		
		if(yearint == 0 && testSaison == null) {
			BackendService.errorMessage(request);
			
		} else {
			
			Set<TournamentSeason> seasionsPage = getSeasons();
			for(TournamentSeason seasion : seasionsPage) {
				if(yearint == seasion.getYear()) {
					season = seasion;
				}
			}
			
			if(!seasons.contains(season)) {
				BackendService.errorMessage(request);
			} else {
				request.getSession().setAttribute(SEASION, season);
			}
		}
		
		return "snooker/saison";
	}
	
	@PostMapping(value = "/saison")
	public String establishSeasion(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
			
		String number = request.getParameter(SEASON);
		TournamentSeason season = null;
		TournamentSeason testSaison = (TournamentSeason) request.getSession().getAttribute(SEASION);
		
		if(number.isEmpty() && testSaison == null) {
			
			BackendService.errorMessage(request);
			
		} else {
		
			int numberInt = Integer.parseInt(number);
			season = BackendService.creatSeason(numberInt);
			getSeasons().add(season);
			request.getSession().getServletContext().setAttribute(SEASION, season);
			
		}
				
		return "snooker/saison";
	}
	
	@GetMapping(value = "/snookernews")
	public String snookerNews(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "snooker/snookernews";
	}
	
	@GetMapping(value = "/snookerrules")
	public String snookerrules(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "snooker/snookerrules";
	}
	
	@GetMapping(value = "/snookermaterials")
	public String snookermaterials(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "snooker/snookermaterials";
	}
	
	@GetMapping(value = "/overview")
	public String snookeroverview(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "snooker/overview";
	}
	
	@GetMapping(value = "/snookerhistory")
	public String snookerhistory(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "snooker/snookerhistory";
	}
	
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
	
	public static Set<TournamentSeason> getSeasons() {
		return seasons;
	}

	public static void setSeasons(Set<TournamentSeason> seasons) {
		SnookerController.seasons = seasons;
	}

}
