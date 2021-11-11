package service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.thorben.helloworld.service.TypeConverter;

public class TypeConverterTest {

	@Test
	public void typConvertTest() {
		
		String intString = "147";
		String intStringNull = null;
		String longString = "1636187910688";
		String longStringNull = null;
		String floatString = "147.75";
		String floatStringNull = null;
		String doubleString = "1636187910688.75";
		String doubleStringNull = null;
		
		int intWert = TypeConverter.string2int(intString, 0);
		int intWertNull = TypeConverter.string2int(intStringNull, 0);
		long longWert = TypeConverter.string2long(longString, 0);
		long longWertNull = TypeConverter.string2long(longStringNull, 0);
		float floatWert = TypeConverter.string2float(floatString, 0);
		float floatWertNull = TypeConverter.string2float(floatStringNull, 0);
		double doubleWert = TypeConverter.string2double(doubleString, 0);
		double doubleWertNull = TypeConverter.string2double(doubleStringNull, 0);
		
		assertEquals(147, intWert);
		assertEquals(0, intWertNull);
		assertEquals(1636187910688L, longWert);
		assertEquals(0, longWertNull);
		assertEquals(147.75, floatWert,0);
		assertEquals(0, floatWertNull,0);
		assertEquals(1636187910688.75d, doubleWert,0);
		assertEquals(0, doubleWertNull,0);
		
	}
}
