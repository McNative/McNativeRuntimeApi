package org.mcnative.runtime.api.stream;

import java.util.function.Consumer;
import java.util.function.Function;

public interface SteamOptional<T> extends Optional<T>{

    boolean subscribe(Consumer<T> callback);


    <U> boolean subscribeMapped(Function<T,U> mapper,Consumer<U> callback);


    boolean unsubscribe(Consumer<T> callback);


}
