package com.thorben.filter;

import java.io.IOException;
import java.security.SecureRandom;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
@Order(0)
public class CSRFPostFilter implements Filter {
	
	private static final SecureRandom RANDOM_GENERATOR = new SecureRandom();
	private static final String CSRF_SESSION_TOKEN = "CSRF_SESSION_TOKEN";
	private static final String REQUEST_CSRF_TOKEN = "csrfToken";
	
	@Override
	public void destroy() {
		// ...
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        
        String sessionToken = (String) httpServletRequest.getSession().getAttribute(CSRF_SESSION_TOKEN);
        if(httpServletRequest.getMethod().equals("GET") && sessionToken == null) {
        	httpServletRequest.getSession().setAttribute(CSRF_SESSION_TOKEN, generateUniqueToken(32));
        }
        
        String requestToken = httpServletRequest.getParameter(REQUEST_CSRF_TOKEN);
        if(httpServletRequest.getMethod().equals("GET") || 
        		(httpServletRequest.getMethod().equals("POST") && sessionToken != null && sessionToken.equals(requestToken))) {
        	chain.doFilter(request, response);
        } else {
        	httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
		
	}
	
	public static String generateUniqueToken(int size) {
		final byte[] formId = new byte[size];
		RANDOM_GENERATOR.nextBytes(formId);
		return String.valueOf(RANDOM_GENERATOR.nextLong());
	}

}
