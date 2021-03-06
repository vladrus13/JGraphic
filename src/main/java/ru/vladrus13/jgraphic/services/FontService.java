package ru.vladrus13.jgraphic.services;

import ru.vladrus13.jgraphic.exception.AppException;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author vladkuznetsov
 * Font service
 */
public class FontService {
    /**
     * Fonts map
     */
    private static final Map<String, Font> fonts = new HashMap<>();

    /**
     * Create font
     *
     * @param path path to file
     * @return {@link Font}
     * @throws AppException if we can't find file, or this is incorrect for this OS font
     */
    private static Font createFontFromFile(String path) throws AppException {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(FontService.class.getResourceAsStream("/font/" + path + ".ttf")));
        } catch (FontFormatException | IOException e) {
            throw new AppException("Error on loading font: " + path, e);
        }
    }

    /**
     * Get font from created
     *
     * @param name name of font
     * @return {@link Font}
     * @throws AppException if we not found this font and choose create this, but on creating we 1. Can't find file. 2. Incorrect for this OS font
     */
    public static Font getFont(String name) throws AppException {
        if (!fonts.containsKey(name)) {
            fonts.put(name, createFontFromFile(name));
        }
        return fonts.get(name);
    }

    /**
     * Get font from created
     *
     * @param name name of font
     * @param size size of font
     * @return {@link Font}
     * @throws AppException if we not found this font and choose create this, but on creating we 1. Can't find file. 2. Incorrect for this OS font
     */
    public static Font getFont(String name, int size) throws AppException {
        return getFont(name).deriveFont(Font.PLAIN, size);
    }

    /**
     * Get font width
     *
     * @param text text
     * @param font {@link Font}
     * @return width
     */
    public static int fontWidth(String text, Font font) {
        return AppService.getApp().getFontMetrics(font).stringWidth(text);
    }

    /**
     * Split text by width
     *
     * @param text  text
     * @param font  {@link Font}
     * @param width width
     * @return array of text
     */
    public static ArrayList<String> splitByWidth(String text, Font font, long width) {
        // TODO better
        ArrayList<String> answer = new ArrayList<>();
        int current = 0, last = 0;
        while (current != text.length()) {
            if (fontWidth(text.substring(last, current), font) > width) {
                answer.add(text.substring(last, current - 1));
                last = current - 1;
            }
            current++;
        }
        answer.add(text.substring(last, current));
        return answer;
    }
}
