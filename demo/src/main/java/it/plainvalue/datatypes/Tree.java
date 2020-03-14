package it.plainvalue.datatypes;

import it.plainvalue.datatypes.Tree.Node;

public interface Tree<T extends Node> {

	T getRoot();

	void putNode(T node, T parent);

	T newNode();

	interface Node extends Item {

		Node getParent();

		void setParent(Node parent);

		Iterable<Node> getChildren();

		void addChild(Node child);
	}
}
