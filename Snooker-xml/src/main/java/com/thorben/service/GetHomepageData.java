package com.thorben.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetHomepageData {
	
	private GetHomepageData() {
	   	
		throw new IllegalStateException("Utility Class");
	    	
    }
	
	public static List<String> getData(String url) {
		List<String> data = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements player = doc.select("tr[class]");
			for (Element elem : player) {
				Elements element = elem.children();
				String textData = element.text();
				if(!textData.equals("")) {
					String[] splitTextData = textData.split(" ");
					data.add(splitTextData[0]);
					data.add(splitTextData[1]);
					data.add(splitTextData[2]);
				}
			}
			
			return data;
			
		} catch (IOException e) {
			e.printStackTrace();
			return data;
		}
		
	}

}
