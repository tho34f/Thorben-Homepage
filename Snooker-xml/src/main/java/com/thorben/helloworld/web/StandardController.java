package com.thorben.helloworld.web;

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

import com.thorben.helloworld.queries.CalendarQueries;
import com.thorben.helloworld.queries.NewsQueries;
import com.thorben.helloworld.service.DateConverter;
import com.thorben.helloworld.service.GetHomepageData;
import com.thorben.helloworld.service.ThorbenDierkesService;
import com.thorben.helloworld.service.TypeConverter;
import com.thorben.helloworld.service.UpdateDB;
import com.thorben.helloworld.snooker.TournamentSeason;
import com.thorben.helloworld.snooker.News;
import com.thorben.helloworld.snooker.Termin;


@Controller
public class StandardController {

	private final Logger logger = LoggerFactory.getLogger(StandardController.class);
	private final ThorbenDierkesService helloWorldService;
	
	Date indexDate = new Date();
	private static Set<TournamentSeason> seasons = new HashSet<>();
	private static int pageReminderNewsList = 1;
	private static int pageReminderTerminList = 1;

	@Autowired
	public StandardController(ThorbenDierkesService helloWorldService) {
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
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchfunction(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
			
		String searchParameter = request.getParameter("suchen");
		
		request.setAttribute("searchresult", searchParameter);
				
		return "orga/search";
	}
	
	@RequestMapping(value = "/terminslider", method = RequestMethod.GET)
	public String createTerminSlider(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		String action = request.getParameter("action");
		int pageNumber = TypeConverter.string2int(request.getParameter("page"),0);
		
		Set<Termin> terminList = CalendarQueries.loadCalendarList();
		Map<String,Set<Termin>> splitedTerminList = ThorbenDierkesService.splitTerminList(terminList);
		int slider = splitedTerminList.size();
		
		if(action == null) {
			setPageReminderTerminList(pageNumber);
		} else if(action.equals("next")) {
			pageNumber = getPageReminderNewsList() + 1;
			setPageReminderTerminList(pageNumber);
		} else {
			pageNumber = getPageReminderNewsList() - 1;
			setPageReminderTerminList(pageNumber);
		}
		
		if(slider < pageNumber) {
			pageNumber = slider;
			setPageReminderTerminList(pageNumber);
		} else if(pageNumber <= 0) {
			pageNumber = 1;
		}
		
		terminList = splitedTerminList.get("terminsilderpage" + pageNumber);
		
		request.getSession().setAttribute("activePage", pageNumber);
		request.getSession().setAttribute("terminList", terminList);
		request.getSession().setAttribute("sliderlenght", slider);
				
		return "personal/terminslider";
	}
	
	@RequestMapping(value = "/terminreader", method = RequestMethod.GET)
	public String createTerminReader(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		
		String terminId = request.getParameter("id");
		Termin terminToRead = new Termin();
		if(terminId != null) {
			terminToRead = CalendarQueries.loadCalendar(TypeConverter.string2int(terminId, 0));
		}
		
		request.getSession().setAttribute("calendarToRead", terminToRead);
				
		return "personal/terminreader";
	}
	
	@RequestMapping(value = "/newsslider", method = RequestMethod.GET)
	public String createNewsSlider(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		String action = request.getParameter("action");
		int pageNumber = TypeConverter.string2int(request.getParameter("page"),0);
		
		Set<News> newsList = NewsQueries.loadNewsList();
		Map<String,Set<News>> splitedNewsList = ThorbenDierkesService.splitNewsList(newsList);
		int slider = splitedNewsList.size();
		
		if(action == null) {
			setPageReminderNewsList(pageNumber);
		} else if(action.equals("next")) {
			pageNumber = getPageReminderNewsList() + 1;
			setPageReminderNewsList(pageNumber);
		} else {
			pageNumber = getPageReminderNewsList() - 1;
			setPageReminderNewsList(pageNumber);
		}
		
		if(slider < pageNumber) {
			pageNumber = slider;
			setPageReminderNewsList(pageNumber);
		} else if(pageNumber <= 0) {
			pageNumber = 1;
		}
		
		newsList = splitedNewsList.get("newssilderpage" + pageNumber);
		
		request.getSession().setAttribute("activePage", pageNumber);
		request.getSession().setAttribute("newsList", newsList);
		request.getSession().setAttribute("sliderlenght", slider);
				
		return "personal/newsslider";
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
				
		return "personal/newsreader";
	}
	
	@RequestMapping(value = "/politik", method = RequestMethod.GET)
	public String politik(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "political/politik";
	}
	
	@RequestMapping(value = "/politik-werdegang", method = RequestMethod.GET)
	public String werdegang(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "political/politikwerdegang";
	}
	
	@RequestMapping(value = "/personal", method = RequestMethod.GET)
	public String personal(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "personal/personal";
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
	
	@RequestMapping(value = "/kontakt", method = RequestMethod.GET)
	public String kontakt(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "orga/kontaktdaten";
	}

	public static Set<TournamentSeason> getSeasons() {
		return seasons;
	}

	public static void setSeasons(Set<TournamentSeason> seasons) {
		StandardController.seasons = seasons;
	}

	public ThorbenDierkesService getHelloWorldService() {
		return helloWorldService;
	}

	public static int getPageReminderNewsList() {
		return pageReminderNewsList;
	}

	public static void setPageReminderNewsList(int pageReminder) {
		StandardController.pageReminderNewsList = pageReminder;
	}

	public static int getPageReminderTerminList() {
		return pageReminderTerminList;
	}

	public static void setPageReminderTerminList(int pageReminderTerminList) {
		StandardController.pageReminderTerminList = pageReminderTerminList;
	}

}