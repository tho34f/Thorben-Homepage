package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.thorben.service.BundleTranslator;

public class BundleTranslatorTest {

	@Test
	public void translatBundleTest() {
		
		BundleTranslator btDe = new BundleTranslator("de");
		BundleTranslator btEn = new BundleTranslator("en");
		
		String valueDe = btDe.translatBundle("global.politic.desc");
		String valueEn = btEn.translatBundle("global.politic.desc");
		
		assertEquals("Auf diesen Seiten finden Sie mher �ber meine politische Agenda.", valueDe);
		assertEquals("de", btDe.getLanguage());
		assertEquals("On these pages you will find more about my political agenda.", valueEn);
		
	}
}
