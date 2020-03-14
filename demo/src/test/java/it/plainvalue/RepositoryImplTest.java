package it.plainvalue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RepositoryImplTest {

	Repository repository = new RepositoryImpl();

	@Test
	public void test() {
		Object item = new Object() {
			@Override
			public String toString() {
				return "/foo";
			}
		};
		Object id = repository.addItem(item);
		assertEquals("/foo", id);

		Object item2 = repository.getItem("/foo");
		assertEquals(item, item2);
	}
}
