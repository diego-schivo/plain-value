package it.plainvalue.datatypes;

import it.plainvalue.datatypes.Repository.Content;
import it.plainvalue.datatypes.Tree.Node;
import it.plainvalue.datatypes.TreeRepository.NodeContent;

public interface DataTypes {

	Impl impl = new Impl();

	static Tree<Node> newTree() {
		return impl.newTree();
	}

	static Repository<Content> newRepository() {
		return impl.newRepository();
	}

	static TreeRepository<NodeContent> newTreeRepository() {
		return impl.newTreeRepository();
	}
}
