package com.thorben.helloworld.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import com.thorben.helloworld.queries.MySql;
import com.thorben.helloworld.service.ThorbenDierkesService;
import com.thorben.helloworld.service.TypeConverter;
import com.thorben.helloworld.snooker.Termin;


@Controller
public class TerminWizardController extends HttpServlet {

	private static final long serialVersionUID = -3295292219817459332L;
	private static ThorbenDierkesService helloWorldService = new ThorbenDierkesService();
	private static final String CONTROLLER_MAPPING = "/WEB-INF/views/jsp/backend/terminewizard.jsp";
	
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
			forwordPath = helloWorldService.errorUserLogin(request, true);
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
			MySql.getInstance().getCalendarQueries().newCalendarEntry(title, description, teaser);
			forwordPath = "backend/terminewizard";
		} else {
			forwordPath = helloWorldService.errorUserLogin(request, true);
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


}