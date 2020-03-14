package it.plainvalue;

import static it.plainvalue.PlainValue.array;
import static it.plainvalue.PlainValue.convert;
import static it.plainvalue.PlainValue.find;
import static it.plainvalue.PlainValue.iterable;
import static it.plainvalue.PlainValue.split;
import static it.plainvalue.PlainValue.stream;
import static it.plainvalue.PlainValue.substringAfterLast;
import static it.plainvalue.PlainValue.substringBeforeLast;
import static it.plainvalue.PlainValue.unsafeGet;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class PlainValueTest {

	@Test
	public void testsSubstringBeforeLast() {
		assertNull(substringBeforeLast(null, '/'));
		assertEquals(null, substringBeforeLast("foo", '/'));
		assertEquals("/foo/bar", substringBeforeLast("/foo/bar/baz", '/'));
	}

	@Test
	public void testsSubstringAfterLast() {
		assertNull(substringAfterLast(null, '/'));
		assertEquals(null, substringAfterLast("foo", '/'));
		assertEquals("baz", substringAfterLast("/foo/bar/baz", '/'));
	}

	@Test
	public void testSplit() {
		Iterable<String> strings = split(null, '/');
		assertIterableEquals(Collections.emptySet(), strings);

		strings = split("", '/');
		assertIterableEquals(Arrays.asList(""), strings);

		strings = split("foo", '/');
		assertIterableEquals(Arrays.asList("foo"), strings);

		strings = split("/foo/bar/baz", '/');
		assertIterableEquals(Arrays.asList("", "foo", "bar", "baz"), strings);

		assertThrows(NoSuchElementException.class, () -> {
			Iterator<String> iterator = split("hello world", ' ').iterator();
			iterator.next();
			iterator.next();
			iterator.next();
		});
	}

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
	public void testStream() {
		assertIterableEquals(Collections.emptySet(), convert(stream(null), Iterable.class));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testConvert() {
		Iterator<Object> objects1 = convert((Object[]) null, Iterator.class);
		assertThrows(NoSuchElementException.class, () -> {
			objects1.next();
		});

		Iterable<Object> objects2 = convert((Object[]) null, Iterable.class);
		assertIterableEquals(Collections.emptySet(), objects2);

		Iterable<Object> objects3 = convert(new Object[] { null }, Iterable.class);
		assertIterableEquals(Collections.singleton(null), objects3);

		Iterable<String> strings = convert(array("foo", "bar", "baz"), Iterable.class);
		assertIterableEquals(Arrays.asList("foo", "bar", "baz"), strings);
	}

	@Test
	public void testUnsafeGet() {
		assertNull(unsafeGet(() -> null));
		assertEquals("foo", unsafeGet(() -> "foo"));
		assertThrows(RuntimeException.class, () -> unsafeGet(() -> "foo".substring(4)));
	}

	@Test
	public void testIterable() {
		assertThrows(NullPointerException.class, () -> iterable(null, null));
		assertIterableEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), iterable(1, n -> (n <= 9) ? (n + 1) : null));
		assertThrows(NoSuchElementException.class, () -> {
			Iterator<String> iterator = iterable("foo", str -> null).iterator();
			iterator.next();
			iterator.next();
		});
	}

	@Test
	public void testFind() {
		assertThrows(NullPointerException.class, () -> find(null, null, null));
		assertNull(find("foo", null, (obj1, obj2) -> null));
		assertNull(find("foo", Arrays.asList(0, 1, 2), (str1, index) -> null));
		assertEquals("foo", find("foo", Collections.emptySet(), (str, index) -> str + index));
		assertEquals("foo012", find("foo", Arrays.asList(0, 1, 2), (str, index) -> str + index));
	}
}
