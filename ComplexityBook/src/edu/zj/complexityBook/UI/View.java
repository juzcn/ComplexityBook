package edu.zj.complexityBook.UI;

import javafx.scene.Node;

public abstract class View {
	private String title;
	private Node node;
	
	public abstract void show();
	public abstract void redraw();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}
}
