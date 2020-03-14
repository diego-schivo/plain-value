package it.plainvalue;

import static it.plainvalue.PlainValue.array;
import static it.plainvalue.PlainValue.convert;
import static it.plainvalue.PlainValue.split;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class PlainValueTest {

	@Test
	public void testArray() {
		Object[] objects = array((Object[]) null);
		assertNull(objects);

		objects = array((Object) null);
		assertArrayEquals(new Object[] { null }, objects);

		String[] strings = array("foo", "bar");
		assertArrayEquals(new String[] { "foo", "bar" }, strings);
	}

	@Test
	public void testSplit() {
		Iterable<String> strings = split(null, '/');
		assertNull(strings);

		strings = split("", '/');
		assertIterableEquals(Arrays.asList(""), strings);

		strings = split("foo", '/');
		assertIterableEquals(Arrays.asList("foo"), strings);

		strings = split("/foo/bar/baz", '/');
		assertIterableEquals(Arrays.asList("", "foo", "bar", "baz"), strings);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testConvert() {
		Iterable<Object> objects = convert((Object[]) null, Iterable.class);
		assertNull(objects);

		Iterable<String> strings = convert(array("foo", "bar", "baz"), Iterable.class);
		assertIterableEquals(Arrays.asList("foo", "bar", "baz"), strings);
	}
}
