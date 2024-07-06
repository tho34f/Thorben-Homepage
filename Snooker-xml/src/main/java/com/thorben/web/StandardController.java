package com.thorben.web;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.thorben.objects.News;
import com.thorben.objects.Termin;
import com.thorben.queries.MySql;
import com.thorben.service.DateConverter;
import com.thorben.service.ThorbenDierkesLogger;
import com.thorben.service.TypeConverter;
import com.thorben.web.data.StandardControllerData;
import com.thorben.web.service.FrontendService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class StandardController extends HttpServlet {


	private static final long serialVersionUID = 6766367415600280400L;
	private static final ThorbenDierkesLogger LOOGER = new ThorbenDierkesLogger();
	
	private final StandardControllerData controllerData;
	
	@Autowired
    public StandardController() {
        this.controllerData = new StandardControllerData();
        this.controllerData.setLanguage("de");
    }
	
	@GetMapping(value = "/")
	public ModelAndView start(final HttpServletRequest request, final HttpServletResponse response) {
		
		LOOGER.infoLog("StandardCintroller: getIndex()");
		
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);
		return new ModelAndView("index");
	}
	
	@PostMapping(value = "/search")
	public ModelAndView searchfunction(final HttpServletRequest request, final HttpServletResponse response) {
		
		LOOGER.infoLog("StandardCintroller: searchfunction()");
		
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);
		String searchParameter = request.getParameter("suchen");
		request.setAttribute("searchresult", searchParameter);
				
		return new ModelAndView("orga/search");
	}
	
	@GetMapping(value = "/terminslider")
	public ModelAndView createTerminSlider(final HttpServletRequest request, final HttpServletResponse response) {
		
		LOOGER.infoLog("StandardCintroller: createTerminSlider()");
		
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);
		String action = request.getParameter("action");
		int pageNumber = TypeConverter.string2int(request.getParameter("page"),0);
		
		Set<Termin> terminList = MySql.getInstance().getCalendarQueries().loadCalendarList();
		Map<String,Set<Termin>> splitedTerminList = FrontendService.splitTerminList(terminList);
		int slider = splitedTerminList.size();
		
		if(action == null) {
			controllerData.setPageReminderTerminList(pageNumber);
		} else if(action.equals("next")) {
			pageNumber = controllerData.getPageReminderNewsList() + 1;
			controllerData.setPageReminderTerminList(pageNumber);
		} else {
			pageNumber = controllerData.getPageReminderNewsList() - 1;
			controllerData.setPageReminderTerminList(pageNumber);
		}
		
		if(slider < pageNumber) {
			pageNumber = slider;
			controllerData.setPageReminderTerminList(pageNumber);
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
		
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);
		
		String terminId = request.getParameter("id");
		LOOGER.infoLog("StandardCintroller: read Termin " + terminId);
		Termin terminToRead = new Termin();
		if(terminId != null) {
			terminToRead = MySql.getInstance().getCalendarQueries().loadCalendar(TypeConverter.string2int(terminId, 0));
		}
		
		request.getSession().setAttribute("calendarToRead", terminToRead);
				
		return new ModelAndView("personal/terminreader");
	}
	
	@GetMapping(value = "/newsslider")
	public ModelAndView createNewsSlider(final HttpServletRequest request, final HttpServletResponse response) {
		
		LOOGER.infoLog("StandardCintroller: createNewsSlider()");

		DateConverter.setDateFooter(controllerData.getIndexDate(), request);
		String action = request.getParameter("action");
		int pageNumber = TypeConverter.string2int(request.getParameter("page"),0);
		
		Set<News> newsList = MySql.getInstance().getNewsQueries().loadNewsList();
		Map<String,Set<News>> splitedNewsList = FrontendService.splitNewsList(newsList);
		int slider = splitedNewsList.size();
		
		if(action == null) {
			controllerData.setPageReminderNewsList(pageNumber);
		} else if(action.equals("next")) {
			pageNumber = controllerData.getPageReminderNewsList() + 1;
			controllerData.setPageReminderNewsList(pageNumber);
		} else {
			pageNumber = controllerData.getPageReminderNewsList() - 1;
			controllerData.setPageReminderNewsList(pageNumber);
		}
		
		if(slider < pageNumber) {
			pageNumber = slider;
			controllerData.setPageReminderNewsList(pageNumber);
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
		
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);
		
		String newsId = request.getParameter("id");
		LOOGER.infoLog("StandardCintroller: read News " + newsId);
		News messageToRead = new News();
		if(newsId != null) {
			messageToRead = MySql.getInstance().getNewsQueries().loadNews(TypeConverter.string2int(newsId, 0));
		}
		
		request.getSession().setAttribute("messageToRead", messageToRead);
				
		return  new ModelAndView("personal/newsreader");
	}
	
	@GetMapping(value = "/politik")
	public ModelAndView politik(final HttpServletRequest request, final HttpServletResponse response) {
		
		LOOGER.infoLog("StandardCintroller: politik()");
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);		
		return  new ModelAndView("political/politik");
	}
	
	@GetMapping(value = "/politik-werdegang")
	public ModelAndView werdegang(final HttpServletRequest request, final HttpServletResponse response) {
		
		LOOGER.infoLog("StandardCintroller: werdegang()");
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);	
		return new ModelAndView("political/politikwerdegang");
	}
	
	@GetMapping(value = "/personal")
	public ModelAndView personal(final HttpServletRequest request, final HttpServletResponse response) {
		
		LOOGER.infoLog("StandardCintroller: personal()");
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);		
		return new ModelAndView("personal/personal");
	}
	
	@GetMapping(value = "/datenschutz")
	public ModelAndView data(final HttpServletRequest request, final HttpServletResponse response) {
		
		LOOGER.infoLog("StandardCintroller: datenschutz()");
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);	
		return new ModelAndView("orga/datenschutz");
	}
	
	@GetMapping(value = "/impressum")
	public ModelAndView impressum(final HttpServletRequest request, final HttpServletResponse response) {
		
		LOOGER.infoLog("StandardCintroller: impressum()");
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);	
		return new ModelAndView("orga/impressum");
	}
	
	@GetMapping(value = "/kontakt")
	public ModelAndView kontakt(final HttpServletRequest request, final HttpServletResponse response) {
		
		LOOGER.infoLog("StandardCintroller: kontakt()");
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);	
		return new ModelAndView("orga/kontaktdaten");
	}

}