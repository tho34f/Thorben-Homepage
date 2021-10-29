package com.thorben.helloworld.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.thorben.helloworld.service.DateConverter;
import com.thorben.helloworld.service.ThorbenDierkesService;


@Controller
public class MathController extends HttpServlet {

	private static final long serialVersionUID = -3295292219817459332L;
	private static ThorbenDierkesService helloWorldService = new ThorbenDierkesService();
	private static Date indexDate = new Date();
	private static String language;

	@GetMapping(value = "/math")
	public String start(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		
		return "math/math";
	}
	

	public static ThorbenDierkesService getHelloWorldService() {
		return helloWorldService;
	}


	public static String getLanguage() {
		return language;
	}


	public static void setLanguage(String language) {
		MathController.language = language;
	}


}