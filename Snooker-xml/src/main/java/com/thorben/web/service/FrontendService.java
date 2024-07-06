package com.thorben.web.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.thorben.objects.News;
import com.thorben.objects.Termin;
import com.thorben.service.ThorbenDierkesLogger;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class FrontendService {
	
	private static final ThorbenDierkesLogger LOOGER = new ThorbenDierkesLogger();
	
	public static Map<String,Set<News>> splitNewsList(Set<News> objectList){
		
		LOOGER.infoLog("BackendService: splitNewsList start");
		
		Map<String,Set<News>> splitedNewsList = new HashMap<>();
		Set<News> helpNewsList = new HashSet<>();
		int counter = 0;
		int newssilderpage = 1;
		Iterator<News> it = objectList.iterator();
		while(it.hasNext()) {
			helpNewsList.add(it.next());
			counter++;
			if(counter == 5) {
				splitedNewsList.put("newssilderpage" + newssilderpage, helpNewsList);
				newssilderpage++;
				helpNewsList = new HashSet<>();
				counter = 0;
			}
		}
		if(counter != 5) {
			splitedNewsList.put("newssilderpage" + newssilderpage, helpNewsList);
		}
		
		return splitedNewsList;
	}
	
	public static Map<String,Set<Termin>> splitTerminList(Set<Termin> objectList){
		
		LOOGER.infoLog("BackendService: splitTerminList start");
		
		Map<String,Set<Termin>> splitedTerminList = new HashMap<>();
		Set<Termin> helpTerminList = new HashSet<>();
		int counter = 0;
		int terminsilderpage = 1;
		Iterator<Termin> it = objectList.iterator();
		while(it.hasNext()) {
			helpTerminList.add(it.next());
			counter++;
			if(counter == 5) {
				splitedTerminList.put("terminsilderpage" + terminsilderpage, helpTerminList);
				terminsilderpage++;
				helpTerminList = new HashSet<>();
				counter = 0;
			}
		}
		if(counter != 5) {
			splitedTerminList.put("terminsilderpage" + terminsilderpage, helpTerminList);
		}
		
		return splitedTerminList;
	}

}
