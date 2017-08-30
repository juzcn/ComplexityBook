package edu.zj.complexityBook.MAS.VirtualAnts;

import edu.zj.complexityBook.MAS.Grid.GridAgent;
import edu.zj.utils.Grid.Model.Grid;
import edu.zj.utils.Grid.Model.Grid.Direction;
import edu.zj.utils.Grid.Model.GridNeighb;
import edu.zj.utils.Grid.Model.GridPos;

public class AntAgent extends GridAgent<AntWorld,Integer,AntAgent> {

	public AntAgent(AntWorld world, GridPos pos) {
		super(world, pos);
	}

	Direction prev = Direction.RIGHT;

	private void moveTo(Direction next) {
		Grid<Integer> space = world.getEnvironment();
		GridNeighb<Integer> pos=space.neighb(getPos(), next);
		moveTo(pos.getPos());
		prev=next;
	}

	private void turn90right() {
		Direction next = Direction.values()[(prev.ordinal() + 1) % 4];
		moveTo(next);
	}

	private void turn90left() {
		Direction next = Direction.values()[(prev.ordinal() + 3) % 4];
		moveTo(next);
	}

	@Override
	public void stepRun() {
		Grid<Integer> space = world.getEnvironment();
		if (space.get(getPos()) == 1) {
			space.set(getPos(), 0);
			turn90right();
		} else {
			space.set(getPos(), 1);
			turn90left();
		}
	}


	public Direction getPrev() {
		return prev;
	}

	public void setPrev(Direction prev) {
		this.prev = prev;
	}

}
