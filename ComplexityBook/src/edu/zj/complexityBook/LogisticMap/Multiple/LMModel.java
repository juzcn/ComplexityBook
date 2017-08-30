package edu.zj.complexityBook.LogisticMap.Multiple;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.zj.complexityBook.LogisticMap.LogisticMap;
import edu.zj.complexityBook.UI.Model;

public class LMModel extends Model {
	private final List<LogisticMap> lms;
	private List<BigDecimal> currentValue;

	public LMModel(LMConfig.ModelParams params) {
		lms = new ArrayList<>();
		if (params.getR1() != null) {
			lms.add(new LogisticMap(params.getR1(), params.getX0(), params.getScale()));
		}
		if (params.getR2() != null) {
			lms.add(new LogisticMap(params.getR2(), params.getX0(), params.getScale()));
		}
		if (params.getR3() != null) {
			lms.add(new LogisticMap(params.getR3(), params.getX0(), params.getScale()));
		}
		if (params.getR4() != null) {
			lms.add(new LogisticMap(params.getR4(), params.getX0(), params.getScale()));
		}
		if (params.getR5() != null) {
			lms.add(new LogisticMap(params.getR5(), params.getX0(), params.getScale()));
		}
		if (params.getR6() != null) {
			lms.add(new LogisticMap(params.getR6(), params.getX0(), params.getScale()));
		}
		currentValue = new ArrayList<>();
		for (int i = 0; i < lms.size(); i++)
			currentValue.add(params.getX0());
	}

	@Override
	public void stepRun() {
		step++;
		for (int i=0;i<lms.size();i++) {
			currentValue.set(i, lms.get(i).next(currentValue.get(i)));
			System.out.println(step+":"+currentValue.get(i));
		}
	}

	public List<BigDecimal> getCurrentValue() {
		return currentValue;
	}

	public List<LogisticMap> getLms() {
		return lms;
	}

}
