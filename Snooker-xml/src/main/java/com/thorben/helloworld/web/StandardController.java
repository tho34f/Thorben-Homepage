package com.mkyong.helloworld.web;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

import com.mkyong.helloworld.queries.NewsQueries;
import com.mkyong.helloworld.service.DateConverter;
import com.mkyong.helloworld.service.GetHomepageData;
import com.mkyong.helloworld.service.HelloWorldService;
import com.mkyong.helloworld.service.TypeConverter;
import com.mkyong.helloworld.service.UpdateDB;
import com.mkyong.helloworld.snooker.Tournament;
import com.mkyong.helloworld.snooker.TournamentSeason;
import com.mkyong.helloworld.snooker.News;
import com.mkyong.helloworld.snooker.Spieler;


@Controller
public class WelcomeController {

	private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	private final HelloWorldService helloWorldService;
	private static final String SEASION = "seasion";
	private static final String SEASON = "season";
	
	Date indexDate = new Date();
	private static Set<TournamentSeason> seasons = new HashSet<>();

	@Autowired
	public WelcomeController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String start(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		if(request.getSession().getAttribute("seasions") == null) {
			request.getSession().setAttribute("seasions", getSeasons());
		}
		
		List<String> provisionalRanking = GetHomepageData.getData();
		UpdateDB.updateDatenbank(provisionalRanking);
		
		DateConverter.setDateFooter(indexDate, request);
		
		return "index";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String establish(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
			
		String number = request.getParameter(SEASON);
		TournamentSeason season = null;
		
		HelloWorldService.setSeason(number, season, request);
		
		return "index";
	}
	
	@RequestMapping(value = "/saisonOverwiev", method = RequestMethod.GET)
	public String overviewget(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		
		return "snooker/saisonOverwiev";
	}
	
	@RequestMapping(value = "/saisonOverwiev", method = RequestMethod.POST)
	public String overviewpost(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
			
		String number = request.getParameter(SEASON);
		TournamentSeason season = null;
		
		HelloWorldService.setSeason(number, season, request);
		
		return "snooker/saisonOverwiev";
	}
	
	@RequestMapping(value = "/saison", method = RequestMethod.GET)
	public String saison(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		int yearint = TypeConverter.string2int(request.getParameter("id"), 0);
		
		DateConverter.setDateFooter(indexDate, request);
		
		TournamentSeason season = null;
		TournamentSeason testSaison = (TournamentSeason) request.getAttribute(SEASION);
		
		if(yearint == 0 && testSaison == null) {
			HelloWorldService.errorMessage(request);
			
		} else {
			
			Set<TournamentSeason> seasionsPage = getSeasons();
			for(TournamentSeason seasion : seasionsPage) {
				if(yearint == seasion.getYear()) {
					season = seasion;
				}
			}
			
			if(!seasons.contains(season)) {
				HelloWorldService.errorMessage(request);
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
			
			HelloWorldService.errorMessage(request);
			
		} else {
		
			int numberInt = Integer.parseInt(number);
			season = HelloWorldService.creatSeason(numberInt);
			getSeasons().add(season);
			request.getSession().getServletContext().setAttribute(SEASION, season);
			
		}
				
		return "snooker/saison";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchfunction(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
			
		String searchParameter = request.getParameter("suchen");
		
		request.setAttribute("searchresult", searchParameter);
				
		return "orga/search";
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
			HelloWorldService.errorMessage(request);
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
			HelloWorldService.errorMessage(request);
		}else if(request.getSession().getServletContext().getAttribute(SEASION) !=null) {
			Tournament tournament = (Tournament) request.getSession().getAttribute("tournament");
			
			Spieler gewinner = HelloWorldService.simulation(tournament, participationPlayer);
			
			request.getSession().setAttribute("gewinner", gewinner);
			
		}
		
		logger.info("Simulation erfolgrecih durchgeführt.");
		return "snooker/simulation";
		
	}
	
	@RequestMapping(value = "/newsslider", method = RequestMethod.GET)
	public String createNewsSlider(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		
		Set<News> newsList = NewsQueries.loadNewsList();
		request.getSession().setAttribute("newsList", newsList);
				
		return "newsslider";
	}
	
	@RequestMapping(value = "/newsreader", method = RequestMethod.GET)
	public String createNewsReader(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		
		String newsId = request.getParameter("id");
		News messageToRead = new News();
		if(newsId != null) {
			messageToRead = NewsQueries.loadNews(TypeConverter.string2int(newsId, 0));
		}
		
		request.getSession().setAttribute("messageToRead", messageToRead);
				
		return "newsreader";
	}
	
	@RequestMapping(value = "/snooker", method = RequestMethod.GET)
	public String snooker(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "snooker/snooker";
	}
	
	@RequestMapping(value = "/politik", method = RequestMethod.GET)
	public String politik(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "politik";
	}
	
	@RequestMapping(value = "/personal", method = RequestMethod.GET)
	public String personal(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "personal";
	}
	
	@RequestMapping(value = "/datenschutz", method = RequestMethod.GET)
	public String data(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "orga/datenschutz";
	}
	
	@RequestMapping(value = "/impressum", method = RequestMethod.GET)
	public String impressum(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "orga/impressum";
	}
	
	@RequestMapping(value = "/snookernews", method = RequestMethod.GET)
	public String snookerNews(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "snooker/snookernews";
	}

	public static Set<TournamentSeason> getSeasons() {
		return seasons;
	}

	public static void setSeasons(Set<TournamentSeason> seasons) {
		WelcomeController.seasons = seasons;
	}

	public HelloWorldService getHelloWorldService() {
		return helloWorldService;
	}

}