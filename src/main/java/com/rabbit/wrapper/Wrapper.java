package com.rabbit.wrapper;

public abstract class Wrapper<T> implements Comparable<Wrapper<T>> {

	protected T value;

	public Wrapper(T value) {
		this.value = value;
	}

	@Override
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
