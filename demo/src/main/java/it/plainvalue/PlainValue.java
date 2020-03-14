package it.plainvalue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Supplier;

public class PlainValue {

	@SuppressWarnings("unchecked")
	public static <T> T[] array(T... elements) {
		return elements;
	}

	public static <T, U> U convert(T[] array, Class<U> class1) {
		return from(array).to(class1);
	}

	public static <T, U> U convert(Supplier<T> supplier, Class<U> class1) {
		return from(supplier).to(class1);
	}

	protected static <T> To<?> from(T[] array) {
		return new To<T>() {

			@Override
			@SuppressWarnings("unchecked")
			public <U> U to(Class<U> class1) {
				if (array == null) {
					return null;
				}
				if (Objects.equals(class1, Supplier.class)) {
					return (U) new Supplier<T>() {

						int index;

						@Override
						public T get() {
							if (index >= array.length) {
								return null;
							}
							T element = array[index];
							index++;
							return element;
						}
					};
				}
				if (Objects.equals(class1, Iterator.class)) {
					return (U) from(to(Supplier.class)).to(Iterator.class);
				}
				return null;
			}
		};
	}

	protected static <T> To<?> from(Supplier<T> supplier) {
		return new To<T>() {

			@Override
			@SuppressWarnings("unchecked")
			public <U> U to(Class<U> class1) {
				if (supplier == null) {
					return null;
				}
				if (Objects.equals(class1, Iterator.class)) {
					return (U) new Iterator<T>() {

						Boolean hasMoreElements;
						T element;

						@Override
						public boolean hasNext() {
							if (hasMoreElements == null) {
								element = supplier.get();
								hasMoreElements = (element != null);
							}
							return hasMoreElements;
						}

						@Override
						public T next() {
							if (!hasNext()) {
								throw new NoSuchElementException();
							}
							T next = element;
							hasMoreElements = null;
							element = null;
							return next;
						}
					};
				}
				return null;
			}
		};
	}

	interface To<T> {
		<U> U to(Class<U> class1);
	}
}