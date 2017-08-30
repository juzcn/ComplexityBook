package edu.zj.complexityBook.LogisticMap.Precision;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.zj.complexityBook.LogisticMap.LogisticMap;
import edu.zj.complexityBook.UI.Model;

public class LMModel extends Model {
	private final LogisticMap lm10;
	private final LogisticMap lm12;
	private final LogisticMap lm;
	private BigDecimal currentValue10;
	private BigDecimal currentValue12;
	private BigDecimal currentValue;

	public LMModel(LMConfig.ModelParams params) {
		lm10 = new LogisticMap(params.getR(), params.getX0(), params.getScale1());
		lm12 = new LogisticMap(params.getR(), params.getX0(), params.getScale2());
		lm = new LogisticMap(params.getR(), params.getX0(), params.getScale3());
		currentValue10=params.getX0();
		currentValue12=params.getX0();
		currentValue=params.getX0();
	}

	@Override
	public void stepRun() {
		step++;
		currentValue10=lm10.next(currentValue10);
		currentValue12=lm12.next(currentValue12);
		currentValue=lm.next(currentValue);
	}

	public LogisticMap getLm10() {
		return lm10;
	}

	public LogisticMap getLm12() {
		return lm12;
	}

	public LogisticMap getLm() {
		return lm;
	}

	public BigDecimal getCurrentValue10() {
		return currentValue10;
	}

	public BigDecimal getCurrentValue12() {
		return currentValue12;
	}

	public BigDecimal getCurrentValue() {
		return currentValue;
	}
}
