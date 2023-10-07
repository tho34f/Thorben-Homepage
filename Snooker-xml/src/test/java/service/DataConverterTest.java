package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.Locale;
import org.junit.jupiter.api.Test;

import com.thorben.service.DateConverter;

import jakarta.servlet.http.*;

public class DataConverterTest {
	
	@Test
	public void long2DateTest() {
		//Datum: Sun Oct 31 2021 07:13:00
		long date = 1635664380878L;
		
		String mediumDateOne = DateConverter.long2Date(date, 1);
		String mediumDateTwo = DateConverter.long2Date(date, 2);
		String longDate = DateConverter.long2Date(date, 3);
		String nullDate = DateConverter.long2Date(date, 4);
		
		assertEquals("31.10.2021, 08:13:00", mediumDateOne);
		assertEquals("08:13:00", mediumDateTwo);
		assertEquals("31. Oktober 2021", longDate);
		assertEquals(null, nullDate);
	}
	
	@Test
	public void long2DateLocalTest() {
		//Datum: Sun Oct 31 2021 07:13:00
		long date = 1635664380878L;
		Locale local = Locale.of("de");
		
		String mediumDateOne = DateConverter.long2DateLocal(date, 1, local);
		String mediumDateTwo = DateConverter.long2DateLocal(date, 2, local);
		String longDate = DateConverter.long2DateLocal(date, 3, local);
		String nullDate = DateConverter.long2DateLocal(date, 4, local);
		
		assertEquals("31.10.2021, 08:13:00", mediumDateOne);
		assertEquals("08:13:00", mediumDateTwo);
		assertEquals("31. Oktober 2021", longDate);
		assertEquals(null, nullDate);
	}
	
	@Test
	public void setDateFooterTest() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		Date indexDate = new Date(1636187910688l);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("formatDate")).thenReturn(null);
		boolean isOk = DateConverter.setDateFooter(indexDate,request);
		assertEquals(true, isOk);
	}
}
