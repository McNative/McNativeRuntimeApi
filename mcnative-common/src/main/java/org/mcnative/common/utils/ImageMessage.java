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

package org.mcnative.common.utils;

import org.mcnative.common.text.Text;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.format.TextColor;
import org.mcnative.common.text.format.TextStyle;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

//@Todo optimize
public class ImageMessage {

    private final Color[] colors = {
            new Color(0, 0, 0),
            new Color(0, 0, 170),
            new Color(0, 170, 0),
            new Color(0, 170, 170),
            new Color(170, 0, 0),
            new Color(170, 0, 170),
            new Color(255, 170, 0),
            new Color(170, 170, 170),
            new Color(85, 85, 85),
            new Color(85, 85, 255),
            new Color(85, 255, 85),
            new Color(85, 255, 255),
            new Color(255, 85, 85),
            new Color(255, 85, 255),
            new Color(255, 255, 85),
            new Color(255, 255, 255) };

    private final String[] lines;

    public ImageMessage(BufferedImage image, int height, char imgChar) {
        TextColor[][] TextColors = toTextColorArray(image, height);
        this.lines = toImgMessage(TextColors, imgChar);
    }
    public ImageMessage(TextColor[][] TextColors, char imgChar) {
        this.lines = toImgMessage(TextColors, imgChar);
    }

    public ImageMessage(String... imgLines) {
        this.lines = imgLines;
    }

    public ImageMessage appendText(String... text) {
        for(int y = 0; y < this.lines.length; y++) {
            if(text.length > y) {
                String[] tmp16_12 = this.lines;
                tmp16_12[y] = (tmp16_12[y]+" "+text[y]);
            }
        }
        return this;
    }

    private TextColor[][] toTextColorArray(BufferedImage image, int height) {
        double ratio = image.getHeight() / image.getWidth();
        int width = (int)(height / ratio);
        if(width > 10) width = 10;
        BufferedImage resized = resizeImage(image, (int)(height / ratio), height);
        TextColor[][] chatImg = new TextColor[resized.getWidth()][resized.getHeight()];
        for(int x = 0; x < resized.getWidth(); x++) {
            for(int y = 0; y < resized.getHeight(); y++) {
                int rgb = resized.getRGB(x, y);
                TextColor closest = getClosestTextColor(new Color(rgb, true));
                chatImg[x][y] = closest;
            }
        }
        return chatImg;
    }

    private String[] toImgMessage(TextColor[][] colors, char imgchar) {
        String[] lines = new String[colors[0].length];
        for (int y = 0; y < colors[0].length; y++) {
            StringBuilder line = new StringBuilder();
            for (TextColor[] textColors : colors) {
                TextColor color = textColors[y];
                line.append(color != null ? textColors[y].toFormatCode() + imgchar : Character.valueOf(' '));
            }
            lines[y] = (line.toString() + TextStyle.RESET.toFormatCode());
        }
        return lines;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        AffineTransform af = new AffineTransform();
        af.scale(width / originalImage.getWidth(), height / originalImage.getHeight());
        AffineTransformOp operation = new AffineTransformOp(af, 1);
        return operation.filter(originalImage, null);
    }

    private double getDistance(Color c1, Color c2) {
        double rmean = (c1.getRed() + c2.getRed()) / 2.0D;
        double r = c1.getRed() - c2.getRed();
        double g = c1.getGreen() - c2.getGreen();
        int b = c1.getBlue() - c2.getBlue();
        double weightR = 2.0D + rmean / 256.0D;
        double weightG = 4.0D;
        double weightB = 2.0D + (255.0D - rmean) / 256.0D;
        return weightR * r * r + weightG * g * g + weightB * b * b;
    }

    private boolean areIdentical(Color c1, Color c2) {
        return (Math.abs(c1.getRed() - c2.getRed()) <= 5) &&
                (Math.abs(c1.getGreen() - c2.getGreen()) <= 5) &&
                (Math.abs(c1.getBlue() - c2.getBlue()) <= 5);
    }

    private TextColor getClosestTextColor(Color color) {
        if(color.getAlpha() < 128) return null;
        int index = 0;
        double best = -1.0D;
        for(int i = 0; i < this.colors.length; i++) if(areIdentical(this.colors[i], color)) return TextColor.values()[i];
        for(int i = 0; i < this.colors.length; i++){
            double distance = getDistance(color, this.colors[i]);
            if((distance < best) || (best == -1.0D)){
                best = distance;
                index = i;
            }
        }
        return TextColor.values()[index];
    }

    public String[] getLines() {
        return this.lines;
    }

    public MessageComponent<?>[] getLinesAsMessageComponent() {
        MessageComponent<?>[] components = new MessageComponent<?>[lines.length];
        for (int i = 0; i < lines.length; i++) {
            components[i] = Text.parse(lines[i]);
        }
        return components;
    }
}
