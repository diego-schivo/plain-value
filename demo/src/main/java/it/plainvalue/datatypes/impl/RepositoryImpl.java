package it.plainvalue.datatypes.impl;

import static it.plainvalue.PlainValue.unsafeGet;

import java.util.HashMap;
import java.util.Map;

import it.plainvalue.datatypes.Repository;
import it.plainvalue.datatypes.Repository.Content;

public class RepositoryImpl<T extends Content> implements Repository<T> {

	public static RepositoryImpl<Content> newInstance() {
		return new RepositoryImpl<Content>(ContentImpl.class);
	}

	Class<? extends T> contentClass;

	Map<Object, T> map = new HashMap<>();

	protected RepositoryImpl(Class<? extends T> contentClass) {
		this.contentClass = contentClass;
	}

	@Override
	public T getContent(Object id) {
		if (id == null) {
			return null;
		}
		return map.get(id);
	}

	@Override
	public Object putContent(T content) {
		if (content == null) {
			return null;
		}
		if (content.getId() == null) {
			content.setId(new Object());
		}
		map.put(content.getId(), content);
		return content.getId();
	}

	@Override
	public T newContent() {
		return unsafeGet(() -> contentClass.newInstance());
	}

	static class ContentImpl extends ItemImpl implements Content {

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
