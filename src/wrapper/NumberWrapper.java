package wrapper;

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
	public int compareTo(Wrapper<Number> o) {
		if (!(o instanceof NumberWrapper)) {
			throw new IllegalArgumentException("compare to requires a NumberWrapper as a parameter");
		}
		NumberWrapper n = (NumberWrapper) o;
		if (this.value == n.value
				|| (this.value.intValue() == n.value.intValue() && this.value.doubleValue() == n.value.doubleValue())) {
			return 0;
		} else if (this.value.intValue() < n.value.intValue() || this.value.doubleValue() < n.value.doubleValue()) {
			return -1;
		} else {
			return 1;
		}
	}

	public int compareNum(Number num) {
		NumberWrapper n = new NumberWrapper(num);
		return this.compareTo(n);
	}

}
