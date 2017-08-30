package edu.zj.complexityBook.MAS.Schelling;

import java.util.List;

import edu.zj.complexityBook.MAS.Grid.GridAgent;
import edu.zj.utils.Grid.Model.GridPos;

public class SchellingAgent extends GridAgent<SchellingWorld, Integer,SchellingAgent> {
	public static enum State {
		red, blue
	}

	private final State state;

	public State getState() {
		return state;
	}

	public SchellingAgent(SchellingWorld world, GridPos pos, State state) {
		super(world, pos);
		this.state = state;
	}

	@Override
	public void stepRun() {
		List<SchellingAgent> list = neighb();
		if (list.size() > 0) {
			int sameColors = 0;
			for (SchellingAgent a : list) {
				if (a.getState() == state) {
					sameColors++;
				}
			}
			// System.out.println(this + " same colors=" +
			// sameColors+"/"+list.size());
			if ((double) sameColors / list.size() >= world.getNeighbourPercent()) {
				return;
			}
		}
		List<GridPos> pos = empties();
		if (pos.size() > 0) {
			GridPos p = pos.get(world.getRandom().nextInt(pos.size()));
			// System.out.println(this + " move to " + p);
			moveTo(p);
		}
	}
}
