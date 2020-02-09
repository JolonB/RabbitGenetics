package wrapper;

public abstract class Wrapper<T> implements Comparable<Wrapper<T>> {

	T value;

	public Wrapper(T value) {
		this.value = value;
	}

	public String toString() {
		return "" + this.value;
	}

	public void setValue(T newValue) {
		this.value = newValue;
	}

	public T getValue() {
		return this.value;
	}

}
