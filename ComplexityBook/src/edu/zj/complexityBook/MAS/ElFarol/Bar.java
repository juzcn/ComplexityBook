package edu.zj.complexityBook.MAS.ElFarol;

import java.util.ArrayList;
import java.util.List;

public class Bar {
	private List<BarAgent> agents=new ArrayList<>();

	public int getAgentCount() {
		return agents.size();
	}

	public void clear() {
		agents.clear();;
	}

	public void gotoBar(BarAgent agent) {
		agents.add(agent);
	}

	public List<BarAgent> getAgents() {
		return agents;
	}
}
