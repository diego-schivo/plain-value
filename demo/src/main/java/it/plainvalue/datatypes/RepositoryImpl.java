package it.plainvalue.datatypes;

import static it.plainvalue.PlainValue.unsafeGet;

import java.util.HashMap;
import java.util.Map;

import it.plainvalue.datatypes.Impl.ItemImpl;
import it.plainvalue.datatypes.Repository.Content;

class RepositoryImpl<T extends Content> implements Repository<T> {

	Class<? extends T> contentClass;

	Map<Object, T> contents = new HashMap<>();

	protected RepositoryImpl(Class<? extends T> contentClass) {
		this.contentClass = contentClass;
	}

	@Override
	public T getContent(Object id) {
		if (id == null) {
			return null;
		}
		return contents.get(id);
	}

	@Override
	public Object putContent(T content) {
		if (content == null) {
			return null;
		}
		if (content.getId() == null) {
			((ModifiableContent) content).setId(new Object());
		}
		contents.put(content.getId(), content);
		return content.getId();
	}

	@Override
	public boolean removeContent(Object id) {
		if (id == null) {
			return false;
		}
		return contents.remove(id) != null;
	}

	@Override
	public T newContent() {
		return unsafeGet(() -> contentClass.newInstance());
	}

	interface ModifiableContent extends Content {

		void setId(Object id);
	}

	static class ContentImpl extends ItemImpl implements ModifiableContent {

		Object id;

		@Override
		public Object getId() {
			return id;
		}

		@Override
		public void setId(Object id) {
			this.id = id;
		}
	}
}
