package edu.zj.utils.Grid.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph<V extends Vertex<V, E>, E extends Edge<V, E>> {
	public enum Type {
		Directed, Undirected
	};

	private Type type;
	private Set<V> vertexSet = new HashSet<>();

	public Graph() {
		this(Type.Undirected);
	}

	public Graph(Type type) {
		this.type = type;
	}

	public void addVertex(V vertex) {
		vertexSet.add(vertex);
	}

	public void addEdge(E edge, V from, V to) {
		if (!vertexSet.contains(from)) {
			throw new VertexNotExistantException(from.toString());
		}
		if (!vertexSet.contains(to)) {
			throw new VertexNotExistantException(from.toString());
		}
		edge.setFrom(from);
		edge.setTo(to);
		if (type == Type.Directed) {
			from.addFromEdge(edge);
			to.addToEdge(edge);
		} else {
			from.addEdge(edge);
			to.addEdge(edge);
		}
	}

	public boolean isDirected() {
		return (type == Type.Directed);
	}

	public boolean isUndirected() {
		return (type == Type.Undirected);
	}

	public List<V> getVertexList() {
		return new ArrayList<>(vertexSet);
	}
	public static void main(String[] args) {
		Graph g = new Graph();
		Vertex v1 = new Vertex();
		Vertex v2 = new Vertex();
		g.addVertex(v1);
		g.addVertex(v2);
		g.addEdge(new Edge(), v1, v2);
		for (Object v:g.getVertexList()) {
			System.out.println(v);
		}

	}
	
}
