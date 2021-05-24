package org.mcnative.runtime.api.text.components;

import java.util.Collection;

public interface ExtraAbleComponent<T extends ExtraAbleComponent<?>> {

    Collection<MessageComponent<?>> getExtras();

    T addExtra(MessageComponent<?> component);

    T removeExtra(MessageComponent<?> component);

}
