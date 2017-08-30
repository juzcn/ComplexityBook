package edu.zj.complexityBook.MAS;

import java.lang.reflect.Field;

public abstract class Agent<W extends World<W, E, A>, E, A extends Agent<W, E, A>> {
	protected static Field[] fields;
	protected W world;
	protected boolean dead = false;
	private final int id;

	public Agent(W world) {
		id = world.getAndIncrement();
		this.world = world;
	}

	public abstract void stepRun();

	public void die() {
		dead = true;
	}

	public boolean isDead() {
		return dead;
	}

	@Override
	public String toString() {
		return "["+id+"]";
	}

	public int getId() {
		return id;
	}
	
}
