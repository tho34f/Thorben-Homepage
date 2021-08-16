package com.thorben.helloworld.web;

import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.thorben.helloworld.queries.MySql;
import com.thorben.helloworld.service.ThorbenDierkesService;
import com.thorben.helloworld.service.TypeConverter;
import com.thorben.helloworld.snooker.Termin;


@Controller
public class TerminWizardController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3295292219817459332L;
	private ThorbenDierkesService helloWorldService = new ThorbenDierkesService();
	
	public TerminWizardController() {
		
	}

	@Autowired
	public TerminWizardController(ThorbenDierkesService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@GetMapping(value = "/backend/terminewizard")
	public String creatTermin(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		String forwordPath = null;
		Termin tm = null;
		String terminId = request.getParameter("id");
		
		if(request.getSession().getAttribute("user") != null) {
			forwordPath = "backend/terminewizard";
			if(terminId != null) {
				tm = MySql.getInstance().getCalendarQueries().loadCalendar(TypeConverter.string2int(terminId, 0));
			}
			request.getSession().setAttribute("termin", tm);
		} else {
			forwordPath = helloWorldService.errorUserLogin(request);
		} 
		
		return forwordPath;
	}
	
	@PostMapping(value = "/backend/terminewizard")
	public String setTermin(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		String forwordPath = null;
		
		String title = request.getParameter("titleWizard");
		String teaser = request.getParameter("teaserWizard");
		String description = request.getParameter("beschreibungWizard");
		
		if(request.getSession().getAttribute("user") != null) {
			MySql.getInstance().getCalendarQueries().newCalendarEntry(title, description, teaser);
			forwordPath = "backend/terminewizard";
		} else {
			forwordPath = helloWorldService.errorUserLogin(request);
		} 
		
		return forwordPath;
	}
	

	public ThorbenDierkesService getHelloWorldService() {
		return helloWorldService;
	}


}