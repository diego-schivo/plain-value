package it.plainvalue.datatypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.plainvalue.datatypes.TreeRepository.NodeContent;
import it.plainvalue.datatypes.TreeRepositoryImpl.NodeContentImpl;

public class TreeRepositoryImplTest {

	TreeRepositoryImpl<NodeContent> repository;

	@BeforeEach
	public void initRepository() {
		repository = new TreeRepositoryImpl<NodeContent>(NodeContentImpl.class);
		NodeContentImpl content = new NodeContentImpl();
		content.setName("foo");
		repository.contents.put(new Object(), content);
		repository.tree.putNode(content, null);
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

		assertNull(repository.getContentByPath("foo"));

		NodeContent content2 = repository.getContentByPath("/foo");
		assertNotNull(content2);
		assertEquals("foo", content2.getName());
	}

	@Test
	public void testPutContent() {
		Object id1 = repository.putContent(null, null);
		assertNull(id1);

		NodeContent content2 = repository.newContent("bar");
		Object id2 = repository.putContent(content2, null);
		assertNotNull(id2);
		assertEquals(id2, content2.getId());

		NodeContent content3 = repository.newContent("baz");
		Object id3 = repository.putContent(content3, content2);
		assertNotNull(id3);
		assertEquals(id3, content3.getId());
	}

	@Test
	public void testRemoveContent() {
		assertFalse(repository.removeContent(null));
		assertFalse(repository.removeContent(new Object()));
		assertEquals(1, repository.contents.size());
		assertNotEquals(Collections.emptySet(), repository.tree.root.getChildren());

		assertTrue(repository.removeContent(repository.contents.keySet().iterator().next()));
		assertEquals(0, repository.contents.size());
		assertIterableEquals(Collections.emptySet(), repository.tree.root.getChildren());
	}
}
