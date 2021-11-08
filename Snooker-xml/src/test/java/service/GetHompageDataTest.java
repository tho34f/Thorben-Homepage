package service;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;
import com.thorben.helloworld.service.GetHomepageData;

public class GetHompageDataTest {

	@Test
	public void getDataTest() {
		List<String> data = GetHomepageData.getData("https://wst.tv/");
		assertEquals(366, data.size());
		
	}
}
