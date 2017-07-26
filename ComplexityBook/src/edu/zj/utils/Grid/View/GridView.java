package edu.zj.utils.Grid.View;

import edu.zj.utils.Grid.Data.AbstractGrid;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class GridView<T, N extends Number & Comparable<N>, CA extends AbstractGrid<T, N>> 
extends GridPane implements I_GridView {
	private CA data;
	private int rowCount, columnCount;
	public Color BACKGROUND_COLOR = Color.WHITE;
	public final Color borderColor;
	public N beginRow, beginColumn;

	public GridView(int rowCount, int columnCount, double latticeSize) {
		this(rowCount, columnCount, latticeSize, Color.BLUE);
	}

	public GridView(int rowCount, int columnCount, double latticeSize, Color borderColor) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		Rectangle rectangle;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				rectangle = new Rectangle(latticeSize, latticeSize, BACKGROUND_COLOR);
				if (borderColor != null) {
					rectangle.setStroke(borderColor);
					rectangle.setStrokeWidth(1);
				}
				this.add(rectangle, j, i);
			}
		}
		this.borderColor = borderColor;
	}

	public void setBegin(N beginRow, N beginColumn) {
		this.beginRow = beginRow;
		this.beginColumn = beginColumn;
	}

	public void loadData(CA data) {
		this.data = data;
	}

	public abstract void draw(T data, int row, int column);

	public void show() {
		// System.out.println("Show ");
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				N row = data.add(beginRow, i);
				N column = data.add(beginColumn, j);
				draw(data.getData(row, column), i, j);
			}
		}
	}

	public final int getRowCount() {
		return rowCount;
	}

	public final int getColumnCount() {
		return columnCount;
	}

	public Rectangle getNodeByRowColumnIndex(final int row, final int column) {
		Node result = null;
		ObservableList<Node> childrens = this.getChildren();

		for (Node node : childrens) {
			if (getRowIndex(node) == row && getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}

		return (Rectangle) result;
	}
	
	public <GC extends GridCell<T,N>> void redraw(GC[] cells) {
		
	}
}
