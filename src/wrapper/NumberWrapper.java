package wrapper;

public class NumberWrapper extends Wrapper<Number> {

	public NumberWrapper(Number value) {
		super(value);
	}

	public Long getValueLong() {
		return (Long) this.value;
	}

	public Integer getValueInteger() {
		return (Integer) this.value;
	}

	public Short getValueShort() {
		return (Short) this.value;
	}

	public Byte getValueByte() {
		return (Byte) this.value;
	}

	public Double getValueDouble() {
		return (Double) this.value;
	}

	public Float getValueFloat() {
		return (Float) this.value;
	}

}
