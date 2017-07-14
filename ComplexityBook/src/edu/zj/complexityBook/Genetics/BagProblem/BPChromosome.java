package edu.zj.complexityBook.Genetics.BagProblem;

import edu.zj.complexityBook.Genetics.BinaryChromosome;

public class BPChromosome extends BinaryChromosome {

	public BPChromosome(int length) {
		super(length);
	}

	public BPChromosome(byte[] genes) {
		super(genes);
	}

	@Override
	public double calcFitness() {
		fitness = 0;
		double weight = 0;
		BagProblem problem = getProblem();
		for (int i = 0; i < getLength(); i++) {
			if (getGene(i) == 1) {
				weight += problem.getBags()[i].getWeight();
				if (weight > problem.getCapacity()) {
					fitness = 1;
					break;
				}
				fitness += problem.getBags()[i].getPrice();
			}
		}
		return fitness;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BPSolution decode() {
		byte[] result = new byte[getLength()];
		for (int i = 0; i < result.length; i++) {
			result[i] = (byte) getGene(i);
		}
		return new BPSolution(result);
	}


}
