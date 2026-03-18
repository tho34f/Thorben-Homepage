package com.thorben.backend.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thorben.objectbrowser.ObjectBrowser;
import com.thorben.objectbrowser.ObjectBrowserData;
import com.thorben.objectbrowser.ObjectBrowserService;
import com.thorben.objectbrowser.title.filter.ObjectBrowserFilter;
import com.thorben.objectbrowser.title.filter.ObjectBrowserTitle;
import com.thorben.service.ThorbenDierkes;
import com.thorben.service.ThorbenDierkesLogger;
import com.thorben.service.TypeConverter;
import com.thorben.web.data.ControllerData;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/backend/ob3")
public class ObjectBrowserController {
	
	private static final ThorbenDierkesLogger LOOGER = new ThorbenDierkesLogger();
	
	private final ControllerData controllerData;
	
	@Autowired
    public ObjectBrowserController() {
        this.controllerData = new ControllerData();
        this.controllerData.setLanguage("de");
    }
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ObjectBrowserData getData(@PathVariable("id") String id, final HttpServletRequest request) {
		
		int objectId = TypeConverter.string2int(id, 0);
		LOOGER.infoLog("ObjectBrowserController: getObjects for Ob3 " + objectId);
		
		ObjectBrowser ob = ObjectBrowserService.setHeaderInformation(objectId,request.getLocale());
		ObjectBrowserService.getInformationForOb(ob, request);
		
		return ob.getOb3Data();
	}
	
	@GetMapping(value = "/filter/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ObjectBrowserFilter> getOb3Filters(@PathVariable("id") String id, final HttpServletRequest request) {
		
		int objectId = TypeConverter.string2int(id, 0);
		LOOGER.infoLog("ObjectBrowserController: getColums for Ob3 " + objectId);
		
		ObjectBrowser ob = ObjectBrowserService.setHeaderInformation(objectId,request.getLocale());
		ObjectBrowserService.getInformationForOb(ob, request);
		
		return ob.getOb3Data().getFilter();
	}
	
	@GetMapping(value = "/colums/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ObjectBrowserTitle> getOb3Colums(@PathVariable("id") String id, final HttpServletRequest request) {
		
		int objectId = TypeConverter.string2int(id, 0);
		LOOGER.infoLog("ObjectBrowserController: getColums for Ob3 " + objectId);
		
		ObjectBrowser ob = ObjectBrowserService.setHeaderInformation(objectId,request.getLocale());
		ObjectBrowserService.getInformationForOb(ob, request);
		
		return ob.getOb3Data().getTitle();
	}
	
	@GetMapping(value = "/data/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Set<?> getOb3Data(@PathVariable("id") String id, final HttpServletRequest request) {
		
		int objectId = TypeConverter.string2int(id, 0);
		LOOGER.infoLog("ObjectBrowserController: getObjects for Ob3 " + objectId);
		
		ObjectBrowser ob = ObjectBrowserService.setHeaderInformation(objectId,request.getLocale());
		ObjectBrowserService.getInformationForOb(ob, request);
		
		return ob.getOb3Data().getObjectList();
	}
	
	@PostMapping(value = "/applyObjectBrowserFilter", produces = MediaType.APPLICATION_JSON_VALUE)
	public ObjectBrowserData filterOb3Data(HttpServletRequest request) {
		
		int objectId = TypeConverter.string2int(request.getParameter("ob3Id"), 0);
		LOOGER.infoLog("ObjectBrowserController: filterOb3Data for OB3 " + objectId);
		
		ObjectBrowser ob = ObjectBrowserService.setHeaderInformation(objectId,request.getLocale());
		ObjectBrowserService.getInformationForOb(ob, request);
		
		return ob.getOb3Data();
	}
	
	@PostMapping(value = "/resetObjectBrowserFilter", produces = MediaType.APPLICATION_JSON_VALUE)
	public ObjectBrowserData resetOb3Data(HttpServletRequest request) {
		
		int objectId = TypeConverter.string2int(request.getParameter("ob3Id"), 0);
		LOOGER.infoLog("ObjectBrowserController: resetOb3Data for OB3 " + objectId);
		
		ObjectBrowser ob = ObjectBrowserService.setHeaderInformation(objectId,request.getLocale());
		ObjectBrowserService.getInformationForOb(ob, request);
		
		return ob.getOb3Data();
	}

}
