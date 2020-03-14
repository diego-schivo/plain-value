package it.plainvalue.datatypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.plainvalue.datatypes.Repository.Content;
import it.plainvalue.datatypes.RepositoryImpl.ContentImpl;

public class RepositoryImplTest {

	RepositoryImpl<Content> repository;

	@BeforeEach
	public void initRepository() {
		repository = new RepositoryImpl<Content>(ContentImpl.class);
	}

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
		Object id1 = repository.putContent(null);
		assertNull(id1);

		Content content = repository.newContent();
		Object id2 = repository.putContent(content);
		assertNotNull(id2);
		assertEquals(id2, content.getId());

		Object id3 = repository.putContent(content);
		assertEquals(id2, id3);
	}
}
