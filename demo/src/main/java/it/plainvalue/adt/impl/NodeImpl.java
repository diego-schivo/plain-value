package it.plainvalue.adt.impl;

import java.util.ArrayList;
import java.util.List;

import it.plainvalue.adt.Node;

public class NodeImpl implements Node {

	protected Node parent;

	protected List<Node> children = new ArrayList<>();

	protected Object value;

	@Override
	public Node getParent() {
		return parent;
	}

	@Override
	public Iterable<Node> getChildren() {
		return children;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(Object value) {
		this.value = value;
	}
}
