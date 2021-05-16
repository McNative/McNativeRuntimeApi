package org.mcnative.runtime.api.stream;

import java.util.stream.Stream;

public interface StreamSubscription<T> {

    Stream<T> getStream();

    void cancel();

}
