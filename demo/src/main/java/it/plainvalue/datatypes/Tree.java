package it.plainvalue.datatypes;

import it.plainvalue.datatypes.Tree.Node;

public interface Tree<T extends Node> {

	T getRoot();

	T newNode();

	void putNode(T node, T parent);

	void clear();

	interface Node extends Item {

		Node getParent();

		void setParent(Node parent);

		Iterable<Node> getChildren();

		void addChild(Node child);
	}
}
