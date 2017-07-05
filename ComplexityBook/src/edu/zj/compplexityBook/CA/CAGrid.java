package edu.zj.compplexityBook.CA;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CAGrid<T extends Enum<T>> extends GridPane {
	private final CADataBounded<T> data;
	private SimpleDoubleProperty latticeSizeProperty = new SimpleDoubleProperty();

	public CAGrid(CADataBounded<T> data, double latticeSize) {
		this.data = data;
		this.latticeSizeProperty.setValue(latticeSize);
		this.setPrefSize(getColumnSize() * latticeSize, getRowSize() * latticeSize);
		Rectangle rectangle;
		for (int i = 0; i < getRowSize(); i++) {
			for (int j = 0; j < getColumnSize(); j++) {
				rectangle = new Rectangle(latticeSize, latticeSize, Color.WHITE);
				rectangle.setStroke(Color.BLACK);
				rectangle.setStrokeWidth(1);
				this.add(rectangle, j, i);
			}

		}
	}

	public final int getRowSize() {
		return data.getRowSize();
	}

	public final int getColumnSize() {
		return data.getColumnSize();
	}

}
