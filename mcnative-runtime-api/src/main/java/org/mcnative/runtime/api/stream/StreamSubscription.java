package org.mcnative.runtime.api.stream;

import java.util.function.Consumer;

public interface StreamSubscription<T> {

    StreamOptional<T> getStream();

    Consumer<T> getCallback();

    void cancel();


}
