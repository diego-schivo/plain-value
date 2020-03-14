package it.plainvalue.datatypes;

import it.plainvalue.datatypes.Tree.Node;
import it.plainvalue.datatypes.TreeRepository.NodeContent;

public interface TreeRepository<T extends NodeContent> extends Repository<T> {

	T getContentByPath(String path);

	interface NodeContent extends Content, Node {

		String getName();

		void setName(String name);
	}
}
