package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import wrapper.NumberWrapper;

public class WrapperTests {

	@Test
	public void testDoubleToByteConversion() {
		double num = Math.PI;
		NumberWrapper nw = new NumberWrapper(num);

		assertEquals(nw.getValueByte(), (byte) num);
	}

	@Test 
	public void testIntToLongConversion() {
		int num = Integer.MAX_VALUE;
		NumberWrapper nw = new NumberWrapper(num);

		assertEquals(nw.getValueLong(), num);
	}

}
