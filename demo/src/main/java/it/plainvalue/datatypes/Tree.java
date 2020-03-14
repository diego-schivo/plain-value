package it.plainvalue.datatypes;

import it.plainvalue.datatypes.Tree.Node;

public interface Tree<T extends Node> {

	T getRoot();

	T newNode();

	void putNode(T node, T parent);

	void removeNode(T node);

	interface Node extends Item {

		Node getParent();

		Iterable<Node> getChildren();
	}
}
