package com.thorben.backend.wizard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.thorben.objects.News;
import com.thorben.objects.User;
import com.thorben.queries.MySql;
import com.thorben.service.BackendService;
import com.thorben.service.ThorbenDierkes;
import com.thorben.service.ThorbenDierkesLogger;
import com.thorben.service.TypeConverter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class NewsWizardController extends HttpServlet {
	
	private static final long serialVersionUID = -3295292219817459332L;
	private static final ThorbenDierkesLogger LOOGER = new ThorbenDierkesLogger();
	
	private static final String VIEW = "backend/newswizard";
	private static final String SUBMIT_VIEW = "backend/submitWizard";
	
	@GetMapping(value = "/backend/newswizard")
	public ModelAndView createOrEditNews(HttpServletRequest request, HttpServletResponse response) {
		LOOGER.infoLog("NewsWizardController: start create News");
		String newsId = request.getParameter("id");
		
		if(request.getSession().getAttribute("user") == null) {
			return new ModelAndView(BackendService.errorUserLogin(request));
		} 
		
		if(newsId != null) {
			News message = MySql.getInstance().getNewsQueries().loadNews(TypeConverter.string2int(newsId, 0));
			request.getSession().setAttribute("message", message);
		}
		
		return new ModelAndView(VIEW);
	}
	
	@PostMapping(value = "/backend/newswizard/submit")
	public ModelAndView saveNews(final HttpServletRequest request, final HttpServletResponse response) {
		LOOGER.infoLog("NewsWizardController:start save News");
		
		String title = request.getParameter("titleWizard");
		String teaser = request.getParameter("teaserWizard");
		String text = request.getParameter("textWizard");
		
		boolean success = false;
		if(request.getSession().getAttribute("user") != null) {
			User author = (User) request.getSession().getAttribute("user");
			success = MySql.getInstance().getNewsQueries().newNewsEntry(title, text, teaser, null, author.getUserLogin(), author.getId());
		} else {
			return new ModelAndView(BackendService.errorUserLogin(request));
		} 
		
		if(!success) {
			request.setAttribute(ThorbenDierkes.ERROR_MASSAGE, ThorbenDierkes.ERROR_MESSAGE_SQL_ERROR );
		} else {
			request.setAttribute(ThorbenDierkes.SUCCESS_MASSAGE, true);
		}

		return new ModelAndView(SUBMIT_VIEW);

	}

}