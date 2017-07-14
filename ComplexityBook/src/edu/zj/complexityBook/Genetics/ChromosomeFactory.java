package edu.zj.complexityBook.Genetics;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ChromosomeFactory {
	protected final Class<? extends Chromosome> chromosomeClass;
	protected final int chromosomeLength;

	public ChromosomeFactory(Class<? extends Chromosome> chromosomeClass, int chromosomeLength) {
		this.chromosomeClass = chromosomeClass;
		this.chromosomeLength = chromosomeLength;
	}

	@SuppressWarnings("unchecked")
	public <C extends Chromosome> C clone(C chromosome) {
		Constructor<C> constructor;
		C newChromosome = null;
		try {
			constructor = (Constructor<C>) chromosomeClass.getConstructor(byte[].class);
			newChromosome = constructor.newInstance(chromosome.getGenes());
			newChromosome.fitness = chromosome.fitness;
		} catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException
				| SecurityException | InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newChromosome;
	}

	@SuppressWarnings("unchecked")
	public <C extends Chromosome> List<C> getRandomPopulation(int size) {

		List<C> list = new ArrayList<>();
		Constructor<C> constructor = null;
		try {
			constructor = (Constructor<C>) chromosomeClass.getConstructor(int.class);
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i < size; i++) {
			try {
				list.add(constructor.newInstance(chromosomeLength));
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
