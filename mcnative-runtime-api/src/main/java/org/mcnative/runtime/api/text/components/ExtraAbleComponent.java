package org.mcnative.runtime.api.text.components;

import java.util.Collection;

public interface ExtraAbleComponent<T extends ExtraAbleComponent<?>> extends MessageComponent<T> {

    Collection<MessageComponent<?>> getExtras();

    T addExtra(MessageComponent<?> component);

    T removeExtra(MessageComponent<?> component);

}
