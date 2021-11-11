package service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import com.thorben.helloworld.service.ObjectBrowser;
import com.thorben.helloworld.service.ObjectBrowserController;
import com.thorben.helloworld.service.ObjectBrowserTitle;

import javax.servlet.http.*;

public class ObjectBrowserControllerTest {
	
	@Test
	public void setHeaderInformationTest() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		ObjectBrowser ob = null;
		ob = ObjectBrowserController.setHeaderInformation(request, 38);
		assertEquals("Benutzer", ob.getObjectTitle());
		
		ObjectBrowserTitle[] allTitle = ob.getTableTitle();
		ObjectBrowserTitle title = allTitle[0];
		assertEquals("userLogin", title.getName());
		assertEquals("User-Login", title.getDescription());
		
		ob = ObjectBrowserController.setHeaderInformation(request, 39);
		assertEquals("Neue Nachricht", ob.getButtonTitle());
		
		ob = ObjectBrowserController.setHeaderInformation(request, 40);
		assertEquals("far fa-calendar-alt", ob.getObjectIcon());
		
		ob = ObjectBrowserController.setHeaderInformation(request, 41);
		assertEquals(41, ob.getObjectType());
		
		boolean isOk = ObjectBrowserController.clearInformationAndSetError(request);
		assertEquals(true,isOk);
	}
}
