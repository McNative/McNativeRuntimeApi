package org.mcnative.runtime.api.player.client;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.type.DocumentFileType;

import java.nio.charset.StandardCharsets;

public class MessageDataConverter {

    public static String toString(byte[] data){
        return new String(data, StandardCharsets.UTF_8);
    }

    public static Document toDocument(byte[] data){
        return DocumentFileType.JSON.getReader().read(toString(data));
    }

}
