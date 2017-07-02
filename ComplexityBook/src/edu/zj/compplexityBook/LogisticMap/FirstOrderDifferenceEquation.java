package edu.zj.compplexityBook.LogisticMap;

import java.util.function.BiConsumer;

public abstract class FirstOrderDifferenceEquation<T extends Number> {
	public abstract T next(T current);
	public abstract void iterate(T initial, int max, BiConsumer<Integer, T> handler);

}
