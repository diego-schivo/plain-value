package it.plainvalue.datatypes.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import it.plainvalue.datatypes.TreeRepository.NodeContent;

public class TreeRepositoryImplTest {

	TreeRepositoryImpl<NodeContent> repository = TreeRepositoryImpl.newTreeRepository();

	@AfterEach
	public void clearContents() {
		repository.contents.clear();
		repository.tree.clear();
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

		NodeContent content2 = repository.newContent();
		content2.setName("foo");
		repository.putContent(content2);

		NodeContent content3 = repository.getContentByPath("/foo");
		assertNotNull(content3);
		assertEquals(content2, content3);
	}

	@Test
	public void testPutContent() {
		Object id1 = repository.putContent(null, null);
		assertNull(id1);

		NodeContent content2 = repository.newContent();
		content2.setName("foo");
		Object id2 = repository.putContent(content2, null);
		assertNotNull(id2);
		assertEquals(id2, content2.getId());

		NodeContent content3 = repository.newContent();
		content3.setName("bar");
		Object id3 = repository.putContent(content3, content2);
		assertNotNull(id3);
		assertEquals(id3, content3.getId());
	}
}
