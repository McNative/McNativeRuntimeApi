package org.mcnative.runtime.api.service.inventory.item.data.book;

import org.mcnative.runtime.api.text.components.MessageComponent;

import java.util.List;

public interface BookItemData {

    String getTitle();

    boolean hasTitle();

    BookItemData setTitle(String title);


    String getAuthor();

    boolean hasAuthor();

    BookItemData setAuthor(String author);


    BookGeneration getGeneration();

    BookItemData setGeneration(BookGeneration generation);


    List<MessageComponent<?>> getPages();

    void setPage(int page, MessageComponent<?> content);

    void addPage(MessageComponent<?> content);
}
