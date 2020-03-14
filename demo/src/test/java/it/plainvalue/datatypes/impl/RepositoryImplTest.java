package it.plainvalue.datatypes.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import it.plainvalue.datatypes.Repository.Content;

public class RepositoryImplTest {

	@Test
	public void test1() {
		RepositoryImpl<Content> repository = RepositoryImpl.newInstance();

		Content foo = repository.newContent();
		foo.setValue("foo");

		Object fooId = repository.putContent(foo);
		assertNotNull(fooId);

		Content content = repository.getContent(fooId);
		assertEquals(foo, content);
	}
}
