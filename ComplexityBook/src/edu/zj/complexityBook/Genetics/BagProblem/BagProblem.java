package edu.zj.complexityBook.Genetics.BagProblem;

import edu.zj.complexityBook.Genetics.ChromosomeFactory;
import edu.zj.complexityBook.Genetics.GeneticAlgorithm;
import edu.zj.complexityBook.Genetics.Problem;

public class BagProblem extends Problem {
	private final double capacity;
	private final Bag[] bags;

	public static class Bag {
		private final double weight;
		private final double price;

		public Bag(double weight, double price) {
			this.weight = weight;
			this.price = price;
		}

		public double getWeight() {
			return weight;
		}

		public double getPrice() {
			return price;
		}

		public String toString() {
			return "[" + weight + "," + price + "]";
		}
	}

	public BagProblem(double capacity, Bag... bags) {
		this.capacity = capacity;
		this.bags = bags;

	}

	public int getN() {
		return bags.length;
	}

	public double getCapacity() {
		return capacity;
	}

	public Bag[] getBags() {
		return bags;
	}

	public double evaluate(int pos, byte[] result, double w) {
		if (pos == bags.length - 1) {
			if (bags[pos].getWeight() <= w) {
				result[0] = 1;
				return bags[pos].getPrice();
			} else {
				result[0] = 0;
				return 0d;
			}
		}
		double p1 = 0d, p2;
		byte[] r1 = new byte[result.length - 1];
		byte[] r2 = new byte[result.length - 1];

		if (bags[pos].getWeight() <= w) {
			p1 = bags[pos].getPrice() + evaluate(pos + 1, r1, w - bags[pos].getWeight());
		}

		p2 = evaluate(pos + 1, r2, w);
		if (p1 > p2) {
			result[0] = 1;
			for (int i = 0; i < r1.length; i++) {
				result[i+1] = r1[i];
			}
			return p1;
		}
		result[0] = 0;
		for (int i = 0; i < r2.length; i++) {
			result[i+1] = r2[i];
		}
		return p2;
	}


	@SuppressWarnings("unchecked")
	@Override
	public BPSolution resolve() {
		byte[] result = new byte[bags.length];
		double p = evaluate(0, result, capacity);
		System.out.println("Capacity : " + capacity + " Results : p = " + p);
		return new BPSolution(result);
	}

	public BPSolution resolve(int size, int elitismCount, double crossoverRate, double mutationRate) {
		ChromosomeFactory factory = new ChromosomeFactory(BPChromosome.class, bags.length);
		GeneticAlgorithm<BPChromosome, ChromosomeFactory> ga = new GeneticAlgorithm<>(factory, size, crossoverRate,
				mutationRate, elitismCount);
		return ga.resolve().decode();
	}

}
