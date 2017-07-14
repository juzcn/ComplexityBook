package edu.zj.complexityBook.LogisticMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class LogisticMap extends FirstOrderDifferenceEquation<BigDecimal> {
	protected final BigDecimal r;
	protected final int scale;
	public final static BigDecimal ONE = new BigDecimal(1);
	public final static BigDecimal ZERO = new BigDecimal(0);

	public LogisticMap(BigDecimal r, int scale) {
		this.r = r;
		this.scale = scale;
	}

	public LogisticMap(String r, int scale) {
		this(new BigDecimal(r), scale);
	}

	@Override
	public BigDecimal next(BigDecimal current) {
		return r.multiply(ONE.subtract(current)).multiply(current).setScale(scale, BigDecimal.ROUND_HALF_EVEN);
	}

	public void iterate(String initial, int max, BiConsumer<Integer, BigDecimal> handler) {
		iterate(new BigDecimal(initial), max, handler);
	}

	@Override
	public void iterate(BigDecimal initial, int max, BiConsumer<Integer, BigDecimal> handler) {
		handler.accept(0, initial);
		for (int i = 1; i <= max; i++) {
			handler.accept(i, initial = next(initial));
		}
	}

	public void variations(BigDecimal step, BiConsumer<BigDecimal, BigDecimal> handler) {
		for (BigDecimal x = ZERO; x.compareTo(ONE) <= 0; x = x.add(step)) {
			handler.accept(x, next(x));
		}
	}

	public void variations(String step, BiConsumer<BigDecimal, BigDecimal> handler) {
		variations(new BigDecimal(step), handler);
	}

	public List<BigDecimal> bifurcation(BigDecimal initial, long max) {
		ArrayList<BigDecimal> bifurcations = new ArrayList<>();
		bifurcations.add(initial);
		int index;
		for (int i = 1; i <= max; i++) {
			initial = next(initial);

			// index = bifurcations.indexOf(initial);
			for (index = 0; index < bifurcations.size(); index++) {

				if (bifurcations.get(index).setScale(5, BigDecimal.ROUND_HALF_EVEN)
						.compareTo(initial.setScale(5, BigDecimal.ROUND_HALF_EVEN)) == 0) {
					break;
				}
			}

			if (index == bifurcations.size()) {
				bifurcations.add(initial);
			} else {
				return new ArrayList<BigDecimal>(bifurcations.subList(index, bifurcations.size()));
			}
		}
		return bifurcations;

	}

	public static void bifurcationDiagram(String r1, String r2, String x0, String samples,
			BiConsumer<BigDecimal, BigDecimal> handle, int scale) {
		BigDecimal r1v = new BigDecimal(r1);
		BigDecimal r2v = new BigDecimal(r2);
		BigDecimal x0v = new BigDecimal(x0);
		BigDecimal samplesv = new BigDecimal(samples);
		BigDecimal step = (r2v.subtract(r1v)).divide(samplesv, scale, BigDecimal.ROUND_HALF_EVEN);

		List<BigDecimal> burfications;
		for (BigDecimal r = r1v; r.compareTo(r2v) <= 0; r = r.add(step)) {
			burfications = new LogisticMap(r, 10).bifurcation(x0v, 1000);
			System.out.println(" r = " + r + "  periods = " + burfications.size() + " " + burfications);
			for (BigDecimal x : burfications) {
				handle.accept(r, x);
			}
		}

	}

	public static void bifurcationDiagram1(String r1, String r2, String x0, String samples,
			BiConsumer<BigDecimal, BigDecimal> plot) {
		// r = 2.4 4.0 x0 random
		BigDecimal r1v = new BigDecimal(r1);
		BigDecimal r2v = new BigDecimal(r2);
		BigDecimal x0v = new BigDecimal(x0);
		BigDecimal samplesv = new BigDecimal(samples);
		BigDecimal step = (r2v.subtract(r1v)).divide(samplesv, 10, BigDecimal.ROUND_HALF_EVEN);

		BigDecimal x;
		for (BigDecimal r = r1v; r.compareTo(r2v) <= 0; r = r.add(step)) {

			// discard initial 1000
			x = x0v;
			for (int i = 1; i <= 1000; i++) {
				x = r.multiply(ONE.subtract(x)).multiply(x);
				x = x.setScale(10, BigDecimal.ROUND_HALF_EVEN);
			}

			for (int i = 1; i <= 100; i++) {
				x = r.multiply(ONE.subtract(x)).multiply(x);
				x = x.setScale(10, BigDecimal.ROUND_HALF_EVEN);
				System.out.println("r = " + r + "  x= " + x);
				plot.accept(r, x);
			}
		}

	}

}
