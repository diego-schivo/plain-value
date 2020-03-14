package it.plainvalue.datatypes;

import it.plainvalue.datatypes.Tree.Node;
import it.plainvalue.datatypes.TreeRepository.NodeContent;

public interface TreeRepository<T extends NodeContent> extends Repository<T> {

	T getContentByPath(String path);

	Object putContent(T content, T parent);

	T newContent(String name);

	interface NodeContent extends Content, Node {

		String getName();
	}
}
