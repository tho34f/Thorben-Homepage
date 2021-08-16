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
import com.thorben.helloworld.snooker.News;


@Controller
public class NewsWizardController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3295292219817459332L;
	private ThorbenDierkesService helloWorldService = new ThorbenDierkesService();
	
	public NewsWizardController() {
		
	}

	@Autowired
	public NewsWizardController(ThorbenDierkesService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@GetMapping(value = "/backend/newswizard")
	public String creatNews(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		
		String forwordPath = null;
		News message = null;
		String newsId = request.getParameter("id");
		
		if(request.getSession().getAttribute("user") != null) {
			forwordPath = "backend/newswizard";
			if(newsId != null) {
				message = MySql.getInstance().getNewsQueries().loadNews(TypeConverter.string2int(newsId, 0));
			}
			request.getSession().setAttribute("message", message);
		} else {
			forwordPath = helloWorldService.errorUserLogin(request);
		} 
		
		return forwordPath;
	}
	
	@PostMapping(value = "/backend/newswizard")
	public String setNews(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		String forwordPath = null;
		
		String title = request.getParameter("titleWizard");
		String teaser = request.getParameter("teaserWizard");
		String text = request.getParameter("textWizard");
		
		if(request.getSession().getAttribute("user") != null) {
			MySql.getInstance().getNewsQueries().newNewsEntry(title, text, teaser, null);
			forwordPath = "backend/newswizard";
		} else {
			forwordPath = helloWorldService.errorUserLogin(request);
		} 
		
		return forwordPath;
	}
	

	public ThorbenDierkesService getHelloWorldService() {
		return helloWorldService;
	}


}