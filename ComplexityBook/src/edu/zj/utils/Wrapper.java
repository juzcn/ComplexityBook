package edu.zj.utils;

public class Wrapper<T> {
	public Wrapper() {
	}
	public Wrapper(T value) {
		this.value=value;
	}
	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
