package com.thorben.backend.wizard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.thorben.backend.service.BackendService;
import com.thorben.objects.Termin;
import com.thorben.objects.User;
import com.thorben.queries.MySql;
import com.thorben.service.TextService;
import com.thorben.service.ThorbenDierkes;
import com.thorben.service.ThorbenDierkesLogger;
import com.thorben.service.TypeConverter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class TerminWizardController extends HttpServlet {

	private static final long serialVersionUID = -3295292219817459332L;
	private static final ThorbenDierkesLogger LOOGER = new ThorbenDierkesLogger();
	
	private static final String VIEW = "backend/terminewizard";
	private static final String SUBMIT_VIEW = "backend/submitWizard";
	
	@GetMapping(value = "/backend/terminewizard")
	public ModelAndView createEvent(final HttpServletRequest request, final HttpServletResponse response) {
		String terminId = request.getParameter("id");
		
		if(request.getSession().getAttribute("user") == null ) {
			return new ModelAndView(BackendService.errorUserLogin(request));
		} 
		
		if(terminId != null) {
			Termin tm = MySql.getInstance().getCalendarQueries().loadCalendar(TypeConverter.string2int(terminId, 0));
			request.getSession().setAttribute("termin", tm);
		}
		
		return new ModelAndView(VIEW);
	}
	
	@PostMapping(value = "/backend/terminewizard/submit")
	public ModelAndView saveEvent(final HttpServletRequest request, final HttpServletResponse response) {
		LOOGER.infoLog("TerminWizardController: start save Event");
		
		String title = TextService.getRequestParameter(request, "titleWizard", "");
		String teaser = TextService.getRequestParameter(request, "teaserWizard", "");
		String description = TextService.getRequestParameter(request, "beschreibungWizard", "");
		
		boolean success = false;
		if(request.getSession().getAttribute("user") != null) {
			User author = (User) request.getSession().getAttribute("user");
			success = MySql.getInstance().getCalendarQueries().newCalendarEntry(title, description, teaser, author.getUserLogin(), author.getId());
		} else {
			return new ModelAndView(BackendService.errorUserLogin(request));
		} 
		
		if(!success) {
			request.setAttribute(ThorbenDierkes.ERROR_MASSAGE, ThorbenDierkes.ERROR_MESSAGE_SQL_ERROR );
		}else {
			request.setAttribute(ThorbenDierkes.SUCCESS_MASSAGE, true);
		}

		return new ModelAndView(SUBMIT_VIEW);
	}


}