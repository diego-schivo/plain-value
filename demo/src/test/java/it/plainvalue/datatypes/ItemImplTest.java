package it.plainvalue.datatypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import it.plainvalue.datatypes.Impl.ItemImpl;

public class ItemImplTest {

	ItemImpl item = new ItemImpl();

	@Test
	public void testGetValue() {
		assertNull(item.getValue());
	}

	@Test
	public void testSetValue() {
		Object value = new Object();
		item.setValue(value);
		assertEquals(value, item.getValue());
	}
}
