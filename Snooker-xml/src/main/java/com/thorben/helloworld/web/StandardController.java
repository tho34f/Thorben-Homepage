package com.thorben.helloworld.web;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.thorben.helloworld.queries.MySql;
import com.thorben.helloworld.service.DateConverter;
import com.thorben.helloworld.service.ThorbenDierkesService;
import com.thorben.helloworld.service.TypeConverter;
import com.thorben.helloworld.snooker.TournamentSeason;
import com.thorben.helloworld.snooker.News;
import com.thorben.helloworld.snooker.Termin;


@Controller
public class StandardController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6766367415600280400L;

	private ThorbenDierkesService helloWorldService = new ThorbenDierkesService();
	
	private static Date indexDate = new Date();
	private static Set<TournamentSeason> seasons = new HashSet<>();
	private static int pageReminderNewsList = 1;
	private static int pageReminderTerminList = 1;
	
	public StandardController() {
		
	}
	
	@Autowired
	public StandardController(ThorbenDierkesService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}
	
	@GetMapping(value = "/")
	public ModelAndView start(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("index");
		
		if(request.getSession().getAttribute("seasions") == null) {
			request.getSession().setAttribute("seasions", getSeasons());
		}
		
		DateConverter.setDateFooter(indexDate, request);
		
		return mav;
	}
	
	@PostMapping(value = "/search")
	public String searchfunction(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
			
		String searchParameter = request.getParameter("suchen");
		
		request.setAttribute("searchresult", searchParameter);
				
		return "orga/search";
	}
	
	@GetMapping(value = "/terminslider")
	public String createTerminSlider(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		String action = request.getParameter("action");
		int pageNumber = TypeConverter.string2int(request.getParameter("page"),0);
		
		Set<Termin> terminList = MySql.getInstance().getCalendarQueries().loadCalendarList();
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
	
	@GetMapping(value = "/terminreader")
	public String createTerminReader(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		
		String terminId = request.getParameter("id");
		Termin terminToRead = new Termin();
		if(terminId != null) {
			terminToRead = MySql.getInstance().getCalendarQueries().loadCalendar(TypeConverter.string2int(terminId, 0));
		}
		
		request.getSession().setAttribute("calendarToRead", terminToRead);
				
		return "personal/terminreader";
	}
	
	@GetMapping(value = "/newsslider")
	public String createNewsSlider(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		String action = request.getParameter("action");
		int pageNumber = TypeConverter.string2int(request.getParameter("page"),0);
		
		Set<News> newsList = MySql.getInstance().getNewsQueries().loadNewsList();
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
	
	@GetMapping(value = "/newsreader")
	public String createNewsReader(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		
		String newsId = request.getParameter("id");
		News messageToRead = new News();
		if(newsId != null) {
			messageToRead = MySql.getInstance().getNewsQueries().loadNews(TypeConverter.string2int(newsId, 0));
		}
		
		request.getSession().setAttribute("messageToRead", messageToRead);
				
		return "personal/newsreader";
	}
	
	@GetMapping(value = "/politik")
	public String politik(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "political/politik";
	}
	
	@GetMapping(value = "/politik-werdegang")
	public String werdegang(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "political/politikwerdegang";
	}
	
	@GetMapping(value = "/personal")
	public String personal(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "personal/personal";
	}
	
	@GetMapping(value = "/datenschutz")
	public String data(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "orga/datenschutz";
	}
	
	@GetMapping(value = "/impressum")
	public String impressum(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
				
		return "orga/impressum";
	}
	
	@GetMapping(value = "/kontakt")
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