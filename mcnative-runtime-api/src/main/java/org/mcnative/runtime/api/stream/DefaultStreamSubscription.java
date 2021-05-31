package org.mcnative.runtime.api.stream;

import java.util.function.Consumer;

public class DefaultStreamSubscription<T> implements StreamSubscription<T> {

    private final StreamOptional<T> stream;
    private final Consumer<T> callback;

    protected DefaultStreamSubscription(StreamOptional<T> stream, Consumer<T> callback) {
        this.stream = stream;
        this.callback = callback;
    }

    @Override
    public StreamOptional<T> getStream() {
        return stream;
    }

    @Override
    public Consumer<T> getCallback() {
        return callback;
    }

    @Override
    public void cancel() {
        stream.unsubscribe(callback);
    }
}
