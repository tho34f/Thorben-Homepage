package service;

import org.junit.jupiter.api.Test;

import com.thorben.service.GetHomepageData;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetHompageDataTest {

	@Test
	public void getDataTest() {
		List<String> data = GetHomepageData.getData("https://wst.tv/");
		assertEquals(366, data.size());
		
	}
}
