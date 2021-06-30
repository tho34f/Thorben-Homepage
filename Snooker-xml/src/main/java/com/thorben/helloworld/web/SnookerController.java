package com.thorben.helloworld.web;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thorben.helloworld.service.DateConverter;
import com.thorben.helloworld.service.ThorbenDierkesService;
import com.thorben.helloworld.service.TypeConverter;
import com.thorben.helloworld.snooker.Spieler;
import com.thorben.helloworld.snooker.Tournament;
import com.thorben.helloworld.snooker.TournamentSeason;

@Controller
public class SnookerController {
	
	private final Logger logger = LoggerFactory.getLogger(SnookerController.class);
	private final ThorbenDierkesService helloWorldService;
	private static final String SEASION = "seasion";
	private static final String SEASON = "season";
	
	Date indexDate = new Date();
	private static Set<TournamentSeason> seasons = new HashSet<>();
	
	@Autowired
	public SnookerController(ThorbenDierkesService helloWorldService) {
		this.helloWorldService = helloWorldService;
		
	}
	
	@RequestMapping(value = "/snooker", method = RequestMethod.GET)
	public String snooker(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "snooker/snooker";
	}
	
	@RequestMapping(value = "/simulation", method = RequestMethod.GET)
	public String simulation(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);

		String name = request.getParameter("name");
		float weight = TypeConverter.string2float(request.getParameter("weight"), 0);
		double roundnumber = TypeConverter.string2double(request.getParameter("number1"), 0);
		double playernumber = TypeConverter.string2double(request.getParameter("number2"), 0);
		Tournament tournament = null;
		
		if(name == null || request.getSession().getServletContext().getAttribute(SEASION) == null) {
			ThorbenDierkesService.errorMessage(request);
		}else if(request.getSession().getAttribute(SEASION) !=null) {
			tournament = new Tournament(name, weight, playernumber, roundnumber);
		
			request.getSession().setAttribute("tournament", tournament);
			
		}
		
		return "snooker/simulation";
		
	}
	
	@RequestMapping(value = "/simulation", method = RequestMethod.POST)
	public String simulationT(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		
		String participationPlayer = request.getParameter("participationPlayer");
		
		if(participationPlayer == null || request.getSession().getAttribute(SEASION) == null) {
			ThorbenDierkesService.errorMessage(request);
		}else if(request.getSession().getServletContext().getAttribute(SEASION) !=null) {
			Tournament tournament = (Tournament) request.getSession().getAttribute("tournament");
			
			Spieler gewinner = ThorbenDierkesService.simulation(tournament, participationPlayer);
			
			request.getSession().setAttribute("gewinner", gewinner);
			
		}
		
		logger.info("Simulation erfolgrecih durchgeführt.");
		return "snooker/simulation";
		
	}
	
	@RequestMapping(value = "/saisonOverwiev", method = RequestMethod.GET)
	public String overviewget(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		
		return "snooker/saisonOverwiev";
	}
	
	@RequestMapping(value = "/saisonOverwiev", method = RequestMethod.POST)
	public String overviewpost(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
			
		String number = request.getParameter(SEASON);
		ThorbenDierkesService.setSeason(number, request);
		
		return "snooker/saisonOverwiev";
	}
	
	@RequestMapping(value = "/saison", method = RequestMethod.GET)
	public String saison(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		int yearint = TypeConverter.string2int(request.getParameter("id"), 0);
		
		DateConverter.setDateFooter(indexDate, request);
		
		TournamentSeason season = null;
		TournamentSeason testSaison = (TournamentSeason) request.getAttribute(SEASION);
		
		if(yearint == 0 && testSaison == null) {
			ThorbenDierkesService.errorMessage(request);
			
		} else {
			
			Set<TournamentSeason> seasionsPage = getSeasons();
			for(TournamentSeason seasion : seasionsPage) {
				if(yearint == seasion.getYear()) {
					season = seasion;
				}
			}
			
			if(!seasons.contains(season)) {
				ThorbenDierkesService.errorMessage(request);
			} else {
				request.getSession().setAttribute(SEASION, season);
			}
		}
		
		return "snooker/saison";
	}
	
	@RequestMapping(value = "/saison", method = RequestMethod.POST)
	public String establishSeasion(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
			
		String number = request.getParameter(SEASON);
		TournamentSeason season = null;
		TournamentSeason testSaison = (TournamentSeason) request.getSession().getAttribute(SEASION);
		
		if(number.isEmpty() && testSaison == null) {
			
			ThorbenDierkesService.errorMessage(request);
			
		} else {
		
			int numberInt = Integer.parseInt(number);
			season = ThorbenDierkesService.creatSeason(numberInt);
			getSeasons().add(season);
			request.getSession().getServletContext().setAttribute(SEASION, season);
			
		}
				
		return "snooker/saison";
	}
	
	@RequestMapping(value = "/snookernews", method = RequestMethod.GET)
	public String snookerNews(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "snooker/snookernews";
	}
	
	@RequestMapping(value = "/snookerrules", method = RequestMethod.GET)
	public String snookerrules(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "snooker/snookerrules";
	}
	
	@RequestMapping(value = "/snookermaterials", method = RequestMethod.GET)
	public String snookermaterials(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "snooker/snookermaterials";
	}
	
	@RequestMapping(value = "/overview", method = RequestMethod.GET)
	public String snookeroverview(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "snooker/overview";
	}
	
	@RequestMapping(value = "/snookerhistory", method = RequestMethod.GET)
	public String snookerhistory(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "snooker/snookerhistory";
	}
	
	public static Set<TournamentSeason> getSeasons() {
		return seasons;
	}

	public static void setSeasons(Set<TournamentSeason> seasons) {
		SnookerController.seasons = seasons;
	}

	public ThorbenDierkesService getHelloWorldService() {
		return helloWorldService;
	}

}
