package it.plainvalue;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class PlainValue {

	static Impl impl = new Impl();

	public static String substringBeforeLast(String str, int ch) {
		return impl.substringBeforeLast(str, ch);
	}

	public static String substringAfterLast(String str, int ch) {
		return impl.substringAfterLast(str, ch);
	}

	public static Iterable<String> split(String str, int ch) {
		return impl.split(str, ch);
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] array(T... elements) {
		return impl.array(elements);
	}

	public static <T> Stream<T> stream(Iterable<T> iterable) {
		return impl.stream(iterable);
	}

	public static <T, U> U convert(T[] array, Class<U> class1) {
		return impl.convert(array, class1);
	}

	public static <T, U> U convert(Stream<T> stream, Class<U> class1) {
		return impl.convert(stream, class1);
	}

	public static <T> T unsafeGet(ThrowSupplier<T> supplier) {
		return impl.unsafeGet(supplier);
	}

	public static <T> Iterable<T> iterable(T first, Function<T, T> function) {
		return impl.iterable(first, function);
	}

	public static <T, U> T find(T start, Iterable<U> path, BiFunction<T, U, T> next) {
		return impl.find(start, path, next);
	}
}
