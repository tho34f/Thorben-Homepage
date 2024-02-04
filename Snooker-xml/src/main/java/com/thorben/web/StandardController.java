package com.thorben.web;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.thorben.objects.News;
import com.thorben.objects.Termin;
import com.thorben.queries.MySql;
import com.thorben.service.BackendService;
import com.thorben.service.DateConverter;
import com.thorben.service.TypeConverter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class StandardController extends HttpServlet {


	private static final long serialVersionUID = 6766367415600280400L;
	private static Date indexDate = new Date();
	private static int pageReminderNewsList = 1;
	private static int pageReminderTerminList = 1;
	
	@GetMapping(value = "/")
	public ModelAndView start(final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		return new ModelAndView("index");
	}
	
	@PostMapping(value = "/search")
	public ModelAndView searchfunction(final HttpServletRequest request, final HttpServletResponse response) {
		
		
		DateConverter.setDateFooter(indexDate, request);
			
		String searchParameter = request.getParameter("suchen");
		request.setAttribute("searchresult", searchParameter);
				
		return new ModelAndView("orga/search");
	}
	
	@GetMapping(value = "/terminslider")
	public ModelAndView createTerminSlider(final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		String action = request.getParameter("action");
		int pageNumber = TypeConverter.string2int(request.getParameter("page"),0);
		
		Set<Termin> terminList = MySql.getInstance().getCalendarQueries().loadCalendarList();
		Map<String,Set<Termin>> splitedTerminList = BackendService.splitTerminList(terminList);
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
				
		return new ModelAndView("personal/terminslider");
	}
	
	@GetMapping(value = "/terminreader")
	public ModelAndView createTerminReader(final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		
		String terminId = request.getParameter("id");
		Termin terminToRead = new Termin();
		if(terminId != null) {
			terminToRead = MySql.getInstance().getCalendarQueries().loadCalendar(TypeConverter.string2int(terminId, 0));
		}
		
		request.getSession().setAttribute("calendarToRead", terminToRead);
				
		return new ModelAndView("personal/terminreader");
	}
	
	@GetMapping(value = "/newsslider")
	public ModelAndView createNewsSlider(final HttpServletRequest request, final HttpServletResponse response) {

		DateConverter.setDateFooter(indexDate, request);
		String action = request.getParameter("action");
		int pageNumber = TypeConverter.string2int(request.getParameter("page"),0);
		
		Set<News> newsList = MySql.getInstance().getNewsQueries().loadNewsList();
		Map<String,Set<News>> splitedNewsList = BackendService.splitNewsList(newsList);
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
				
		return new ModelAndView("personal/newsslider");
	}
	
	@GetMapping(value = "/newsreader")
	public ModelAndView createNewsReader(final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		
		String newsId = request.getParameter("id");
		News messageToRead = new News();
		if(newsId != null) {
			messageToRead = MySql.getInstance().getNewsQueries().loadNews(TypeConverter.string2int(newsId, 0));
		}
		
		request.getSession().setAttribute("messageToRead", messageToRead);
				
		return  new ModelAndView("personal/newsreader");
	}
	
	@GetMapping(value = "/politik")
	public ModelAndView politik(final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);		
		return  new ModelAndView("political/politik");
	}
	
	@GetMapping(value = "/politik-werdegang")
	public ModelAndView werdegang(final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);	
		return new ModelAndView("political/politikwerdegang");
	}
	
	@GetMapping(value = "/personal")
	public ModelAndView personal(final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);		
		return new ModelAndView("personal/personal");
	}
	
	@GetMapping(value = "/datenschutz")
	public ModelAndView data(final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);	
		return new ModelAndView("orga/datenschutz");
	}
	
	@GetMapping(value = "/impressum")
	public ModelAndView impressum(final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);	
		return new ModelAndView("orga/impressum");
	}
	
	@GetMapping(value = "/kontakt")
	public ModelAndView kontakt(final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);	
		return new ModelAndView("orga/kontaktdaten");
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