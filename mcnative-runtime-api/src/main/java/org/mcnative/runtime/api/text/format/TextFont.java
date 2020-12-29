package org.mcnative.runtime.api.text.format;

import java.util.ArrayList;
import java.util.List;

public class TextFont {

    private final static List<TextFont> DEFAULT_FONTS = new ArrayList<>();

    public static TextFont DEFAULT = createDefault("minecraft");

    public static TextFont UNIFORM = createDefault("uniform");

    private final String name;

    private TextFont(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<TextFont> getDefaultFonts(){
        return DEFAULT_FONTS;
    }

    public static TextFont make(String name){
        return new TextFont(name);
    }

    private static TextFont createDefault(String name){
        TextFont textFont = new TextFont(name);
        DEFAULT_FONTS.add(textFont);
        return textFont;
    }

}
