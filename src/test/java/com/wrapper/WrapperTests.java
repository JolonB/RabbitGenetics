package com.wrapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.rabbit.wrapper.NumberWrapper;

import org.junit.jupiter.api.Test;

public class WrapperTests {

	@Test
	public void testDoubleToByteConversion() {
		double num = Math.PI;
		NumberWrapper numWrapper = new NumberWrapper(num);

		assertEquals((byte) numWrapper.getValueByte(), (byte) num, "Double was not converted to byte correctly");
	}

	@Test
	public void testIntToLongConversion() {
		int num = Integer.MAX_VALUE;
		NumberWrapper numWrapper = new NumberWrapper(num);

		assertEquals((long) numWrapper.getValueLong(), (long) num, "Integer was not converted to a long correctly");
	}

}
