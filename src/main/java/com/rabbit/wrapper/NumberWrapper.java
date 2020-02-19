package com.rabbit.wrapper;

public class NumberWrapper extends Wrapper<Number> {

	public NumberWrapper(Number value) {
		super(value);
	}

	public Long getValueLong() {
		return this.value.longValue();
	}

	public Integer getValueInteger() {
		return this.value.intValue();
	}

	public Short getValueShort() {
		return this.value.shortValue();
	}

	public Byte getValueByte() {
		return this.value.byteValue();
	}

	public Double getValueDouble() {
		return this.value.doubleValue();
	}

	public Float getValueFloat() {
		return this.value.floatValue();
	}

}
