package it.plainvalue.datatypes;

import it.plainvalue.datatypes.Repository.Content;
import it.plainvalue.datatypes.RepositoryImpl.ContentImpl;
import it.plainvalue.datatypes.Tree.Node;
import it.plainvalue.datatypes.TreeImpl.NodeImpl;
import it.plainvalue.datatypes.TreeRepository.NodeContent;
import it.plainvalue.datatypes.TreeRepositoryImpl.NodeContentImpl;

class Impl {

	Tree<Node> newTree() {
		return new TreeImpl<Node>(NodeImpl.class);
	}

	Repository<Content> newRepository() {
		return new RepositoryImpl<Content>(ContentImpl.class);
	}

	TreeRepository<NodeContent> newTreeRepository() {
		return new TreeRepositoryImpl<NodeContent>(NodeContentImpl.class);
	}

	interface ModifiableItem extends Item {

		void setValue(Object value);
	}

	static class ItemImpl implements ModifiableItem {

		Object value;

		@Override
		public Object getValue() {
			return value;
		}

		@Override
		public void setValue(Object value) {
			this.value = value;
		}
	}
}
