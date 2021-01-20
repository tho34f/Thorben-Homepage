package com.mkyong.helloworld.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetHomepageData {
	
	private static List<String> provisionalRanking = null;
	
	private GetHomepageData() {
	   	
		throw new IllegalStateException("Utility Class");
	    	
    }
	
	public static List<String> getData() {
		provisionalRanking = new ArrayList<>();
		try {
			Document doc = Jsoup.connect("https://wst.tv/rankings/1-year-ranking-list/").get();
			Elements player = doc.select("tr[class]");
			for (Element elem : player) {
				Elements data = elem.children();
				String textData = data.text();
				if(!textData.equals("")) {
					String[] splitTextData = textData.split(" ");
					provisionalRanking.add(splitTextData[0]);
					provisionalRanking.add(splitTextData[1]);
					provisionalRanking.add(splitTextData[2]);
				}
			}
			
			return provisionalRanking;
			
		} catch (IOException e) {
			e.printStackTrace();
			return provisionalRanking;
		}
		
		
		
	}

	public static List<String> getProvisionalRanking() {
		return provisionalRanking;
	}

	public static void setProvisionalRanking(List<String> provisionalRanking) {
		GetHomepageData.provisionalRanking = provisionalRanking;
	}

}
