package service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Locale;

import org.apache.tomcat.jni.Local;
import org.junit.Test;

import com.thorben.helloworld.service.DateConverter;

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
		Locale local = new Locale("de");
		
		String mediumDateOne = DateConverter.long2DateLocal(date, 1, local);
		String mediumDateTwo = DateConverter.long2DateLocal(date, 2, local);
		String longDate = DateConverter.long2DateLocal(date, 3, local);
		String nullDate = DateConverter.long2DateLocal(date, 4, local);
		
		assertEquals("31.10.2021, 08:13:00", mediumDateOne);
		assertEquals("08:13:00", mediumDateTwo);
		assertEquals("31. Oktober 2021", longDate);
		assertEquals(null, nullDate);
	}
}
