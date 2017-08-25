package edu.zj.complexityBook.CA.Wolfram;

import edu.zj.utils.Grid.View.GridDisplayCanvas;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

//public class WolframView extends CAGridView<State,Integer,WolframData> {
public class WolframView extends GridDisplayCanvas<Integer> {
	public WolframView(WolframModel model, WolframConfig.ViewParams params ) {
		super(1, model.getColumnCount(), params.getCellSize(), Color.BLACK, model.getGrid());
		VBox vbox = new VBox();
		vbox.setPrefHeight(600);
		vbox.getChildren().add(super.getNode());
		setNode(vbox);
		setTitle("Wolfram CA ±ø’Õº");
	}

	
	@Override
	public Color cellFill(Integer data) {
		if (data == 1)
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
