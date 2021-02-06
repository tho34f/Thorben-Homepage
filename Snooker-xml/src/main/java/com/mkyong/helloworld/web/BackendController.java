package com.mkyong.helloworld.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mkyong.helloworld.queries.UserQueries;
import com.mkyong.helloworld.service.HelloWorldService;
import com.mkyong.helloworld.service.ObjectBrowser;
import com.mkyong.helloworld.service.TypeConverter;
import com.mkyong.helloworld.snooker.User;

@Controller
public class BackendController {
	
	private final Logger logger = LoggerFactory.getLogger(BackendController.class);
	private final HelloWorldService helloWorldService;
	
	@Autowired
	public BackendController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}
	
	String login = "backend/login";
	String userNotLogin = "Der User ist nicht eingeloggt.";
	String erromessage = "errormasage";
	String isLoginOkString = "isLoginOk";
	
	@RequestMapping(value = "/backend/login", method = RequestMethod.GET)
	public String startLogin(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		request.getSession().setAttribute("user", null);
		return login;
	}
	
	@RequestMapping(value = "/backend/backendindex", method = RequestMethod.POST)
	public String checkLogin(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		User loginUser = new User();
		String forwordPath = null;
		boolean isLoginOk = false;
		
		String test = request.getParameter("username");
		
		if(request.getParameter("username") != null) {
			loginUser.setUserLogin(test);
			loginUser.setPassword(request.getParameter("password"));
		}
		
		isLoginOk = UserQueries.checkLogin(loginUser);
		request.getSession().setAttribute(isLoginOkString, isLoginOk);
		
		if(isLoginOk) {
			request.getSession().setAttribute("user", loginUser);
			forwordPath = "backend/backendindex";
			logger.info("Login war erfolgreich.");
			
		} else {
			request.setAttribute(erromessage, "Der User ist nicht gültig.");
			forwordPath = login;
			logger.info("Der User ist nicht gültig.");
		}
		
		return forwordPath;
	}
	
	@RequestMapping(value = "/backend/backendindex", method = RequestMethod.GET)
	public String createIndex(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		String forwordPath = null;
		
		if(request.getSession().getAttribute("user") != null) {
			forwordPath = "backend/backendindex";
		} else {
			request.getSession().setAttribute(isLoginOkString, false);
			request.setAttribute(erromessage, userNotLogin);
			forwordPath = login;
		} 
		
		return forwordPath;
	}
	
	@RequestMapping(value = "/backend/backendObjectBrowser", method = RequestMethod.POST)
	public String createObject(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		String forwordPath = null;
		
		if(request.getSession().getAttribute("user") != null) {
			forwordPath = "backend/backendObjectBrowser";
		} else {
			request.getSession().setAttribute(isLoginOkString, false);
			request.setAttribute(erromessage, userNotLogin);
			forwordPath = login;
		} 
		
		return forwordPath;
	}
	
	@RequestMapping(value = "/backend/backendObjectBrowser", method = RequestMethod.GET)
	public String getObject(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		String forwordPath = null;
		int objectId = TypeConverter.string2int(request.getParameter("id"), 0);
		
		if(request.getSession().getAttribute("user") != null) {
			ObjectBrowser.setHeaderInformation(request, objectId);
			request.getSession().setAttribute("objectId", objectId);
			forwordPath = "backend/backendObjectBrowser";
		} else {
			request.getSession().setAttribute(isLoginOkString, false);
			request.setAttribute(erromessage, userNotLogin);
			forwordPath = login;
		} 
		
		return forwordPath;
	}

	public HelloWorldService getHelloWorldService() {
		return helloWorldService;
	}

}
