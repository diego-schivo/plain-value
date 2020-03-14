package it.plainvalue.datatypes;

import it.plainvalue.datatypes.Tree.Node;
import it.plainvalue.datatypes.TreeRepository.NodeContent;
import it.plainvalue.datatypes.TreeRepositoryImpl.NodeContentImpl;

public interface TreeRepository<T extends NodeContent> extends Repository<T> {

	T getRoot();

	T getContentByPath(String path);

	Object putContent(T content, T parent);

	T newContent(String name);

	interface NodeContent extends Content, Node {

		String getName();
	}

	static TreeRepository<NodeContent> newTreeRepository() {
		return new TreeRepositoryImpl<NodeContent>(NodeContentImpl.class);
	}
}
