package it.plainvalue.datatypes.impl;

import static it.plainvalue.PlainValue.find;
import static it.plainvalue.PlainValue.split;
import static it.plainvalue.PlainValue.stream;

import java.util.Objects;

import it.plainvalue.datatypes.Tree;
import it.plainvalue.datatypes.Tree.Node;
import it.plainvalue.datatypes.TreeRepository;
import it.plainvalue.datatypes.TreeRepository.NodeContent;
import it.plainvalue.datatypes.impl.TreeImpl.NodeImpl;

public class TreeRepositoryImpl<T extends NodeContent> extends RepositoryImpl<T> implements TreeRepository<T> {

	public static TreeRepositoryImpl<NodeContent> newTreeRepository() {
		return new TreeRepositoryImpl<NodeContent>(NodeContentImpl.class);
	}

	Tree<T> tree;

	public TreeRepositoryImpl(Class<? extends T> contentClass) {
		super(contentClass);
		tree = new TreeImpl<T>(contentClass);
	}

	@Override
	public T getRoot() {
		return tree.getRoot();
	}

//	@Override
//	public Object putContent(T content) {
//		Object id = super.putContent(content);
//		List<String> list = stream(iterable(content, c -> (T) c.getParent())).map(c -> c.getName()).collect(Collectors.toList());
//		Collections.reverse(list);
//		String path = "/" + list.stream().collect(Collectors.joining("/"));
//		return id;
//	}

	@Override
	@SuppressWarnings("unchecked")
	public T getContentByPath(String path) {
		if (path == null || !path.startsWith("/")) {
			return null;
		}
		return find(tree.getRoot(), split(path.substring(1), '/'),
				(node, name) -> stream((Iterable<T>) node.getChildren()).filter(child -> {
					return Objects.equals(child.getName(), name);
				}).findFirst().orElse(null));
	}

	@Override
	public void putContent(T content, T parent) {
		super.putContent(content);
		tree.putNode(content, parent);
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
