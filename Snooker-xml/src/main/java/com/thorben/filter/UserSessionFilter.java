package com.thorben.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.thorben.service.ThorbenDierkes;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
@Order(1)
public class UserSessionFilter implements Filter {
	
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
        
        String url = httpServletRequest.getRequestURI();
        String loginUrl = httpServletRequest.getContextPath() + "/backend/login";
        String loginAction = httpServletRequest.getContextPath() + "/backend/backendindex";
        httpServletRequest.getSession().getAttribute("user");
        
        if(httpServletRequest.getSession().getAttribute("user") != null || loginUrl.equals(url) ||
        		(httpServletRequest.getMethod().equals("POST") && loginAction.equals(url))) {
            chain.doFilter(request, response);
        } else {
        	httpServletRequest.getSession().setAttribute(ThorbenDierkes.IS_LOGIN_OK, false);
    		request.setAttribute(ThorbenDierkes.ERROR_MASSAGE, ThorbenDierkes.USER_NOT_LOGIN );
        	httpServletResponse.sendRedirect(loginUrl);
        }
	}

}
