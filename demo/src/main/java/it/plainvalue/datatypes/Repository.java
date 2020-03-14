package it.plainvalue.datatypes;

import it.plainvalue.datatypes.Repository.Content;
import it.plainvalue.datatypes.RepositoryImpl.ContentImpl;

public interface Repository<T extends Content> {

	T getContent(Object id);

	Object putContent(T content);

	T newContent();

	interface Content extends Item {

		Object getId();
	}
}
