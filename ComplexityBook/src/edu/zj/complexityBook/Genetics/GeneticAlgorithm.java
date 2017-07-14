package edu.zj.complexityBook.Genetics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm<C extends Chromosome, F extends ChromosomeFactory> {
	public static long RANDOM_SEED = 1000L;
	protected static final Random random = new Random(RANDOM_SEED);
	private List<C> population;
	private double crossoverRate;
	private double mutationRate;
	private int elitismCount;

	private int generation;
	private double populationFitness;
	private F chromosomeFactory;
	private int initialSize;

	public GeneticAlgorithm(F chromosomeFactory, int size, double crossoverRate, double mutationRate,
			int elitismCount) {
		this.crossoverRate = crossoverRate;
		this.mutationRate = mutationRate;
		this.elitismCount = elitismCount;
		this.chromosomeFactory = chromosomeFactory;
		this.initialSize = size;
		this.population = chromosomeFactory.getRandomPopulation(initialSize);
	}

	public C resolve() {
		generation = 0;
		System.out.println("Initial Population Size : " + population.size() + " elitismCount : " + elitismCount);
		while (true) {
			evaluate();
			generation++;
			if (terminate()) {
				break;
			}
			selection();
			crossover();
			mutation();
		}
		return population.get(0);
	}

	public void evaluate() {

		populationFitness = 0;
		for (int i = 0; i < population.size(); i++) {
			populationFitness += population.get(i).calcFitness();
		}
		Collections.sort(population, Collections.reverseOrder());
		System.out.println("Generation : " + generation + " Size : " + population.size()
				+ " Total population fitness : " + populationFitness + " Fittest " + population.get(0).getFitness());
	}

	public boolean terminate() {
		if (generation > 2)
			return true;
		return false;
	}

	static class RouletteRegion {
		RouletteRegion(double min, double max) {
			this.min = min;
			this.max = max;
		}

		double min;
		double max;

		public String toString() {
			return "(" + min + "," + max + ")";
		}
	}

	public void selection() {
		RouletteRegion[] roulette = new RouletteRegion[population.size()];
		double min = 0;
		double max;
		for (int i = 0; i < roulette.length; i++) {
			max = min + population.get(i).getFitness() / populationFitness;
			roulette[i] = new RouletteRegion(min, max);
			min = max;
		}
		List<C> newPopulation = new ArrayList<>();
		for (int i = 0; i < elitismCount; i++) {
			newPopulation.add(chromosomeFactory.clone(population.get(i)));
		}
		int j;
		for (int i = 0; i < population.size() - elitismCount; i++) {
			double pos = random.nextDouble();
			for (j = 0; j < roulette.length; j++) {
				if (pos >= roulette[j].min && pos < roulette[j].max) {
					newPopulation.add(chromosomeFactory.clone(population.get(j)));
					break;
				}
			}
			if (j == roulette.length)
				System.out.println("pos = " + pos);
		}
		population = newPopulation;
		System.out.println("size = " + population.size() + " after selection 0=" + population.get(0).getFitness()
				+ " 1=" + population.get(1).getFitness());
	}

	public void crossover() {
		for (int i = elitismCount / 2; i < (population.size() - elitismCount) / 2; i++) {
			if (crossoverRate >= random.nextDouble())
				population.get(2 * i).crossover(population.get(2 * i + 1));
		}
		System.out.println("elitismCount = " + elitismCount + " after crossover size = " + population.size() + " 0="
				+ population.get(0).getFitness() + " 1=" + population.get(1).getFitness());
	}

	public void mutation() {
		for (int i = elitismCount; i < population.size(); i++) {
			if (mutationRate >= random.nextDouble()) {
				population.get(i).mutation();
			}
		}
		System.out.println(
				" after mutation 0=" + population.get(0).getFitness() + " 1=" + population.get(1).getFitness());

	}
}
