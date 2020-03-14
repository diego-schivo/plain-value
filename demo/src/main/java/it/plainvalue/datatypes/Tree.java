package it.plainvalue.datatypes;

import it.plainvalue.datatypes.Tree.Node;
import it.plainvalue.datatypes.TreeImpl.NodeImpl;

public interface Tree<T extends Node> {

	T getRoot();

	T newNode();

	void putNode(T node, T parent);

	void clear();

	interface Node extends Item {

		Node getParent();

		Iterable<Node> getChildren();
	}
}
