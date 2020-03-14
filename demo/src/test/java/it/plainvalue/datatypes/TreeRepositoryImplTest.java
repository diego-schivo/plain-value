package it.plainvalue.datatypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.plainvalue.datatypes.TreeRepository.NodeContent;
import it.plainvalue.datatypes.TreeRepositoryImpl.NodeContentImpl;

public class TreeRepositoryImplTest {

	TreeRepositoryImpl<NodeContent> repository;

	@BeforeEach
	public void initRepository() {
		repository = new TreeRepositoryImpl<NodeContent>(NodeContentImpl.class);
	}

	@Test
	public void testNewContent() {
		NodeContent content = repository.newContent();
		assertNotNull(content);
		assertNull(content.getId());
		assertNull(content.getName());
	}

	@Test
	public void testGetContentByPath() {
		NodeContent content1 = repository.getContentByPath(null);
		assertNull(content1);

		NodeContent content2 = repository.newContent("foo");
		repository.putContent(content2);

		assertNull(repository.getContentByPath("foo"));

		NodeContent content3 = repository.getContentByPath("/foo");
		assertNotNull(content3);
		assertEquals(content2, content3);
	}

	@Test
	public void testPutContent() {
		Object id1 = repository.putContent(null, null);
		assertNull(id1);

		NodeContent content2 = repository.newContent("foo");
		Object id2 = repository.putContent(content2, null);
		assertNotNull(id2);
		assertEquals(id2, content2.getId());

		NodeContent content3 = repository.newContent("bar");
		Object id3 = repository.putContent(content3, content2);
		assertNotNull(id3);
		assertEquals(id3, content3.getId());
	}
}
