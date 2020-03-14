package it.plainvalue;

import static it.plainvalue.PlainValue.split;
import static it.plainvalue.PlainValue.stream;
import static it.plainvalue.PlainValue.substringAfterLast;
import static it.plainvalue.PlainValue.substringBeforeLast;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import it.plainvalue.adt.Node;
import it.plainvalue.adt.Tree;
import it.plainvalue.adt.impl.TreeImpl;

public class RepositoryImpl implements Repository {

	protected Tree tree = new TreeImpl();
	protected Map<Object, String> paths = new HashMap<>();

	@Override
	public Object getItem(Object id) {
		if (id == null) {
			return null;
		}
		String path = paths.get(id);
		Node node = getNode(path);
		if (node == null) {
			return null;
		}
		return node.getValue();
	}

	@Override
	public Object addItem(Object item) {
		if (item == null) {
			return null;
		}
		String path = getPath(item);
		Node parent = getNode(substringBeforeLast(path, '/'));
		Node node = tree.addNode(parent);
		node.setValue(item);

		Object id = new Object();
		paths.put(id, path);

		return id;
	}

	private String getPath(Object item) {
		return item.toString();
	}

	protected Node getNode(String path) {
		if (path == null) {
			return null;
		}
		Node node = null;
		for (String name : split(path, '/')) {
			if (node == null) {
				node = Objects.equals(name, "") ? tree.getRoot() : null;
			} else {
				node = stream(node.getChildren()).filter(n -> {
					return Objects.equals(substringAfterLast(n.getValue().toString(), '/'), name);
				}).findFirst().orElse(null);
			}

			if (node == null) {
				break;
			}
		}
		return node;
	}
}
