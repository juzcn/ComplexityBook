package edu.zj.complexityBook.MAS.Grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.zj.utils.Grid.Model.GridPos;
import edu.zj.utils.Grid.View.GridDisplayCanvas;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public abstract class GridWorldDisplay<W extends GridWorld<W, V, A>, V, A extends GridAgent<W,V, A>>
		extends GridDisplayCanvas<V> {
	private final GridWorld<W,V,  A> world;
	private boolean redrawGrid = true;
	private Map<A, GridPos> agents = new HashMap<>();
	private Canvas view1;
	private final GraphicsContext agentGC;

	public GraphicsContext getAgentGC() {
		return agentGC;
	}

	public GridWorldDisplay(int rowCount, int columnCount, double cellSize, Color borderColor, GridWorld<W, V,A> world,
			int beginRow, int beginColumn) {
		super(rowCount, columnCount, cellSize, borderColor, world.getEnvironment(), beginRow, beginColumn);
		this.world = world;
		view1 = new Canvas(this.getGrid().getColumnSize() * cellSize, this.getGrid().getRowSize() * cellSize);
		agentGC = view1.getGraphicsContext2D();
		setNode(new StackPane(getNode(), view1));
		Tooltip tp = new Tooltip("Clicked");
		Tooltip.install(view1, tp);
		tp.hide();

		view1.setOnMousePressed((e) -> {
			List<A> agents = find(e.getX(), e.getY());
			if (agents.size() != 0) {
				for (A a : agents) {
					tp.setText(a.toString() + "\n");
				}
			} else {
				tp.setText("No Agents");
			}
			tp.show(view1, e.getScreenX(), e.getScreenY());

		});
		view1.setOnMouseMoved((e) -> {
			tp.hide();;

		});
	}

	private List<A> find(double x, double y) {
		int row = (int) (y / this.getCellSize());
		int column = (int) (x / this.getCellSize());
		System.out.println("row =" + row + " column = " + column);
		GridPos pos = new GridPos(row, column);
		List<A> list = new ArrayList<>();
		for (Entry<A, GridPos> e : agents.entrySet()) {
			if (e.getValue().equals(pos))
				list.add(e.getKey());
		}
		return list;
	}

	public GridWorldDisplay(int rowCount, int columnCount, double cellSize, Color borderColor,
			GridWorld<W,V, A> world) {
		this(rowCount, columnCount, cellSize, borderColor, world, 0, 0);
	}

	public abstract void drawAgent(GridPos pos, A ga);

	@Override
	public void show() {
		super.show();
		for (A ga : world.getAgentList()) {
			drawAgent(ga.getPos(), ga);
			agents.put(ga, ga.getPos());
		}
	}

	@Override
	public void redraw() {
		if (redrawGrid) {
			super.redraw();
		}
		List<A> list = new ArrayList<>(agents.keySet());

		for (A ga : list) {
			if (ga.isDead()) {
				// deleted agents
				agentGC.clearRect(ga.getPos().getColumn() * getCellSize(), ga.getPos().getRow() * getCellSize(),
						getCellSize(), getCellSize());
				agents.remove(ga);
			}
		}
		for (A ga : world.getAgentList()) {
			GridPos pos = agents.get(ga);
			if (pos == null) {
				// new agent
				agents.put(ga, ga.getPos());
				drawAgent(ga.getPos(), ga);
				continue;
			}
			if (!ga.getPos().equals(pos)) {
				agentGC.clearRect(pos.getColumn() * getCellSize(), pos.getRow() * getCellSize(), getCellSize(),
						getCellSize());
				drawAgent(ga.getPos(), ga);
				agents.put(ga, ga.getPos());
				continue;
			}
		}
	}

	public void setRedrawGrid(boolean redrawGrid) {
		this.redrawGrid = redrawGrid;
	}

}
