package it.plainvalue.datatypes.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import it.plainvalue.datatypes.Repository.Content;
import it.plainvalue.datatypes.TreeRepository.NodeContent;

public class TreeRepositoryImplTest {

	@Test
	public void test1() {
		TreeRepositoryImpl<NodeContent> repository = TreeRepositoryImpl.newTreeRepository();

		NodeContent foo = repository.newContent();
		foo.setName("foo");
		foo.setValue("Foo");

		repository.putContent(foo, repository.getRoot());
		assertNotNull(foo.getId());

		Content content = repository.getContent(foo.getId());
		assertEquals(foo, content);

		content = repository.getContentByPath("/foo");
		assertEquals(foo, content);
	}
}
