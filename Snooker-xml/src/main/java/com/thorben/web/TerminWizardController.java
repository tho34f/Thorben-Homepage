package com.thorben.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;

import com.thorben.objects.Termin;
import com.thorben.objects.User;
import com.thorben.queries.MySql;
import com.thorben.service.ThorbenDierkesService;
import com.thorben.service.TypeConverter;


@Controller
public class TerminWizardController extends HttpServlet {

	private static final long serialVersionUID = -3295292219817459332L;
	private static ThorbenDierkesService helloWorldService = new ThorbenDierkesService();
	private static final String CONTROLLER_MAPPING = "/WEB-INF/views/jsp/backend/terminewizard.jsp";
	private static String language;
	
	@Override
	public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		String forwordPath = null;
		Termin tm = null;
		String terminId = request.getParameter("id");
		
		if(request.getSession().getAttribute("user") != null) {
			forwordPath = CONTROLLER_MAPPING;
			if(terminId != null) {
				tm = MySql.getInstance().getCalendarQueries().loadCalendar(TypeConverter.string2int(terminId, 0));
			}
			request.getSession().setAttribute("termin", tm);
		} else {
			forwordPath = ThorbenDierkesService.errorUserLogin(request, true);
		} 
		
		try {
			request.getServletContext().getRequestDispatcher(forwordPath).forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		String forwordPath = null;
		
		String title = request.getParameter("titleWizard");
		String teaser = request.getParameter("teaserWizard");
		String description = request.getParameter("beschreibungWizard");
		
		if(request.getSession().getAttribute("user") != null) {
			User author = (User) request.getSession().getAttribute("user");
			MySql.getInstance().getCalendarQueries().newCalendarEntry(title, description, teaser, author.getUserLogin(), author.getId());
			forwordPath = "backend/terminewizard";
		} else {
			forwordPath = ThorbenDierkesService.errorUserLogin(request, true);
		} 
		
		try {
			request.getServletContext().getRequestDispatcher(forwordPath).forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public ThorbenDierkesService getHelloWorldService() {
		return helloWorldService;
	}

	public static String getLanguage() {
		return language;
	}

	public static void setLanguage(String language) {
		TerminWizardController.language = language;
	}


}