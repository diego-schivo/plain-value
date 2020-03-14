package it.plainvalue;

import java.util.function.Supplier;

public class PlainValue {

	protected static Impl impl = new Impl();

	@SuppressWarnings("unchecked")
	public static <T> T[] array(T... elements) {
		return elements;
	}

	@SuppressWarnings("unchecked")
	public static Iterable<String> split(String str, int ch) {
		return impl.from(impl.split(str, ch)).to(Iterable.class);
	}

	public static <T, U> U convert(T[] array, Class<U> class1) {
		return impl.from(array).to(class1);
	}

	public static <T, U> U convert(Supplier<T> supplier, Class<U> class1) {
		return impl.from(supplier).to(class1);
	}
}
