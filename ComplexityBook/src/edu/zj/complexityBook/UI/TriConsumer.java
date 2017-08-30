package edu.zj.complexityBook.UI;

@FunctionalInterface
public interface TriConsumer<R, S, T> {
	void apply(R r, S s, T t);
}
