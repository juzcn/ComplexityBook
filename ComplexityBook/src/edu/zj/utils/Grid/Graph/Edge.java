package edu.zj.utils.Grid.Graph;

public class Edge<V extends Vertex<V,E>,E extends Edge<V,E>> {
	private V from;
	private V to;

	public V getFrom() {
		return from;
	}

	public V getTo() {
		return to;
	}

	public void setTo(V to) {
		this.to = to;
	}

	public void setFrom(V from) {
		this.from = from;
	}
}
