package edu.zj.complexityBook.MAS.Schelling;

import edu.zj.complexityBook.MAS.Grid.GridWorld;
import edu.zj.utils.Grid.Model.Grid;
import edu.zj.utils.Grid.Model.Grid.NeighbType;
import edu.zj.utils.Grid.Model.GridPos;

public class SchellingWorld extends GridWorld<SchellingWorld, Integer, SchellingAgent> {
	private final double neighbourPercent;

	public SchellingWorld(SchellingConfig.ModelParams params) {
		this.setTitle("Schelling∏Ù¿Îƒ£–Õ");
		Grid<Integer> grid = new Grid<>(params.getRowCount(), params.getColumnCount());
		grid.setNeighbType(NeighbType.∞À¡⁄æ”);
		this.setEnvironment(grid);
		this.neighbourPercent = params.getPercent();
		int row, column;
		for (int i = 0; i < params.getBlueAgentCount(); i++) {
			while (true) {
				row = getRandom().nextInt(params.getRowCount());
				column = getRandom().nextInt(params.getColumnCount());
				GridPos pos = new GridPos(row, column);
				if (getAgentList(pos).size() == 0) {
					addAgent(new SchellingAgent(this, pos, SchellingAgent.State.blue));
					break;
				}
			}
		}
		for (int i = 0; i < params.getRedAgentCount(); i++) {
			while (true) {
				row = getRandom().nextInt(params.getRowCount());
				column = getRandom().nextInt(params.getColumnCount());
				GridPos pos = new GridPos(row, column);
				if (getAgentList(pos).size() == 0) {
					addAgent(new SchellingAgent(this, pos, SchellingAgent.State.red));
					break;
				}
			}
		}
		System.out.println("Schelling Setup £°");
		// for (SchellingAgent a:getAgentList()) {
		// System.out.println(a);
		// }
	}

	public double getNeighbourPercent() {
		return neighbourPercent;
	}

}
