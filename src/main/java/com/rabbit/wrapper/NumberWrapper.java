package com.rabbit.wrapper;

public class NumberWrapper extends Wrapper<Number> {

	public NumberWrapper() {
		super(0);
	}

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

	@Override
	public int compareTo(Wrapper<Number> obj) {
		if (!(obj instanceof NumberWrapper)) {
			throw new IllegalArgumentException("compare to requires a NumberWrapper as a parameter");
		}
		NumberWrapper numWrapper = (NumberWrapper) obj;
		if (this.value == numWrapper.value
				|| (this.value.intValue() == numWrapper.value.intValue() && this.value.doubleValue() == numWrapper.value.doubleValue())) {
			return 0;
		} else if (this.value.intValue() < numWrapper.value.intValue() || this.value.doubleValue() < numWrapper.value.doubleValue()) {
			return -1;
		} else {
			return 1;
		}
	}

	public int compareNum(Number num) {
		return this.compareTo(new NumberWrapper(num));
	}

}
