package it.plainvalue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
		assertNotNull(id);

		Object item2 = repository.getItem(id);
		assertEquals(item, item2);
	}
}
