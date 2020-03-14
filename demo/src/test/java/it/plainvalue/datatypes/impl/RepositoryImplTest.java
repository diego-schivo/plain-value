package it.plainvalue.datatypes.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import it.plainvalue.datatypes.Repository.Content;
import it.plainvalue.datatypes.impl.RepositoryImpl.ContentImpl;

public class RepositoryImplTest {

	RepositoryImpl<Content> repository = new RepositoryImpl<Content>(ContentImpl.class);

	@AfterEach
	public void clearContents() {
		repository.contents.clear();
	}

	@Test
	public void testNewContent() {
		Content content = repository.newContent();
		assertNotNull(content);
		assertNull(content.getId());
	}

	@Test
	public void testGetContent() {
		Content content = repository.getContent(null);
		assertNull(content);

		Object id = new Object();
		ContentImpl content2 = new ContentImpl();
		repository.contents.put(id, content2);

		content = repository.getContent(id);
		assertEquals(content2, content);
	}

	@Test
	public void testPutContent() {
		Object id = repository.putContent(null);
		assertNull(id);

		Content content = repository.newContent();
		id = repository.putContent(content);
		assertNotNull(id);
		assertEquals(id, content.getId());
	}
}
