package it.plainvalue.datatypes;

import it.plainvalue.datatypes.Repository.Content;
import it.plainvalue.datatypes.RepositoryImpl.ContentImpl;
import it.plainvalue.datatypes.Tree.Node;
import it.plainvalue.datatypes.TreeImpl.NodeImpl;
import it.plainvalue.datatypes.TreeRepository.NodeContent;
import it.plainvalue.datatypes.TreeRepositoryImpl.NodeContentImpl;

public interface DataTypes {

	static Tree<Node> newTree() {
		return new TreeImpl<Node>(NodeImpl.class);
	}

	static Repository<Content> newRepository() {
		return new RepositoryImpl<Content>(ContentImpl.class);
	}

	static TreeRepository<NodeContent> newTreeRepository() {
		return new TreeRepositoryImpl<NodeContent>(NodeContentImpl.class);
	}
}
