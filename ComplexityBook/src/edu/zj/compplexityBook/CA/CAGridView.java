package edu.zj.compplexityBook.CA;

import edu.zj.compplexityBook.utils.SparseMatrix.AbstractMatrix;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class CAGridView<T, N extends Number, CA extends CAGrid<T, N>> extends GridPane {
	private CA data;
	private int rowCount, columnCount;
	public static Color BACKGROUND_COLOR = Color.WHITE;
	public static Color BORDER_COLOR = Color.BLUE;
	public N beginRow=null, beginColumn=null;

	public CAGridView(int rowCount, int columnCount, double latticeSize) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		Rectangle rectangle;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				rectangle = new Rectangle(latticeSize, latticeSize, BACKGROUND_COLOR);
				rectangle.setStroke(BORDER_COLOR);
				rectangle.setStrokeWidth(1);
				this.add(rectangle, j, i);

			}

		}
	}

	public void setBegin(N beginRow, N beginColumn) {
		this.beginRow = beginRow;
		this.beginColumn = beginColumn;
	}

	public void loadData(CA data) {
		this.data = data;
	}

	
	public abstract void draw(T data,int row,int column);
	protected void show() {
		// System.out.println("Show ");
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				
				N row = data.add(beginRow,i);
				N column = data.add(beginColumn,j);

				draw(data.getData(row, column),i,j);
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
}
