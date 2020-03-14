package it.plainvalue;

@FunctionalInterface
public interface ThrowSupplier<T> {

	T get() throws Exception;
}
