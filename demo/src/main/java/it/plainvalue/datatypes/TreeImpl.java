package it.plainvalue.datatypes;

import static it.plainvalue.PlainValue.unsafeGet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import it.plainvalue.datatypes.Impl.ItemImpl;
import it.plainvalue.datatypes.Tree.Node;

class TreeImpl<T extends Node> implements Tree<T> {

	Class<? extends T> nodeClass;

	T root;

	TreeImpl(Class<? extends T> nodeClass) {
		this.nodeClass = nodeClass;
		root = unsafeGet(() -> nodeClass.newInstance());
	}

	@Override
	public T getRoot() {
		return root;
	}

	@Override
	public T newNode() {
		return unsafeGet(() -> nodeClass.newInstance());
	}

	@Override
	public void putNode(T node, T parent) {
		if (node == null) {
			return;
		}
		if (parent == null) {
			parent = getRoot();
		}

		((ModifiableNode) node).setParent(parent);
		((ModifiableNode) parent).addChild(node);
	}

	@Override
	public void removeNode(T node) {
		if (node == null || node.getParent() == null) {
			return;
		}
		((ModifiableNode) node.getParent()).removeChild(node);
		((ModifiableNode) node).setParent(null);
	}

	interface ModifiableNode extends Node {

		void setParent(Node parent);

		void addChild(Node child);

		void removeChild(Node child);
	}

	static class NodeImpl extends ItemImpl implements ModifiableNode {

		Node parent;

		List<Node> children = new ArrayList<>();

		@Override
		public Node getParent() {
			return parent;
		}

		@Override
		public void setParent(Node parent) {
			if (Objects.equals(parent, this.parent)) {
				return;
			}
			if (this.parent != null) {
				((ModifiableNode) this.parent).removeChild(this);
			}
			this.parent = parent;
		}

		@Override
		public Iterable<Node> getChildren() {
			return children;
		}

		@Override
		public void addChild(Node child) {
			if (child == null || children.contains(child)) {
				return;
			}
			children.add(child);
		}

		@Override
		public void removeChild(Node child) {
			if (child == null) {
				return;
			}
			children.remove(child);
		}
	}
}
