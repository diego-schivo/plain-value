package it.plainvalue.adt.impl;

import it.plainvalue.adt.Node;
import it.plainvalue.adt.Tree;

public class TreeImpl implements Tree {

	protected Node root = new NodeImpl();

	@Override
	public Node getRoot() {
		return root;
	}

	@Override
	public Node addNode(Node parent) {
		NodeImpl node = new NodeImpl();
		node.parent = parent;
		((NodeImpl) parent).children.add(node);
		return node;
	}
}
