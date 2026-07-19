package com.thorben.frontend.contoller;

import com.thorben.frontend.data.StandardControllerData;
import com.thorben.frontend.service.FrontendService;
import com.thorben.objects.News;
import com.thorben.objects.Termin;
import com.thorben.service.DateConverter;
import com.thorben.service.TypeConverter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serial;
import java.util.Map;
import java.util.Set;


@Controller
public class StandardController extends AbstractFrontendController<StandardControllerData, FrontendService> {


	@Serial
    private static final long serialVersionUID = 6766367415600280400L;
	
	@Autowired
    public StandardController() {
        this.controllerData = new StandardControllerData();
        this.controllerData.setLanguage("de");
		this.service = new FrontendService();
    }
	
	@GetMapping(value = "/")
	public ModelAndView start(final HttpServletRequest request) {

		AbstractFrontendController.LOOGER.infoLog("StandardCintroller: getIndex()");
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);

		return new ModelAndView("index");
	}
	
	@PostMapping(value = "/search")
	public ModelAndView searchfunction(final HttpServletRequest request) {

		AbstractFrontendController.LOOGER.infoLog("StandardCintroller: searchfunction()");
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);

		String searchParameter = request.getParameter("suchen");
		request.setAttribute("searchresult", searchParameter);
				
		return new ModelAndView("orga/search");
	}
	
	@GetMapping(value = "/terminslider")
	public ModelAndView createTerminSlider(final HttpServletRequest request) {

		AbstractFrontendController.LOOGER.infoLog("StandardCintroller: createTerminSlider()");
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);

		String action = request.getParameter("action");
		int pageNumber = TypeConverter.string2int(request.getParameter("page"),0);
		
		if(action == null) {
			controllerData.setPageReminderTerminList(pageNumber);
		} else if(action.equals("next")) {
			pageNumber = controllerData.getPageReminderNewsList() + 1;
			controllerData.setPageReminderTerminList(pageNumber);
		} else {
			pageNumber = controllerData.getPageReminderNewsList() - 1;
			controllerData.setPageReminderTerminList(pageNumber);
		}

		Map<String,Set<Termin>> splitedTerminList = service.getSplitTerminList();
		int slider = splitedTerminList.size();

		if(slider < pageNumber) {
			pageNumber = slider;
			controllerData.setPageReminderTerminList(pageNumber);
		} else if(pageNumber <= 0) {
			pageNumber = 1;
		}
		
		request.getSession().setAttribute("activePage", pageNumber);
		request.getSession().setAttribute("terminList", splitedTerminList.get("terminsilderpage" + pageNumber));
		request.getSession().setAttribute("sliderlenght", slider);
				
		return new ModelAndView("personal/terminslider");
	}
	
	@GetMapping(value = "/terminreader/{id}")
	public ModelAndView createTerminReader(@PathVariable String terminId, final HttpServletRequest request) {

		AbstractFrontendController.LOOGER.infoLog("StandardCintroller: read Termin " + terminId);
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);

		request.getSession().setAttribute("calendarToRead", service.getTerminToRead(terminId));
				
		return new ModelAndView("personal/terminreader");
	}
	
	@GetMapping(value = "/newsslider")
	public ModelAndView createNewsSlider(final HttpServletRequest request) {

		AbstractFrontendController.LOOGER.infoLog("StandardCintroller: createNewsSlider()");
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);

		String action = request.getParameter("action");
		int pageNumber = TypeConverter.string2int(request.getParameter("page"),0);
		
		if(action == null) {
			controllerData.setPageReminderNewsList(pageNumber);
		} else if(action.equals("next")) {
			pageNumber = controllerData.getPageReminderNewsList() + 1;
			controllerData.setPageReminderNewsList(pageNumber);
		} else {
			pageNumber = controllerData.getPageReminderNewsList() - 1;
			controllerData.setPageReminderNewsList(pageNumber);
		}

		Map<String,Set<News>> splitedNewsList = service.getSplitNewsList();
		int slider = splitedNewsList.size();
		
		if(slider < pageNumber) {
			pageNumber = slider;
			controllerData.setPageReminderNewsList(pageNumber);
		} else if(pageNumber <= 0) {
			pageNumber = 1;
		}
		
		request.getSession().setAttribute("activePage", pageNumber);
		request.getSession().setAttribute("newsList", splitedNewsList.get("newssilderpage" + pageNumber));
		request.getSession().setAttribute("sliderlenght", slider);
				
		return new ModelAndView("personal/newsslider");
	}
	
	@GetMapping(value = "/newsreader/{id}")
	public ModelAndView createNewsReader(@PathVariable String newsId, final HttpServletRequest request) {
		
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);
		AbstractFrontendController.LOOGER.infoLog("StandardCintroller: read News " + newsId);
		
		request.getSession().setAttribute("messageToRead", service.getMessageToRead(newsId));
				
		return  new ModelAndView("personal/newsreader");
	}
	
	@GetMapping(value = "/politik")
	public ModelAndView politik(final HttpServletRequest request) {

		AbstractFrontendController.LOOGER.infoLog("StandardCintroller: politik()");
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);

		return  new ModelAndView("political/politik");
	}
	
	@GetMapping(value = "/politik-werdegang")
	public ModelAndView werdegang(final HttpServletRequest request) {

		AbstractFrontendController.LOOGER.infoLog("StandardCintroller: werdegang()");
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);

		return new ModelAndView("political/politikwerdegang");
	}
	
	@GetMapping(value = "/personal")
	public ModelAndView personal(final HttpServletRequest request) {

		AbstractFrontendController.LOOGER.infoLog("StandardCintroller: personal()");
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);

		return new ModelAndView("personal/personal");
	}
	
	@GetMapping(value = "/datenschutz")
	public ModelAndView data(final HttpServletRequest request) {

		AbstractFrontendController.LOOGER.infoLog("StandardCintroller: datenschutz()");
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);

		return new ModelAndView("orga/datenschutz");
	}
	
	@GetMapping(value = "/impressum")
	public ModelAndView impressum(final HttpServletRequest request) {

		AbstractFrontendController.LOOGER.infoLog("StandardCintroller: impressum()");
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);

		return new ModelAndView("orga/impressum");
	}
	
	@GetMapping(value = "/kontakt")
	public ModelAndView kontakt(final HttpServletRequest request) {

		AbstractFrontendController.LOOGER.infoLog("StandardCintroller: kontakt()");
		DateConverter.setDateFooter(controllerData.getIndexDate(), request);

		return new ModelAndView("orga/kontaktdaten");
	}

}