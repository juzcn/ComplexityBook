package edu.zj.utils.Grid.Model;

public class GridNeighb<V> extends GridCell<V>{
	private int distance;
	public GridNeighb(int row,int column,V value, int distance) {
		super(row,column,value);
		this.distance=distance;
	}
	public GridNeighb(GridPos pos, V value, int distance) {
		this(pos.getRow(),pos.getColumn(),value,distance);
	}
	public GridNeighb(int row,int column,V value) {
		this(row,column,value,1);
	}
	public GridNeighb(GridPos pos, V value) {
		this(pos.getRow(),pos.getColumn(),value,1);
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
}
