package edu.zj.compplexityBook.Genetics.BagProblem;

import edu.zj.compplexityBook.Genetics.Solution;

public class BPSolution extends Solution {
	private final byte[] selections;

	public BPSolution(byte[] selections) {
		this.selections = selections;
	}

	public byte[] getSelections() {
		return selections;
	}

	public double getTotalPrice() {
		double p = 0;
		BagProblem problem = getProblem();
		for (int i = 0; i < selections.length; i++) {
			if (selections[i] == 1) {
				p += problem.getBags()[i].getPrice();
			}
		}
		return p;
	}

	public double getTotalWeight() {
		double w = 0;
		BagProblem problem = getProblem();
		for (int i = 0; i < selections.length; i++) {
			if (selections[i] == 1)
				w += problem.getBags()[i].getWeight();
		}
		return w;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BPChromosome encode() {
		return new BPChromosome(selections);
	}

}
