package com.wrapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.rabbit.wrapper.NumberWrapper;

import org.junit.jupiter.api.Test;

public class WrapperTests {

	@Test
	public void testDoubleToByteConversion() {
		double num = Math.PI;
		NumberWrapper nw = new NumberWrapper(num);

		assertEquals((byte) nw.getValueByte(), (byte) num);
	}

	@Test
	public void testIntToLongConversion() {
		int num = Integer.MAX_VALUE;
		NumberWrapper nw = new NumberWrapper(num);

		assertEquals((long) nw.getValueLong(), (long) num);
	}

}
