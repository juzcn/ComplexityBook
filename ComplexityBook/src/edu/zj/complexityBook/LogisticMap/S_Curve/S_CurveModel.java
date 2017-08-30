package edu.zj.complexityBook.LogisticMap.S_Curve;

import edu.zj.complexityBook.LogisticMap.LogisticEquation;
import edu.zj.complexityBook.UI.Model;

public class S_CurveModel extends Model {
	private LogisticEquation equation;
	private final double dt;
	private double currentT;
	private double currentValue;
	public S_CurveModel(S_CurveConfig.ModelParams params) {
		equation=new LogisticEquation(params.getR().doubleValue(),params.getX0().doubleValue());
		this.dt=params.getDt().doubleValue();
		this.currentT=0;
		this.currentValue=params.getX0().doubleValue();
	}
	@Override
	public void stepRun() {
		step++;
		currentT += dt;
		currentValue = equation.value(currentT);
	}
	public double getCurrentT() {
		return currentT;
	}
	public double getCurrentValue() {
		return currentValue;
	}
}
