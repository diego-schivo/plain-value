package it.plainvalue;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

interface PlainValue {

	Impl impl = new Impl();

	static String substringBeforeLast(String str, int ch) {
		return impl.substringBeforeLast(str, ch);
	}

	static String substringAfterLast(String str, int ch) {
		return impl.substringAfterLast(str, ch);
	}

	static Iterable<String> split(String str, int ch) {
		return impl.split(str, ch);
	}

	@SuppressWarnings("unchecked")
	static <T> T[] array(T... elements) {
		return impl.array(elements);
	}

	static <T> Stream<T> stream(Iterable<T> iterable) {
		return impl.stream(iterable);
	}

	static <T, U> U convert(T[] array, Class<U> class1) {
		return impl.convert(array, class1);
	}

	static <T, U> U convert(Stream<T> stream, Class<U> class1) {
		return impl.convert(stream, class1);
	}

	static <T> T unsafeGet(ThrowSupplier<T> supplier) {
		return impl.unsafeGet(supplier);
	}

	static <T> Iterable<T> iterable(T first, Function<T, T> function) {
		return impl.iterable(first, function);
	}

	static <T, U> T find(T start, Iterable<U> path, BiFunction<T, U, T> next) {
		return impl.find(start, path, next);
	}
}
