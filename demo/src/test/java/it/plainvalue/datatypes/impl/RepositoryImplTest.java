package it.plainvalue.datatypes.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import it.plainvalue.datatypes.Repository.Content;

public class RepositoryImplTest {

	@Test
	public void test1() {
		RepositoryImpl<Content> repository = RepositoryImpl.newRepository();

		Content foo = repository.newContent();
		foo.setValue("foo");

		repository.putContent(foo);
		assertNotNull(foo.getId());

		Content content = repository.getContent(foo.getId());
		assertEquals(foo, content);
	}
}
