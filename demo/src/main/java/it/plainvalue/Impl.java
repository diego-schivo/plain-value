package it.plainvalue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class Impl {

	String substringBeforeLast(String str, int ch) {
		if (str == null) {
			return null;
		}
		int index = str.lastIndexOf(ch);
		if (index == -1) {
			return null;
		}
		return str.substring(0, index);
	}

	String substringAfterLast(String str, int ch) {
		if (str == null) {
			return null;
		}
		int index = str.lastIndexOf(ch);
		if (index == -1) {
			return null;
		}
		return str.substring(index + 1);
	}

	Iterable<String> split(String str, int ch) {
		if (str == null) {
			return null;
		}
		return () -> new Iterator<String>() {

			int fromIndex;

			@Override
			public boolean hasNext() {
				return fromIndex <= str.length();
			}

			@Override
			public String next() {
				if (fromIndex > str.length()) {
					throw new NoSuchElementException();
				}
				int toIndex = str.indexOf(ch, fromIndex);
				if (toIndex == -1) {
					toIndex = str.length();
				}
				String substr = str.substring(fromIndex, toIndex);
				fromIndex = toIndex + 1;
				return substr;
			}
		};
	}

	@SuppressWarnings("unchecked")
	<T> T[] array(T... elements) {
		return elements;
	}

	<T> Stream<T> stream(Iterable<T> iterable) {
		if (iterable == null) {
			return Stream.empty();
		}
		return StreamSupport.stream(iterable.spliterator(), false);
	}

	<T, U> U convert(T[] array, Class<U> class1) {
		return from(array).to(class1);
	}

	<T, U> U convert(Stream<T> stream, Class<U> class1) {
		return from(stream).to(class1);
	}

	<T> T unsafeGet(ThrowSupplier<T> supplier) {
		try {
			return supplier.get();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	<T> Iterable<T> iterable(T first, Function<T, T> function) {
		Objects.requireNonNull(function);
		return () -> new Iterator<T>() {
			T element = first;

			@Override
			public boolean hasNext() {
				return element != null;
			}

			@Override
			public T next() {
				if (element == null) {
					throw new NoSuchElementException();
				}
				T next = element;
				element = function.apply(element);
				return next;
			}
		};
	}

	<T, U> T find(T start, Iterable<U> path, BiFunction<T, U, T> next) {
		Objects.requireNonNull(next);
		if (path == null) {
			return null;
		}
		T t = start;
		for (U u : path) {
			t = next.apply(t, u);
			if (t == null) {
				break;
			}
		}
		return t;
	}

	<T> To<?> from(T[] array) {
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
					return (U) new Iterator<T>() {

						int index;

						@Override
						public boolean hasNext() {
							return index < array.length;
						}

						@Override
						public T next() {
							if (index >= array.length) {
								throw new NoSuchElementException();
							}
							T element = array[index];
							index++;
							return element;
						}
					};
				}
				if (Objects.equals(class1, Iterable.class)) {
					return (U) (Iterable<T>) () -> (Iterator<T>) to(Iterator.class);
				}
				return null;
			}
		};
	}

	<T> To<?> from(Stream<T> stream) {
		return new To<T>() {

			@Override
			@SuppressWarnings("unchecked")
			public <U> U to(Class<U> class1) {
				if (stream == null) {
					return null;
				}
				if (Objects.equals(class1, Iterator.class)) {
					return (U) stream.iterator();
				}
				if (Objects.equals(class1, Iterable.class)) {
					return (U) (Iterable<T>) () -> (Iterator<T>) to(Iterator.class);
				}
				return null;
			}
		};
	}

	interface To<T> {
		<U> U to(Class<U> class1);
	}
}
