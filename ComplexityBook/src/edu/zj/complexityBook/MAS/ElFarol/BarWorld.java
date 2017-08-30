package edu.zj.complexityBook.MAS.ElFarol;

import edu.zj.complexityBook.MAS.World;
import edu.zj.complexityBook.MAS.ElFarol.BarAgent.State;

public class BarWorld extends World<BarWorld, Bar, BarAgent> {
	private int threshold;
	public BarWorld(BarConfig.ModelParams params) {
		this.setTitle("El Farol Bar Problem");
		this.setRandomSeed(params.getSeed());
		this.setEnvironment(new Bar());
		for (int i = 0; i < params.getAgentCount(); i++) {
			this.addAgent(new BarAgent(this,params.getHistoryCount(),params.getRuleExpired()));
		}
		threshold=params.getThreshold();
	}

	public int getBarAgentCount() {
		return this.getEnvironment().getAgentCount();
	}

	@Override
	public void stepRun() {
		this.getEnvironment().clear();
		for (BarAgent a:this.getAgentList()) {
			a.clearOutdatedRule();
		}
		super.stepRun();
		BarAgent.State state=BarAgent.State.Happy;
		if (this.getEnvironment().getAgentCount()>threshold) {
			state=BarAgent.State.Unhappy;
		}
		for (BarAgent a:this.getEnvironment().getAgents()) {
			a.setState(state);
		}
		for (BarAgent a:this.getAgentList()) {
			if (!this.getEnvironment().getAgents().contains(a)) {
				a.setState(State.Unknown);
			}
		}
		System.out.println("Step " + this.getStep()+" Bar people count "+this.getEnvironment().getAgentCount());
	}
}
