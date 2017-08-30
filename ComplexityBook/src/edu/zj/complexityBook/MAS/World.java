package edu.zj.complexityBook.MAS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import edu.zj.complexityBook.UI.Model;

public abstract class World<W extends World<W, E, A>, E, A extends Agent<W, E, A>> extends Model {
	protected List<A> agentList = new ArrayList<>();
	private boolean shuffle = true;
	private int step = 0;
	private String title;
	private int iDCount = 0;

	int getAndIncrement() {
		int id = iDCount++;
		return id;
	}

	public int getStep() {
		return step;
	}

	public void stepRun() {
		step++;
		Iterator<A> iterator = agentList.iterator();
		while (iterator.hasNext()) {
			A a = iterator.next();
			if (a.isDead())
				iterator.remove();
		}
		if (shuffle)
			Collections.shuffle(getAgentList(), getRandom());
		for (int i = 0; i < getAgentList().size(); i++) {
			getAgentList().get(i).stepRun();
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private E environment;

	public E getEnvironment() {
		return environment;
	}

	public void setEnvironment(E environment) {
		this.environment = environment;
	}

	public List<A> getAgentList() {
		return agentList;
	}

	public int getAgentCount() {
		return agentList.size();
	}

	public void addAgent(A agent) {
		agentList.add(agent);
	}

	@SuppressWarnings("unchecked")
	public void addAgents(A... agents) {
		agentList.addAll(Arrays.asList(agents));
	}

	public boolean isShuffle() {
		return shuffle;
	}

	public void setShuffle(boolean shuffle) {
		this.shuffle = shuffle;
	}
}
