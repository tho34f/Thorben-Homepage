package com.thorben.web;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.thorben.objects.snooker.Spieler;
import com.thorben.objects.snooker.Tournament;
import com.thorben.objects.snooker.TournamentSeason;
import com.thorben.queries.MySql;
import com.thorben.service.DateConverter;
import com.thorben.service.ThorbenDierkes;
import com.thorben.service.ThorbenDierkesLogger;
import com.thorben.service.TypeConverter;
import com.thorben.web.data.SnookerControllerData;
import com.thorben.web.service.SnookerService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SnookerController extends HttpServlet {
	
	private static final long serialVersionUID = -9071950597448957942L;
	private static final ThorbenDierkesLogger LOOGER = new ThorbenDierkesLogger();
	private static final String SEASION = "seasion";
	private static final String SEASON = "season";

	private final SnookerControllerData controllerData;
	
	@Autowired
    public SnookerController() {
        this.controllerData = new SnookerControllerData();
        this.controllerData.setLanguage("de");
    }
	
	
	@GetMapping(value = "/snooker")
	public String snooker(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);
		
		List<String> provisionalRanking = SnookerService.getData(ThorbenDierkes.PROVISIONAL_RANKING);
		MySql.getInstance().getUpdateDB().updateDatenbank(provisionalRanking);
				
		return "snooker/snooker";
	}
	
	@GetMapping(value = "/simulation")
	public String simulation(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);

		String name = request.getParameter("name");
		float weight = TypeConverter.string2float(request.getParameter("weight"), 0);
		double roundnumber = TypeConverter.string2double(request.getParameter("number1"), 0);
		double playernumber = TypeConverter.string2double(request.getParameter("number2"), 0);
		Tournament tournament = null;
		
		if(name == null || request.getSession().getServletContext().getAttribute(SEASION) == null) {
			SnookerService.errorMessage(request);
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
			SnookerService.errorMessage(request);
		}else if(request.getSession().getServletContext().getAttribute(SEASION) !=null) {
			Tournament tournament = (Tournament) request.getSession().getAttribute("tournament");
			Spieler gewinner = SnookerService.simulation(participationPlayer);
			request.getSession().setAttribute("gewinner", gewinner);
			
		}
		
		LOOGER.infoLog("Simulation erfolgrecih durchgeführt.");
		return "snooker/simulation";
		
	}
	
	@GetMapping(value = "/saisonOverwiev")
	public String overviewget(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);
		
		return "snooker/saisonOverwiev";
	}
	
	@PostMapping(value = "/saisonOverwiev")
	public String overviewpost(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
			
		String number = request.getParameter(SEASON);
		SnookerService.setSeason(number, controllerData, request);
		
		return "snooker/saisonOverwiev";
	}
	
	@GetMapping(value = "/saison")
	public String saison(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		int yearint = TypeConverter.string2int(request.getParameter("id"), 0);
		
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);
		
		TournamentSeason season = null;
		TournamentSeason testSaison = (TournamentSeason) request.getAttribute(SEASION);
		
		if(yearint == 0 && testSaison == null) {
			SnookerService.errorMessage(request);
			
		} else {
			
			Set<TournamentSeason> seasionsPage = controllerData.getSeasons();
			for(TournamentSeason seasion : seasionsPage) {
				if(yearint == seasion.getYear()) {
					season = seasion;
				}
			}
			
			if(!controllerData.getSeasons().contains(season)) {
				SnookerService.errorMessage(request);
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
			
			SnookerService.errorMessage(request);
			
		} else {
		
			int numberInt = Integer.parseInt(number);
			season = SnookerService.creatSeason(numberInt);
			controllerData.getSeasons().add(season);
			request.getSession().getServletContext().setAttribute(SEASION, season);
			
		}
				
		return "snooker/saison";
	}
	
	@GetMapping(value = "/snookernews")
	public String snookerNews(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);
				
		return "snooker/snookernews";
	}
	
	@GetMapping(value = "/snookerrules")
	public String snookerrules(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);
				
		return "snooker/snookerrules";
	}
	
	@GetMapping(value = "/snookermaterials")
	public String snookermaterials(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);
				
		return "snooker/snookermaterials";
	}
	
	@GetMapping(value = "/overview")
	public String snookeroverview(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);
				
		return "snooker/overview";
	}
	
	@GetMapping(value = "/snookerhistory")
	public String snookerhistory(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);
				
		return "snooker/snookerhistory";
	}

}
