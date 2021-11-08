package service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Image;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.thorben.helloworld.service.ThorbenDierkesService;
import com.thorben.helloworld.snooker.News;
import com.thorben.helloworld.snooker.Termin;

public class ThorbenDierkesServiceTest {

	@Test
	public void spliListTest() {
		
		Set<News> newsList = new HashSet<>();
		Set<Termin> terminList = new HashSet<>();
		Map<String,Set<Termin>> splitedTerminList = new HashMap<>();
		Map<String,Set<News>> splitedNewsList = new HashMap<>();
		int sliderNews;
		int sliderEvents;
		
		createNews(newsList,1, null, null, null, null, 0, 0);
		createNews(newsList,2, null, null, null, null, 0, 0);
		createNews(newsList,3, null, null, null, null, 0, 0);
		
		createEvents(terminList, 1, null, null, 0 , null, 0,0);
		createEvents(terminList, 2, null, null, 0 , null, 0,0);
		createEvents(terminList, 3, null, null, 0 , null, 0,0);
		
		splitedTerminList = ThorbenDierkesService.splitTerminList(terminList);
		splitedNewsList = ThorbenDierkesService.splitNewsList(newsList);
		
		sliderNews = splitedNewsList.size();
		sliderEvents = splitedTerminList.size();
		
		assertEquals(1,sliderNews);
		assertEquals(1,sliderEvents);
		
		createNews(newsList,4, null, null, null, null, 0, 0);
		createNews(newsList,5, null, null, null, null, 0, 0);
		createNews(newsList,6, null, null, null, null, 0, 0);
		
		splitedNewsList = ThorbenDierkesService.splitNewsList(newsList);
		sliderNews = splitedNewsList.size();
		
		assertEquals(2,sliderNews);
		
		
	}
	
	@Test
	public void errorTest() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		String loginTrue = ThorbenDierkesService.errorUserLogin(request,true);
		String loginFalse = ThorbenDierkesService.errorUserLogin(request,false);
		
		assertEquals("/WEB-INF/views/jsp/backend/login.jsp",loginTrue);
		assertEquals("backend/login",loginFalse);
		
		boolean isOk = ThorbenDierkesService.errorMessage(request);
		assertEquals(true,isOk);
	}
	
	private void createNews(Set<News> newsList,int id, String teaser, String title, Image img, String text, long creationDate, long changeDate) {
		News newsOne = new News(id,teaser,title,img,text,creationDate,changeDate);
		newsList.add(newsOne);
	}
	
	private void createEvents(Set<Termin> terminList,int id, String teaser, String title, long date, String text, long creationDate, long changeDate) {
		Termin newsOne = new Termin(title,text,date,id,teaser,creationDate,changeDate);
		terminList.add(newsOne);
	}
}
