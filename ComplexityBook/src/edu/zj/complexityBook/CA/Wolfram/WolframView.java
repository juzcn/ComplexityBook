package edu.zj.complexityBook.CA.Wolfram;

import edu.zj.complexityBook.CA.Wolfram.WolframCell.State;
import edu.zj.utils.Grid.Model.ArrayGrid;
import edu.zj.utils.Grid.View.GridDisplayCanvas;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

//public class WolframView extends CAGridView<State,Integer,WolframData> {
public class WolframView extends GridDisplayCanvas<WolframCell.State, ArrayGrid<WolframCell.State>> {
	public WolframView(WolframModel model, WolframConfig.ViewParams params ) {
		super(1, model.getColumnCount(), params.getCellSize(), Color.BLACK, model.getCaGrid());
		VBox vbox = new VBox();
		vbox.setPrefHeight(600);
		vbox.getChildren().add(super.getNode());
		setNode(vbox);
		setTitle("Wolfram CA ±ø’Õº");
	}

	
	@Override
	public Color cellFill(State data) {
		if (data == WolframCell.State.ON)
			return Color.BLACK;
		return Color.WHITE;
	}

	@Override
	public void redraw() {
		Canvas c = new Canvas(getMinColumnCount() * this.getCellSize(), getCellSize());
		((VBox)super.getNode()).getChildren().add(c);
		this.setGridGC(c.getGraphicsContext2D());
		updateViewPort();
		super.show();
	}


	@Override
	public Node getNode() {
		return new ScrollPane(super.getNode());
	}

}
