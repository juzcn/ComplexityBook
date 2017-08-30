package edu.zj.complexityBook.LogisticMap.Sensibility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.zj.complexityBook.LogisticMap.LogisticMap;
import edu.zj.complexityBook.UI.Model;

public class LMModel extends Model {
	private final LogisticMap lm1;
	private final LogisticMap lm2;
	private BigDecimal currentValue1;
	private BigDecimal currentValue2;

	public LMModel(LMConfig.ModelParams params) {
		lm1 = new LogisticMap(params.getR(), params.getX01(), params.getScale());
		lm2 = new LogisticMap(params.getR(), params.getX02(), params.getScale());
		currentValue1=params.getX01();
		currentValue2=params.getX02();
	}

	@Override
	public void stepRun() {
		step++;
		currentValue1=lm1.next(currentValue1);
		currentValue2=lm2.next(currentValue2);
	}

	public LogisticMap getLm1() {
		return lm1;
	}

	public LogisticMap getLm2() {
		return lm2;
	}

	public BigDecimal getCurrentValue1() {
		return currentValue1;
	}

	public BigDecimal getCurrentValue2() {
		return currentValue2;
	}

}
