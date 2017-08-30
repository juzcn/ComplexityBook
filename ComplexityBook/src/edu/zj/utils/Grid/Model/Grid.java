package edu.zj.utils.Grid.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import edu.zj.utils.Gadgets;
import edu.zj.utils.Grid.Model.BoundCondition.Condition;

public  class Grid<V> {
	public enum Direction {
		TOP, RIGHT, DOWN, LEFT, TOP_LEFT, TOP_RIGHT, DOWN_RIGHT, DOWN_LEFT
	};

	public static enum NeighbType {
		кдаз╬с, ╟каз╬с
	};

	private String name;
	private final int rowSize;
	private final int columnSize;
	private final Object[][] array;
	private final BoundCondition<V>[] boundConditions;
	private NeighbType neighbType;

	public Grid(int rowSize, int columnSize, BoundCondition<V>[] boundCondition) {
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		array = new Object[rowSize][columnSize];
		this.boundConditions = boundCondition;
		this.neighbType = NeighbType.кдаз╬с;
	}

	@SuppressWarnings("unchecked")
	public Grid(int rowSize, int columnSize) {
		this(rowSize, columnSize,
				new BoundCondition[] { new BoundCondition<V>(Condition.PERIODIC, 1),
						new BoundCondition<V>(Condition.PERIODIC, 1), new BoundCondition<V>(Condition.PERIODIC, 1),
						new BoundCondition<V>(Condition.PERIODIC, 1) });
	}

