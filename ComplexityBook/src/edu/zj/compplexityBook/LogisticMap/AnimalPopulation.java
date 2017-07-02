package edu.zj.compplexityBook.LogisticMap;

import java.math.BigDecimal;

public class AnimalPopulation extends LogisticMap {
	public BigDecimal k;

	public AnimalPopulation(BigDecimal k, BigDecimal r, int scale) {
		super(r, scale);
		this.k = k;
	}

	public AnimalPopulation(long k, BigDecimal r, int scale) {
		this(new BigDecimal(k), r, scale);
	}

	public AnimalPopulation(String k, String r, int scale) {
		this(new BigDecimal(k), new BigDecimal(r), scale);
	}

	@Override
	public BigDecimal next(BigDecimal current) {
		return super.next(current.divide(k, scale, BigDecimal.ROUND_HALF_EVEN)).multiply(k);
	}

}
