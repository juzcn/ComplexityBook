package edu.zj.utils.Grid.Graph;

import java.util.HashSet;
import java.util.Set;

public class Vertex<V extends Vertex<V, E>, E extends Edge<V, E>> {
	private Set<E> fromSet;
	private Set<E> toSet;
	private Set<E> set;

	private void addEdge(Set<E> set, E edge) {
		if (set == null)
			set = new HashSet<>();
		if (set.contains(edge)) {
			throw new EdgeExistantException(edge.toString());
		}
		set.add(edge);

	}

	public void addEdge(E edge) {
		addEdge(set, edge);
	}

	public void addFromEdge(E edge) {
		addEdge(fromSet, edge);
	}

	public void addToEdge(E edge) {
		addEdge(toSet, edge);
	}
}
