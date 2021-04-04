package ru.vladrus13.jgraphic.basic.components;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.basic.event.impl.IntEvent;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.services.FontService;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Text class. Using for drawing text on screen
 */
public class Text extends Frame {

    @Override
    public ru.vladrus13.jgraphic.basic.event.Event keyPressed(KeyEvent e) {
        return new IntEvent(IntEvent.NOTHING);
    }

    @Override
    public Event mousePressed(MouseEvent e) {
        return new IntEvent(IntEvent.NOTHING);
    }

    /**
     * Text align. Helper class for {@link Text}
     */
    public enum TextAlign {
        /**
         * Center align enum
         */
        CENTER,
        /**
         * Left align enum
         */
        LEFT,
        /**
         * Right align enum
         */
        RIGHT
    }

    /**
     * Text
     */
    private final String text;
    private String[] splitText;
    private Point[] splitTextHeight;
    /**
     * Font for text
     *
     * @see Font
     */
    private Font font;
    /**
     * Color for text
     *
     * @see Color
     */
    private final Color color;
    /**
     * Text align
     *
     * @see TextAlign
     */
    private final TextAlign textAlign;
    /**
     * Font size. Only need x-axis and type
     */
    private final Size fontSize;

    /**
     * Classic frame constructor for Text class. The text is contained in a rectangle given by size and start
     *
     * @param name      system name of frame
     * @param start     start position of text
     * @param size      size position of text
     * @param text      text
     * @param nameFont  name of font. An attempt will be made to find it using the FontService
     * @param fontSize  size of font. Only need x-axis and type
     * @param color     color of text.
     * @param textAlign text align
     * @param parent    parent frame
     * @throws GameException if we can't load a font
     * @see Color
     * @see Font
     * @see TextAlign
     */
    public Text(String name, Point start, Size size, String text, String nameFont, Size fontSize, Color color, TextAlign textAlign, Frame parent) throws GameException {
        super(name, start, size, parent);
        this.text = text;
        this.color = color;
        this.font = FontService.getFont(nameFont,
                (int) (fontSize.coordinatesType == CoordinatesType.RATIO ? ((fontSize.x * size.y) / 1000f) : fontSize.x));
        this.textAlign = textAlign;
        this.fontSize = fontSize;
        recalculateChildes();
    }

    public Text(String name, String text, String nameFont, Size fontSize, Color color, TextAlign textAlign, Frame parent) throws GameException {
        super(name, parent);
        this.text = text;
        this.color = color;
        this.font = FontService.getFont(nameFont);
        this.textAlign = textAlign;
        this.fontSize = fontSize;
    }

    @Override
    public void nonCheckingDraw(Graphics graphics) {
        graphics.setColor(color);
        graphics.setFont(font);
        for (int i = 0; i < splitText.length; i++) {
            graphics.drawString(splitText[i], splitTextHeight[i].x, splitTextHeight[i].y);
        }
    }

    @Override
    public void recalculateChildes() {
        // TODO make two or more strings on one text
        if (text != null && fontSize != null && size != null) {
            if (fontSize.coordinatesType == CoordinatesType.RATIO) {
                float newSizeFont = (fontSize.x * size.y) / 1000f;
                font = font.deriveFont(newSizeFont);
            }
            splitText = FontService.splitByWidth(text, font, size.x).toArray(String[]::new);
            splitTextHeight = new Point[splitText.length];
            int textHeight = font.getSize();
            int textStartPosition = textHeight;
            for (int position = 0; position < splitText.length; position++) {
                String split = splitText[position];
                int textWidth = FontService.fontWidth(split, font);
                int textHeightStart = textStartPosition;
                textStartPosition += textHeight;
                if (textStartPosition > size.y) {
                    logger.warning("Text size greater than size of panel: y");
                }
                if (textWidth > size.x) {
                    logger.warning("Text size greater than size of panel: x");
                }
                int textWidthStart = -1;
                switch (textAlign) {
                    case LEFT:
                        textWidthStart = 0;
                        break;
                    case RIGHT:
                        textWidthStart = (int) (size.x - textWidth);
                        break;
                    case CENTER:
                        textWidthStart = (int) (size.x / 2) - textWidth / 2;
                        break;
                }
                splitTextHeight[position] = new Point(start.x + textWidthStart, start.y + textHeightStart, CoordinatesType.REAL);
            }
        }
    }
}
