package com.thorben.backend.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.thorben.service.TextService;
import com.thorben.service.ThorbenDierkesLogger;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class ObjectBrowser3Service {
	
	private static final ThorbenDierkesLogger LOOGER = new ThorbenDierkesLogger();
	
	public static Map<String,String> getOb3FilterValue(HttpServletRequest request){
		
		LOOGER.infoLog("ObjectBrowser3Service: getOb3FilterValue start");
		
		Map<String,String> filterValue = new HashMap<>();
		String objectId = TextService.getRequestParameter(request, "ob3Id", "");
		if(StringUtils.isBlank(objectId)) {
			return filterValue;
		}
		
		LOOGER.infoLog("ObjectBrowser3Service: getOb3FilterValue - getFilterValue for OB3 " + objectId);
		
		for(Entry<String,String[]> parameter :  request.getParameterMap().entrySet()) {
			if(parameter.getKey().contains(objectId)) {
				filterValue.put(parameter.getKey(), parameter.getValue()[0]);
			}
		}
		return filterValue;
	}

}