	public void setAll(V initialValue) {
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < columnSize; j++) {
				this.set(i, j, initialValue);
			}
		}
	}

	public void setBoundCondition(V fixedValue) {
		for (int i = 0; i < 4; i++) {
			boundConditions[i] = new BoundCondition<V>(Condition.FIXED_VALUE, 1, fixedValue);
		}
	}

	public void setBoundCondition(Condition c, int radius) {
		for (int i = 0; i < 4; i++) {
			boundConditions[i] = new BoundCondition<V>(c, radius);
		}
	}

	public int getRowSize() {
		return rowSize;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public GridPos transform(GridPos pos) {
		return transform(pos.getRow(), pos.getColumn());
	}

	public GridPos transform(int row, int column) {
		// input row, column
		// if no condition, no change
		// if condition fixed-value, in radius, null, else null
		// if other conditions, in radius, change, else no change
		if (boundConditions == null)
			return new GridPos(row, column);
		if (row < 0) {
			// TOP
			if (boundConditions[Direction.TOP.ordinal()].getCondition() == Condition.FIXED_VALUE) {
				if (boundConditions[Direction.TOP.ordinal()].getRadius() + row < 0)
					return new GridPos(row, column);
				else
					return null;
			} else {
				if (boundConditions[Direction.TOP.ordinal()].getRadius() + row < 0)
					return new GridPos(row, column);
				if (boundConditions[Direction.TOP.ordinal()].getCondition() == Condition.PERIODIC) {
					row += rowSize;
				}
				if (boundConditions[Direction.TOP.ordinal()].getCondition() == Condition.REFLECTIVE) {
					row = -row - 1;
				}
			}
		}
		if (row >= rowSize) {
			// Down
			if (boundConditions[Direction.DOWN.ordinal()].getCondition() == Condition.FIXED_VALUE) {
				if (row >= rowSize + boundConditions[Direction.DOWN.ordinal()].getRadius())
					return new GridPos(row, column);
				else
					return null;
			} else {
				if (row >= rowSize + boundConditions[Direction.DOWN.ordinal()].getRadius())
					return new GridPos(row, column);
				if (boundConditions[Direction.DOWN.ordinal()].getCondition() == Condition.PERIODIC) {
					row %= rowSize;
				}
				if (boundConditions[Direction.DOWN.ordinal()].getCondition() == Condition.REFLECTIVE) {
					row = rowSize - row % rowSize - 1;
				}
			}
		}
		if (column < 0) {
			// Left
			if (boundConditions[Direction.LEFT.ordinal()].getCondition() == Condition.FIXED_VALUE) {
				if (boundConditions[Direction.LEFT.ordinal()].getRadius() + column < 0)
					return new GridPos(row, column);
				else
					return null;
			} else {
				if (boundConditions[Direction.LEFT.ordinal()].getRadius() + column < 0)
					return new GridPos(row, column);
				if (boundConditions[Direction.LEFT.ordinal()].getCondition() == Condition.PERIODIC) {
					column += columnSize;
				}
				if (boundConditions[Direction.LEFT.ordinal()].getCondition() == Condition.REFLECTIVE) {
					column = -column - 1;
				}
			}
		}
		if (column >= columnSize) {
			// Right
			if (boundConditions[Direction.RIGHT.ordinal()].getCondition() == Condition.FIXED_VALUE) {
				if (column >= columnSize + boundConditions[Direction.RIGHT.ordinal()].getRadius())
					return new GridPos(row, column);
				else
					return null;
			} else {
				if (column >= columnSize + boundConditions[Direction.RIGHT.ordinal()].getRadius())
					return new GridPos(row, column);
				if (boundConditions[Direction.RIGHT.ordinal()].getCondition() == Condition.PERIODIC) {
					column %= columnSize;
				}
				if (boundConditions[Direction.RIGHT.ordinal()].getCondition() == Condition.REFLECTIVE) {
					column = columnSize - column % columnSize - 1;
				}
			}
		}
		return new GridPos(row, column);
	}

	@SuppressWarnings("unchecked")
	public final V get(int row, int column) {
		GridPos pos = transform(row, column);
		if (pos == null) {
			if (row < 0) {
				return boundConditions[Direction.TOP.ordinal()].getFixed_value();
			}
			if (row >= rowSize) {
				return boundConditions[Direction.DOWN.ordinal()].getFixed_value();
			}
			if (column < 0) {
				return boundConditions[Direction.LEFT.ordinal()].getFixed_value();
			}
			if (column >= columnSize) {
				return boundConditions[Direction.RIGHT.ordinal()].getFixed_value();
			}
		}
		return (V) array[pos.getRow()][pos.getColumn()];
	}

	public final void set(int row, int column, V value) {
		GridPos pos = transform(row, column);
		if (pos != null) {
			array[pos.getRow()][pos.getColumn()] = value;
		}
	}

	public final V get(GridPos pos) {
		return get(pos.getRow(), pos.getColumn());
	}

	public final void set(GridPos pos, V value) {
		set(pos.getRow(), pos.getColumn(), value);
	}

	public final GridNeighb<V> neighb(int row, int column, Direction direction) {
		GridPos pos = neighbPos(row, column, direction);
		V value = null;
		try {
			value = get(pos);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
		return new GridNeighb<>(pos, value);
	}

	public final GridNeighb<V> neighb(GridPos pos, Direction direction) {
		return neighb(pos.getRow(), pos.getColumn(), direction);
	}

	public final List<GridNeighb<V>> neighbs(int row, int column) {
		List<GridNeighb<V>> list = new ArrayList<>();
		int count = 4;
		if (neighbType == NeighbType.╟каз╬с) {
			count = 8;
		}
		GridNeighb<V> cell;
		for (int i = 0; i < count; i++) {
			cell = neighb(row, column, Direction.values()[i]);
			if (cell != null)
				list.add(cell);
		}
		return list;
	}

	public final List<GridNeighb<V>> neighbs(GridPos pos) {
		return neighbs(pos.getRow(), pos.getColumn());
	}

	public final List<GridNeighb<V>> neighbs(int row, int column, int radius, boolean includeSelf) {
		List<GridNeighb<V>> list = new ArrayList<>();
		int count = 4;
		if (neighbType == NeighbType.╟каз╬с) {
			count = 8;
		}
		GridNeighb<V> cell;
		int r, c;
		for (int i = 0; i < count; i++) {
			r = row;
			c = column;
			for (int j = 1; j <= radius; j++) {
				cell = neighb(r, c, Direction.values()[i]);
				if (cell != null) {
					cell.setDistance(j);
					list.add(cell);
					r = cell.getRow();
					c = cell.getColumn();
				} else
					break;
			}
		}
		if (includeSelf) {
			list.add(new GridNeighb<V>(row, column, this.get(row, column), 0));
		}
		return list;

	}

	public final List<GridNeighb<V>> neighbsRecursive(int row, int column, int radius, boolean includeSelf) {
		List<GridNeighb<V>> list = new ArrayList<>();
		List<GridNeighb<V>> outer = neighbs(row, column);
		list.addAll(outer);
		GridNeighb<V> self = new GridNeighb<>(new GridPos(row, column), get(row, column), 0);
		list.add(self);
		List<GridNeighb<V>> tempTotal;

		for (int i = 2; i <= radius; i++) {
			tempTotal = new ArrayList<>();
			for (GridNeighb<V> n : outer) {
				tempTotal.addAll(neighbs(n.getPos()));
			}
			tempTotal.removeAll(list);
			outer = tempTotal;
			for (GridNeighb<V> cell : outer) {
				cell.setDistance(i);
			}
			list.addAll(outer);
		}
		if (!includeSelf)
			list.remove(self);
		return list;
	}

	public final List<GridNeighb<V>> neighbs(int row, int column, int radius) {
		return neighbs(row, column, radius, false);
	}

	public final List<GridNeighb<V>> neighbs(GridPos pos, int radius) {
		return neighbs(pos.getRow(), pos.getColumn(), radius, false);
	}

	public final List<GridNeighb<V>> neighbs(GridPos pos, int radius, boolean inclSelf) {
		return neighbs(pos.getRow(), pos.getColumn(), radius, true);
	}

	private final GridPos neighbPos(int row, int column, Direction direction) {
		switch (direction) {
		case TOP:
			row -= 1;
			break;
		case RIGHT:
			column += 1;
			break;
		case DOWN:
			row += 1;
			break;
		case LEFT:
			column -= 1;
			break;
		case TOP_LEFT:
			row -= 1;
			column -= 1;
			break;
		case TOP_RIGHT:
			row -= 1;
			column += 1;
			break;
		case DOWN_RIGHT:
			row += 1;
			column += 1;
			break;
		case DOWN_LEFT:
			row += 1;
			column -= 1;
			break;

		}
		return new GridPos(row, column);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name + "[" + rowSize + "]" + "[" + columnSize + "]";
	}

	public static <V> Grid<V> newGridXML(String fileName, Class<V> cls) {
		// xml format
		GridXMLHandler<V> gridXMLHandler = null;
		try {
			XMLReader parser = XMLReaderFactory.createXMLReader();
			gridXMLHandler = new GridXMLHandler<>(cls);
			parser.setContentHandler(gridXMLHandler);
			parser.parse("resources/" + fileName);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		return gridXMLHandler.getGrid();
	}

	public static <V> Grid<V> newGrid(String fileName, Class<V> cls) {
		// raw format
		Scanner stringScanner;
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(new File("resources/" + fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<String> rows = new ArrayList<>();
		List<String> columns;
		String line;
		Grid<V> grid = null;
		while (fileScanner.hasNextLine()) {
			line = fileScanner.nextLine();
			if (line.trim().equals(""))
				break;
			rows.add(line);
		}
		int rowCount = rows.size();
		System.out.println("Row count" + rowCount);
		for (int i = 0; i < rowCount; i++) {
			stringScanner = new Scanner(rows.get(i));
			columns = new ArrayList<>();
			while (stringScanner.hasNext()) {
				columns.add(stringScanner.next());
			}
			if (i == 0) {
				grid = new Grid<>(rowCount, columns.size());
			}
			for (int j = 0; j < columns.size(); j++) {
				grid.set(i, j, Gadgets.valueOf(cls, columns.get(j)));
			}
		}
		return grid;
	}

	public List<GridPos> find(V value) {
		return find(Predicate.isEqual(value));
	}

	public int count(V value) {
		return count(Predicate.isEqual(value));
	}

	public List<GridPos> find(Predicate<V> predicate) {
		List<GridPos> list = new ArrayList<>();
		for (int i = 0; i < getRowSize(); i++) {
			for (int j = 0; j < getColumnSize(); j++) {
				if (predicate.test(get(i, j))) {
					list.add(new GridPos(i, j));
				}
			}
		}
		return list;
	}

	public int count(Predicate<V> predicate) {
		int c = 0;
		for (int i = 0; i < getRowSize(); i++) {
			for (int j = 0; j < getColumnSize(); j++) {
				if (predicate.test(get(i, j))) {
					c++;
				}
			}
		}
		return c;
	}

	public NeighbType getNeighbType() {
		return neighbType;
	}

	public void setNeighbType(NeighbType neighbType) {
		this.neighbType = neighbType;
	}
}
