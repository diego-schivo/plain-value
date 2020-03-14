package it.plainvalue.datatypes.impl;

import static it.plainvalue.PlainValue.unsafeGet;

import java.util.ArrayList;
import java.util.List;

import it.plainvalue.datatypes.Tree;
import it.plainvalue.datatypes.Tree.Node;

public class TreeImpl<T extends Node> implements Tree<T> {

	public static TreeImpl<Node> newInstance() {
		return new TreeImpl<Node>(NodeImpl.class);
	}

	Class<? extends T> nodeClass;

	T root;

	protected TreeImpl(Class<? extends T> nodeClass) {
		this.nodeClass = nodeClass;
		root = unsafeGet(() -> nodeClass.newInstance());
	}

	@Override
	public T getRoot() {
		return root;
	}

	@Override
	public T addNode(T parent) {
		T node = unsafeGet(() -> nodeClass.newInstance());
		node.setParent(parent);
		parent.addChild(node);
		return node;
	}

	static class NodeImpl extends ItemImpl implements Node {

		Node parent;

		List<Node> children = new ArrayList<>();

		@Override
		public Node getParent() {
			return parent;
		}

		@Override
		public void setParent(Node parent) {
			this.parent = parent;
		}

		@Override
		public Iterable<Node> getChildren() {
			return children;
		}

		@Override
		public void addChild(Node child) {
			if (child == null) {
				return;
			}
			children.add(child);
		}
	}
}
