package edu.zj.complexityBook.LogisticMap;

public class LogisticEquation {
	private final double r;
	private final double x0;

	public LogisticEquation(double r, double x0) {
		this.r = r;
		this.x0 = x0;
	}

	public double value(double t) {
		return (1 / (1 + (1 / x0 - 1) * Math.pow(Math.E, -r * t)));
	}

}
