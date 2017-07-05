package edu.zj.compplexityBook.CA;

import java.math.BigInteger;

import edu.zj.compplexityBook.CA.GolData.State;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CAGridView<T extends Enum<T>> extends GridPane {
	private GolData data;
	private int rowCount, columnCount;
	public static Color BACKGROUND_COLOR = Color.WHITE;
	public static Color BORDER_COLOR = Color.BLUE;
	public String beginRow, beginColumn;

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

	public void loadData(GolData data, String beginRow, String beginColumn) {
		this.data = data;
		this.beginRow = beginRow;
		this.beginColumn = beginColumn;
		show();
	}

	protected void show() {
		System.out.println("Show ");
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				BigInteger row = new BigInteger(beginRow).add(new BigInteger(Integer.toString(i)));
				BigInteger column = new BigInteger(beginRow).add(new BigInteger(Integer.toString(j)));
				State s = data.getData(row, column);
				if (s == State.alive) {
					System.out.println("Black");
					Rectangle r = getNodeByRowColumnIndex(i, j);
					r.setFill(Color.BLACK);
				} else {
					System.out.println("White");
					Rectangle r = getNodeByRowColumnIndex(i, j);
					r.setFill(Color.WHITE);
					
				}
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
