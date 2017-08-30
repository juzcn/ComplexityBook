package edu.zj.complexityBook.UI;

import java.util.Random;

import edu.zj.complexityBook.UI.AppConfig.Params;
import edu.zj.utils.Gadgets;

public abstract class Model {
	private Random random;
	private Long randomSeed;
	protected int step;
	private String title;
	public abstract void stepRun();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStep() {
		return step;
	}

	public void setRandomSeed(long seed) {
		this.randomSeed = seed;
	}

	public Random getRandom() {
		if (random == null) {
			if (randomSeed == null)
				random = new Random();
			else
				random = new Random(randomSeed);
		}
		return random;
	}

	public long getRandomSeed() {
		if (randomSeed == null) {
			return Gadgets.getSeed(random);
		} else
			return randomSeed;
	}
}
