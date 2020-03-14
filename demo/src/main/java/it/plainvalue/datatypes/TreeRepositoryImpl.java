package it.plainvalue.datatypes;

import static it.plainvalue.PlainValue.find;
import static it.plainvalue.PlainValue.split;
import static it.plainvalue.PlainValue.stream;

import java.util.Objects;

import it.plainvalue.datatypes.Tree.Node;
import it.plainvalue.datatypes.TreeImpl.ModifiableNode;
import it.plainvalue.datatypes.TreeImpl.NodeImpl;
import it.plainvalue.datatypes.TreeRepository.NodeContent;

public class TreeRepositoryImpl<T extends NodeContent> extends RepositoryImpl<T> implements TreeRepository<T> {

	Tree<T> tree;

	public TreeRepositoryImpl(Class<? extends T> contentClass) {
		super(contentClass);
		tree = new TreeImpl<T>(contentClass);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getContentByPath(String path) {
		if (path == null || !path.startsWith("/")) {
			return null;
		}
		return (T) find((Node) tree.getRoot(), split(path.substring(1), '/'),
				(node, name) -> stream(node.getChildren()).filter(child -> {
					return Objects.equals(((T) child).getName(), name);
				}).findFirst().orElse(null));
	}

	@Override
	public Object putContent(T content, T parent) {
		Object id = super.putContent(content);
		if (id == null) {
			return null;
		}
		tree.putNode(content, parent);
		return id;
	}

	@Override
	public T newContent(String name) {
		T content = super.newContent();
		((ModifiableNodeContent) content).setName(name);
		return content;
	}

	@Override
	public Object putContent(T content) {
		return putContent(content, null);
	}

	interface ModifiableNodeContent extends NodeContent, ModifiableNode {

		void setName(String name);
	}

	static class NodeContentImpl extends ContentImpl implements ModifiableNodeContent {

		NodeImpl node = new NodeImpl();

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
		public void removeChild(Node child) {
			node.removeChild(child);
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
