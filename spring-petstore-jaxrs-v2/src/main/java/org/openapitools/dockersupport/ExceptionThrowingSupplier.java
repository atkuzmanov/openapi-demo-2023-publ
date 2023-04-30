package org.openapitools.dockersupport;
@FunctionalInterface
public interface ExceptionThrowingSupplier<T, E extends Exception> {

    T get() throws E;
}
