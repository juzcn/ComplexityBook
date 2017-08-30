package edu.zj.utils.Grid.Model;

public class BoundCondition<V> {
	public static enum Condition {
		PERIODIC, REFLECTIVE, FIXED_VALUE
	}
	private final Condition condition;
	private final int radius;
	private final V fixed_value;

	public BoundCondition(Condition condition, int radius) {
		this(condition, radius, null);
	}

	public BoundCondition(Condition condition, int radius, V fixedValue) {
		this.condition = condition;
		this.radius = radius;
		this.fixed_value = fixedValue;
	}

	public Condition getCondition() {
		return condition;
	}

	public int getRadius() {
		return radius;
	}

	public V getFixed_value() {
		return fixed_value;
	}
}