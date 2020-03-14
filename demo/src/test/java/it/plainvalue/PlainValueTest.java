package it.plainvalue;

import static it.plainvalue.PlainValue.array;
import static it.plainvalue.PlainValue.convert;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class PlainValueTest {

	@Test
	public void testArray() {
		Object[] objects = array((Object[]) null);
		assertNull(objects);

		objects = array((Object) null);
		assertNotNull(objects);
		assertEquals(1, objects.length);
		assertNull(objects[0]);

		String[] strings = array("foo", "bar");
		assertNotNull(strings);
		assertEquals(2, strings.length);
		assertEquals("foo", strings[0]);
		assertEquals("bar", strings[1]);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testConvert() {
		Iterator<Object> objects = convert((Object[]) null, Iterator.class);
		assertNull(objects);

		Iterator<String> strings = convert(array("foo", "bar"), Iterator.class);
		assertNotNull(strings);
		assertTrue(strings.hasNext());
		assertEquals("foo", strings.next());
		assertTrue(strings.hasNext());
		assertEquals("bar", strings.next());
		assertFalse(strings.hasNext());
	}
}
