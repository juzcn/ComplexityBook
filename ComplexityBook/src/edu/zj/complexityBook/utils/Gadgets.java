package edu.zj.complexityBook.utils;

import java.math.BigInteger;

public class Gadgets<T> {
	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	public final <N extends Number> N add(N op1, N op2) {
		if (op1 == null)
			return op2;
		if (op2 == null)
			return op1;
		if (op1 instanceof BigInteger) {
			return (N) ((BigInteger) op1).add((BigInteger) op2);
		} else {
			return (N) new Integer(((Integer) op1) + (Integer) op2);

		}
	}

	public final <N extends Number> N subtract(N op1, N op2) {
		if (op2 == null)
			return op1;
		if (op2 instanceof BigInteger) {
			if (op1 == null) {
				return (N) BigInteger.ZERO.subtract((BigInteger) op2);
			}
			return (N) ((BigInteger) op1).subtract((BigInteger) op2);
		} else {
			if (op1 == null) {
				return (N) new Integer(-(Integer) op2);
			}
			return (N) new Integer(((Integer) op1) + (Integer) op2);
		}
	}


}
