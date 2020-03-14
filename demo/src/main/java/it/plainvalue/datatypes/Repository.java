package it.plainvalue.datatypes;

import it.plainvalue.datatypes.Repository.Content;

public interface Repository<T extends Content> {

	T getContent(Object id);

	Object putContent(T content);

	boolean removeContent(Object id);

	T newContent();

	interface Content extends Item {

		Object getId();
	}
}
