package org.mcnative.runtime.api.stream;

import java.util.Spliterator;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Optional<T> {

    T get();

    T getOrNull();

    T getOrElse(T defaultValue);

    T getOrElse(Spliterator<T> defaultValue);

    T getOrElseThrow();


    boolean isEmpty();

    boolean isPresent();


    <U> U map(Function<T,U> mapper);


    CompletableFuture<T> getAsync();

    void getAsync(Consumer<T> callback);


}
