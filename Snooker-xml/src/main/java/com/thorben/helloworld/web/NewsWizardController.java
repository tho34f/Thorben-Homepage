package com.thorben.helloworld.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	private static final String CONTROLLER_MAPPING = "/WEB-INF/views/jsp/backend/newswizard.jsp";
	
	public NewsWizardController() {
		
	}

	@Autowired
	public NewsWizardController(ThorbenDierkesService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forwordPath = null;
		News message = null;
		String newsId = request.getParameter("id");
		
		if(request.getSession().getAttribute("user") != null) {
			forwordPath = CONTROLLER_MAPPING;
			if(newsId != null) {
				message = MySql.getInstance().getNewsQueries().loadNews(TypeConverter.string2int(newsId, 0));
			}
			request.getSession().setAttribute("message", message);
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
		String text = request.getParameter("textWizard");
		
		if(request.getSession().getAttribute("user") != null) {
			MySql.getInstance().getNewsQueries().newNewsEntry(title, text, teaser, null);
			forwordPath = CONTROLLER_MAPPING;
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