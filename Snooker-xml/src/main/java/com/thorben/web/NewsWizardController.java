package com.thorben.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;

import com.thorben.objects.News;
import com.thorben.objects.User;
import com.thorben.queries.MySql;
import com.thorben.service.ThorbenDierkesService;
import com.thorben.service.TypeConverter;


@Controller
public class NewsWizardController extends HttpServlet {
	
	private static final long serialVersionUID = -3295292219817459332L;
	private static ThorbenDierkesService helloWorldService = new ThorbenDierkesService();
	private static final String CONTROLLER_MAPPING = "/WEB-INF/views/jsp/backend/newswizard.jsp";
	private static String language;
	
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
		String text = request.getParameter("textWizard");
		
		if(request.getSession().getAttribute("user") != null) {
			User author = (User) request.getSession().getAttribute("user");
			MySql.getInstance().getNewsQueries().newNewsEntry(title, text, teaser, null, author.getUserLogin(), author.getId());
			forwordPath = CONTROLLER_MAPPING;
		} else {
			forwordPath = ThorbenDierkesService.errorUserLogin(request, true);
		} 
		
		try {
			request.getServletContext().getRequestDispatcher(forwordPath).forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	

	public static ThorbenDierkesService getHelloWorldService() {
		return helloWorldService;
	}

	public static String getLanguage() {
		return language;
	}

	public static void setLanguage(String language) {
		NewsWizardController.language = language;
	}


}