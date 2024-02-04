package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Image;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.thorben.objects.News;
import com.thorben.objects.Termin;
import com.thorben.service.BackendService;

class ThorbenDierkesServiceTest {

	@Test
	void spliListTest() {
		
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
		
		splitedTerminList = BackendService.splitTerminList(terminList);
		splitedNewsList = BackendService.splitNewsList(newsList);
		
		sliderNews = splitedNewsList.size();
		sliderEvents = splitedTerminList.size();
		
		assertEquals(1,sliderNews);
		assertEquals(1,sliderEvents);
		
		createNews(newsList,4, null, null, null, null, 0, 0);
		createNews(newsList,5, null, null, null, null, 0, 0);
		createNews(newsList,6, null, null, null, null, 0, 0);
		
		splitedNewsList = BackendService.splitNewsList(newsList);
		sliderNews = splitedNewsList.size();
		
		assertEquals(2,sliderNews);
		
		
	}
	
	@Test
	void errorTest() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		String loginTrue = BackendService.errorUserLogin(request);
		String loginFalse = BackendService.errorUserLogin(request);
		
		assertEquals("/WEB-INF/views/jsp/backend/login.jsp",loginTrue);
		assertEquals("backend/login",loginFalse);
		
		boolean isOk = BackendService.errorMessage(request);
		assertEquals(true,isOk);
	}
	
	@Test
	void idTest() {
		BackendService tds = new BackendService();
		boolean isIdOk = tds.generateId() > 0;
		assertEquals(true,isIdOk);
		
	}
	
	@Test
	void snookerTest() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		boolean isIdOk = BackendService.setSeason("2021", request);
		assertEquals(true,isIdOk);
		
	}
	
	private void createNews(Set<News> newsList,int id, String teaser, String title, Image img, String text, long creationDate, long changeDate) {
		News newsOne = new News(id,teaser,title,img,text,creationDate,changeDate,2);
		newsList.add(newsOne);
	}
	
	private void createEvents(Set<Termin> terminList,int id, String teaser, String title, long date, String text, long creationDate, long changeDate) {
		Termin newsOne = new Termin(title,text,date,id,teaser,creationDate,changeDate,2);
		terminList.add(newsOne);
	}
}
