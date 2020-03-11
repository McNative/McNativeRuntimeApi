/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 14.08.19, 19:53
 *
 * The McNative Project is under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.mcnative.common.network.messaging;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.type.DocumentFileType;
import net.pretronic.libraries.utility.io.IORuntimeException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class PluginMessageHelper {

    public static String getAsString(InputStream input){
        return new String(getAsBytes(input));
    }

    public static String getAsString(InputStream input, Charset charset){
        return new String(getAsBytes(input),charset);
    }

    public static Document getAsDocument(InputStream input){
        return DocumentFileType.BINARY.getReader().read(input);
    }

    public static byte[] getAsBytes(InputStream input){
        try {
            byte[] data = new byte[input.available()];
            input.read(data);
            return data;
        } catch (IOException exception) {
            throw new IORuntimeException(exception);
        }
    }


    public static byte[] fromString(String source){
        return source.getBytes();
    }

    public static byte[] fromString(String source,Charset charset){
        return source.getBytes(charset);
    }

    public static byte[] fromDocument(Document source){
        return DocumentFileType.BINARY.getWriter().write(source);
    }


    public void writeString(OutputStream stream, String source){
        try {
            stream.write(fromString(source));
        } catch (IOException exception) {
            throw new IORuntimeException(exception);
        }
    }

    public void writeString(OutputStream stream, String source, Charset charset){
        try {
            stream.write(fromString(source,charset));
        } catch (IOException exception) {
            throw new IORuntimeException(exception);
        }
    }

    public void writeDocument(OutputStream stream,Document source){
        DocumentFileType.BINARY.getWriter().write(stream,source,false);
    }

    public void writeStream(OutputStream output,InputStream input){
        byte[] buffer = new byte[1024];
        int count;
        try{
            while((count = input.read(buffer)) > 0){
                output.write(buffer,0,count);
            }
        }catch (IOException exception){
            throw new IORuntimeException(exception);
        }
    }
}
