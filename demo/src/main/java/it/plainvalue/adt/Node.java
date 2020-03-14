package it.plainvalue.adt;

public interface Node {

	Node getParent();

	Iterable<Node> getChildren();

	Object getValue();

	void setValue(Object value);
}
