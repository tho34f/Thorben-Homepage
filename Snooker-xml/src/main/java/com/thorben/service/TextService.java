package com.thorben.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
@Service
public class TextService {
	
	private static final ThorbenDierkesLogger LOGGER = new ThorbenDierkesLogger();
	
	public static String getRequestParameter(HttpServletRequest request, String parameterName, String defaultVariable) {
		String parameter = request.getParameter(parameterName);
		if(StringUtils.isBlank(parameter)) {
			LOGGER.infoLog("getRequestParameter(): Use defaultVariable");
			parameter = defaultVariable;
		}
		return parameter;
	}

}
