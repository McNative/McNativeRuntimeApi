/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 09.07.20, 21:32
 * @web %web%
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

package org.mcnative.common.text;

import net.pretronic.libraries.utility.Validate;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.components.MessageComponentSet;
import org.mcnative.common.text.format.TextColor;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.List;

public class ImageText {

    private final char imageCharacter;
    private final TextColor[][] colors;
    private final Object[] lineExtensions;
    private final int padding;

    private ImageText(BufferedImage image, int height, int padding, char imageCharacter) {
        this.imageCharacter = imageCharacter;
        this.colors = toTextColorArray(image, height);
        this.lineExtensions = new Object[colors.length+(padding*2)];
        this.padding = padding;
    }

    private TextColor[][] toTextColorArray(BufferedImage image, int height) {
        double ratio = image.getHeight() / (double)image.getWidth();
        BufferedImage resized = resizeImage(image, (int)(height / ratio), height);
        TextColor[][] chatImg = new TextColor[resized.getWidth()][resized.getHeight()];

        for(int x = 0; x < resized.getWidth(); x++) {
            for(int y = 0; y < resized.getHeight(); y++) {
                int rgb = resized.getRGB(x, y);
                TextColor closest = TextColor.make(new Color(rgb, true));
                chatImg[x][y] = closest;
            }
        }

        return chatImg;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        AffineTransform af = new AffineTransform();
        af.scale(width / (double) originalImage.getWidth(), height / (double) originalImage.getHeight());
        AffineTransformOp operation = new AffineTransformOp(af, 1);
        return operation.filter(originalImage, null);
    }

    public int getLineCount(){
        return colors.length;
    }

    public ImageText addTextExtension(int line,MessageComponent<?> text){
        Validate.notNull(text);
        this.lineExtensions[line] = text;
        return this;
    }

    public ImageText addTextExtension(List<MessageComponent<?>> extensions){
        if(extensions.size() != colors.length){
            throw new IllegalArgumentException("Invalid length");
        }
        int index = 0;
        for (MessageComponent<?> extension : extensions) {
            this.lineExtensions[index] = extension;
            index++;
        }
        return this;
    }

    public MessageComponent<?>[] buildLines() {
        String character = String.valueOf(imageCharacter);
        MessageComponent<?>[] components = new MessageComponent<?>[lineExtensions.length];

        int end = colors.length+padding;
        for (int i = 0; i < components.length; i++) {
            Object lineExtension = lineExtensions[i];
            if(i < this.padding || i >= end){
                components[i] = (MessageComponent<?>) lineExtension;
            }else{
                TextColor[] colors = this.colors[i-padding];
                MessageComponentSet imageLine = new MessageComponentSet();
                int position = i-padding;
                for (TextColor[] color : this.colors) {
                    imageLine.add(Text.of(character, color[position]));
                }
               // for (TextColor color : colors) imageLine.add(Text.of(character,color));
                if(lineExtension != null){
                    imageLine.add(Text.of(" ",TextColor.WHITE));
                    imageLine.add((MessageComponent<?>) lineExtension);
                }
                components[i] = imageLine;
            }
        }
        return components;
    }

    public static ImageText compile(BufferedImage image, int height){
        return new ImageText(image,height,0,'█');
    }

    public static ImageText compile(BufferedImage image, int height, char imgChar){
        return new ImageText(image,height,0,imgChar);
    }

    public static ImageText compile(BufferedImage image, int height,int padding, char imgChar){
        return new ImageText(image,height,padding,imgChar);
    }

}
