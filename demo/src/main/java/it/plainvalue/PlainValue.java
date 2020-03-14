package it.plainvalue;

import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class PlainValue {

	protected static Impl impl = new Impl();

	public static String substringBeforeLast(String str, int ch) {
		return impl.substringBeforeLast(str, ch);
	}

	public static String substringAfterLast(String str, int ch) {
		return impl.substringAfterLast(str, ch);
	}

	@SuppressWarnings("unchecked")
	public static Iterable<String> split(String str, int ch) {
		return impl.from(impl.split(str, ch)).to(Iterable.class);
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] array(T... elements) {
		return elements;
	}

	public static <T> Stream<T> stream(Iterable<T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false);
	}

	public static <T, U> U convert(T[] array, Class<U> class1) {
		return impl.from(array).to(class1);
	}

	public static <T, U> U convert(Supplier<T> supplier, Class<U> class1) {
		return impl.from(supplier).to(class1);
	}
}
