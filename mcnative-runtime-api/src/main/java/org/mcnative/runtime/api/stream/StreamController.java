package org.mcnative.runtime.api.stream;

import net.pretronic.libraries.utility.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class StreamController<T> implements StreamOptional<T> {

    private T value;
    private final List<StreamSubscription<T>> subscriptions;

    public StreamController(T value) {
        this.value = value;
        this.subscriptions = new ArrayList<>();
    }

    @Override
    public T get() {
        Validate.notNull(value);
        return value;
    }

    @Override
    public T getOrNull() {
        return value;
    }

    @Override
    public T getOrElse(T defaultValue) {
        return null;
    }

    @Override
    public T getOrElse(Spliterator<T> defaultValue) {
        return null;
    }

    @Override
    public T getOrElseThrow() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isPresent() {
        return false;
    }

    @Override
    public <U> U map(Function<T, U> mapper) {
        return null;
    }

    @Override
    public CompletableFuture<T> getAsync() {
        return null;
    }

    @Override
    public void getAsync(Consumer<T> callback) {

    }

    @Override
    public T get(Runnable runnable) {
        return null;
    }

    @Override
    public StreamSubscription<T> subscribe(Consumer<T> callback) {
        DefaultStreamSubscription<T> subscription = new DefaultStreamSubscription<>(this, callback);
        this.subscriptions.add(subscription);
        return subscription;
    }

    @Override
    public StreamSubscription<T> subscribeAndGet(Consumer<T> callback) {
        StreamSubscription<T> subscription = subscribe(callback);
        callback.accept(value);
        return subscription;
    }

    @Override
    public <U> StreamSubscription<T> subscribeMapped(Function<T, U> mapper, Consumer<U> callback) {
        return null;
    }

    @Override
    public <U> StreamSubscription<T> subscribeMappedAndGet(Function<T, U> mapper, BiConsumer<StreamSubscription<T>, U> callback) {
        return null;
    }

    @Override
    public void unsubscribe(Consumer<T> callback) {

    }

    @Override
    public void unsubscribe(StreamSubscription<T> subscription) {

    }

    public void setValue(T value) {
        this.value = value;
        for (StreamSubscription<T> subscription : this.subscriptions) {
            subscription.getCallback().accept(value);//@Async
        }
    }
}
