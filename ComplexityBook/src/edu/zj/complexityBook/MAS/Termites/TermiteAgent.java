package edu.zj.complexityBook.MAS.Termites;

import java.util.List;

import edu.zj.complexityBook.MAS.Grid.GridAgent;
import edu.zj.utils.Grid.Model.Grid;
import edu.zj.utils.Grid.Model.GridNeighb;
import edu.zj.utils.Grid.Model.GridPos;

public class TermiteAgent extends GridAgent<TermitesWorld,Integer,TermiteAgent> {
	Grid<Integer> space;

	public TermiteAgent(TermitesWorld world, GridPos pos) {
		super(world, pos);
		space = world.getEnvironment();
	}

	private int carringWoodChip = 0;

	private GridPos randomWalk() {
		Grid<Integer> space = world.getEnvironment();
		List<GridNeighb<Integer>> pos = space.neighbs(getPos());
		return pos.get(world.getRandom().nextInt(pos.size())).getPos();
	}

	@Override
	public void stepRun() {
		if (carringWoodChip > 0 && space.get(getPos()) > 0) {
			space.set(getPos(), space.get(getPos()) + carringWoodChip);
			moveTo(randomWalk());
			carringWoodChip = 0;
			return;
		}
		if (carringWoodChip == 0 && space.get(getPos()) > 0) {
			carringWoodChip = space.get(getPos());
			space.set(getPos(), 0);
			moveTo(randomWalk());
			return;
		}
		if (carringWoodChip>5) {
			space.set(getPos(),carringWoodChip);
			carringWoodChip=0;
		}
		moveTo(randomWalk());
	}

	public int getCarringWoodChip() {
		return carringWoodChip;
	}

}
