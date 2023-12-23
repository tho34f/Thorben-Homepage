package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.thorben.objectbrowser.ObjectBrowser;
import com.thorben.objectbrowser.ObjectBrowserService;
import com.thorben.objectbrowser.title.filter.ObjectBrowserTitle;

import static org.mockito.Mockito.*;

import java.util.List;

import jakarta.servlet.http.*;

class ObjectBrowserControllerTest {
	
	@Test
	void setHeaderInformationTest() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		ObjectBrowser ob = null;
		ob = ObjectBrowserService.setHeaderInformation(request, 38);
		assertEquals("Benutzer", ob.getObjectTitle());
		
		List<ObjectBrowserTitle> allTitle = ob.getOb3Data().getTitle();
		ObjectBrowserTitle title = allTitle.get(0);
		assertEquals("userLogin", title.getName());
		assertEquals("User-Login", title.getDescription());
		
		ob = ObjectBrowserService.setHeaderInformation(request, 39);
		assertEquals("Neue Nachricht", ob.getButtonTitle());
		
		ob = ObjectBrowserService.setHeaderInformation(request, 40);
		assertEquals("far fa-calendar-alt", ob.getObjectIcon());
		
		ob = ObjectBrowserService.setHeaderInformation(request, 41);
		assertEquals(41, ob.getObjectType());
		
		boolean isOk = ObjectBrowserService.clearInformationAndSetError(request);
		assertEquals(true,isOk);
	}
}
