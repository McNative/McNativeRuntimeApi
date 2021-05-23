package org.mcnative.runtime.api.stream;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public interface StreamOptional<T> extends Optional<T>{

    T get(Runnable runnable);
    StreamSubscription<T> subscribe(Consumer<T> callback);

    StreamSubscription<T> subscribeAndGet(Consumer<T> callback);

    <U> StreamSubscription<T> subscribeMapped(Function<T,U> mapper,Consumer<U> callback);

    <U> StreamSubscription<T> subscribeMappedAndGet(Function<T,U> mapper, BiConsumer<StreamSubscription<T>,U> callback);

    void unsubscribe(Consumer<T> callback);

    void unsubscribe(StreamSubscription<T> subscription);


    default boolean belongsTogether(StreamOptional<?> optional){
        return equals(optional);
    }

}
