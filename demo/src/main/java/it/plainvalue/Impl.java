package it.plainvalue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Supplier;

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

	Supplier<String> split(String str, int ch) {
		if (str == null) {
			return null;
		}
		return new Supplier<String>() {

			int fromIndex;

			@Override
			public String get() {
				if (fromIndex > str.length()) {
					return null;
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
					return (U) new Iterable<T>() {

						@Override
						public Iterator<T> iterator() {
							return (Iterator<T>) to(Iterator.class);
						}
					};
				}
				return null;
			}
		};
	}

	<T> To<?> from(Supplier<T> supplier) {
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
				if (Objects.equals(class1, Iterable.class)) {
					return (U) new Iterable<T>() {

						@Override
						public Iterator<T> iterator() {
							return (Iterator<T>) to(Iterator.class);
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
