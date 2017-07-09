package edu.zj.compplexityBook.CA;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CACanvas<T extends Enum<T>> extends Canvas {
	private final CADataBounded<T> data;
	private SimpleDoubleProperty latticeSizeProperty = new SimpleDoubleProperty();
	private final GraphicsContext gc;
	public CACanvas(CADataBounded<T> data, double latticeSize) {
		super(data.getColumnSize() * latticeSize,data.getRowSize() * latticeSize);
		this.data = data;
		this.latticeSizeProperty.setValue(latticeSize);
		gc=this.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, data.getColumnSize() * latticeSize, data.getRowSize() * latticeSize);
		for (int i = 0; i <= getRowSize(); i++) {
			gc.strokeLine(0, i*latticeSize, getColumnSize()*latticeSize, i*latticeSize);
		}
		for (int i = 0; i <=getColumnSize(); i++) {
			gc.strokeLine(i*latticeSize, 0, i*latticeSize,getRowSize()*latticeSize);
		}
	}

	
	public final int getRowSize() {
		return data.getRowSize();
	}

	public final int getColumnSize() {
		return data.getColumnSize();
	}

}
