package edu.zj.complexityBook.MAS.Sugarscape;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import edu.zj.complexityBook.MAS.Grid.GridAgent;
import edu.zj.utils.Grid.Model.GridNeighb;
import edu.zj.utils.Grid.Model.GridPos;

public class SugarscapeAgent extends GridAgent<SugarscapeModel, Integer,SugarscapeAgent> {
	private int wealth;
	private final int vision;
	private final int metabolicRate;
	private final int deathAge;
	
	static {
		Class cls=null;
		try {
			cls = Class.forName(Thread.currentThread().getStackTrace()[1].getClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		fields=cls.getDeclaredFields();
	}


	public SugarscapeAgent(SugarscapeModel world, GridPos pos, int metabolicRate, int vision, int deathAge, int wealth) {
		super(world, pos);
		this.metabolicRate = metabolicRate;
		this.vision = vision;
		this.deathAge = deathAge;
		this.wealth = wealth;
	}

	@Override
	public void stepRun() {
		if (wealth < metabolicRate) {
			System.out.println(this + " die!");
			die();
			return;
		}
		wealth -= metabolicRate;
		List<GridNeighb<Integer>> cellList = world.getEnvironment().neighbs(getPos(), vision,true);
		int maxSugar = -1;
		int sugar;
		List<GridNeighb<Integer>> maxSugarList = new ArrayList<>();
		for (GridNeighb<Integer> cell : cellList) {
			if (world.hasAgent(cell.getPos())) {
				if (!(getPos().equals(cell.getPos())))
					continue;
			}
			sugar = cell.getValue();
			if (sugar > maxSugar) {
				maxSugar = sugar;
				maxSugarList.clear();
				maxSugarList.add(cell);
				continue;
			}
			if (sugar == maxSugar) {
				maxSugarList.add(cell);
				continue;
			}
		}

		int md = vision + 1;
		int d;
		List<GridPos> minDistace=new ArrayList<>();

		for (GridNeighb cell:maxSugarList) {
			d=cell.getDistance();
			if (d<md) {
				minDistace.clear();
				minDistace.add(cell.getPos());
			}
			if (d==md)
				minDistace.add(cell.getPos());
		}

		GridPos p = minDistace.get(world.getRandom().nextInt(minDistace.size()));
		moveTo(p);
		int cell = world.getEnvironment().get(p);
		wealth += cell;
		world.getEnvironment().set(p, 0);
	}

	public int getWealth() {
		return wealth;
	}
	
	public String toString() {
		
		StringBuffer s=new StringBuffer("{");
		for (Field f:fields) {
			s.append("?"+f.getName()+"=");
			try {
				s.append(f.get(this).toString());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		s.append("}");
		return super.toString()+s;
	}

}
