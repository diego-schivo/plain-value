package it.plainvalue.datatypes.impl;

import static it.plainvalue.PlainValue.split;
import static it.plainvalue.PlainValue.stream;

import java.util.Objects;

import it.plainvalue.datatypes.Tree;
import it.plainvalue.datatypes.Tree.Node;
import it.plainvalue.datatypes.TreeRepository;
import it.plainvalue.datatypes.TreeRepository.NodeContent;
import it.plainvalue.datatypes.impl.TreeImpl.NodeImpl;

public class TreeRepositoryImpl<T extends NodeContent> extends RepositoryImpl<T> implements TreeRepository<T> {

	Tree<T> tree;

	public TreeRepositoryImpl(Class<? extends T> contentClass) {
		super(contentClass);
		tree = new TreeImpl<T>(contentClass);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getContentByPath(String path) {
		if (path == null) {
			return null;
		}
		T node = null;
		for (String name : split(path, '/')) {
			if (node == null) {
				node = Objects.equals(name, "") ? tree.getRoot() : null;
			} else {
				node = stream((Iterable<T>) node.getChildren()).filter(child -> {
					return Objects.equals(child.getName(), name);
				}).findFirst().orElse(null);
			}

			if (node == null) {
				break;
			}
		}
		return node;
	}

	static class NodeContentImpl extends ContentImpl implements NodeContent {

		Node node = new NodeImpl();
		String name;

		@Override
		public Node getParent() {
			return node.getParent();
		}

		@Override
		public void setParent(Node parent) {
			node.setParent(parent);
		}

		@Override
		public Iterable<Node> getChildren() {
			return node.getChildren();
		}

		@Override
		public void addChild(Node child) {
			node.addChild(child);
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public void setName(String name) {
			this.name = name;
		}
	}
}
