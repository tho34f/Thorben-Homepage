package com.thorben.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thorben.objectbrowser.ObjectBrowser;
import com.thorben.objectbrowser.ObjectBrowserService;
import com.thorben.service.ThorbenDierkes;
import com.thorben.service.TypeConverter;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ObjectBrowserController {
	
	@PostMapping(value = "/backend/applyObjectBrowserFilter", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean filterOb3Data(HttpServletRequest request) {
		int objectId = TypeConverter.string2int(request.getParameter("ob3Id"), 0);
		ObjectBrowser ob = (ObjectBrowser) request.getSession().getAttribute(ThorbenDierkes.OBJEKT_BROWSER);
		return true;
	}
	
	@PostMapping(value = "/backend/resetObjectBrowserFilter", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean resetOb3Data(HttpServletRequest request) {
		int objectId = TypeConverter.string2int(request.getParameter("ob3Id"), 0);
		
		ObjectBrowser ob = ObjectBrowserService.setHeaderInformation(objectId);
		ObjectBrowserService.getInformationForOb(ob, request);
		request.getSession().setAttribute(ThorbenDierkes.OBJEKT_BROWSER, ob);
		
		return true;
	}

}
