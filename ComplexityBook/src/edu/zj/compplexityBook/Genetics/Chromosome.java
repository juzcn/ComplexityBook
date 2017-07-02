package edu.zj.compplexityBook.Genetics;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Chromosome implements Comparable<Chromosome> {
	public static long RANDOM_SEED = 1000L;
	protected static final Random random = new Random(RANDOM_SEED);
	private final byte[] genes;

	protected double fitness = -1;

	// public static Comparator<Chromosome> getComparator() {
	// return comparator;
	// }

	public Chromosome(byte genes[]) {
		this.genes = genes;
	}

	public Chromosome(int length, int bound) {
		genes = new byte[length];
		for (int i = 0; i < length; i++) {
			setGene(i, random.nextInt(bound));
		}
	}

	public void setGene(int index, int gene) {
		genes[index] = (byte) gene;
	}

	public int getGene(int index) {
		return genes[index];
	}

	public double getFitness() {
		return fitness;
	}

	public abstract double calcFitness();

	public int getLength() {
		return genes.length;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < getLength(); i++) {
			sb.append(getGene(i));
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public <P extends Problem> P getProblem() {
		return (P) Problem.getProblem();
	}

	public <C extends Chromosome> void crossover(C c) {
		int crossoverPoint = random.nextInt(c.getLength() - 1) + 1;
		for (int i = 0; i < crossoverPoint; i++) {
			this.setGene(i, c.getGene(i));
		}

		for (int i = crossoverPoint; i < this.getLength(); i++) {
			c.setGene(i, this.getGene(i));
		}
	}

	public <C extends Chromosome> void mutation() {
		int mutationPoint = random.nextInt(getLength());
		if (getGene(mutationPoint) == 1) {
			setGene(mutationPoint, 0);
		} else {
			setGene(mutationPoint, 1);
		}
	}

	public abstract <S extends Solution> S decode();

	public byte[] getGenes() {
		return genes;
	}

	@Override
	public int compareTo(Chromosome c) {
		if (fitness > c.fitness)
			return 1;
		if (fitness < c.fitness)
			return -1;
		return 0;
	}
}
