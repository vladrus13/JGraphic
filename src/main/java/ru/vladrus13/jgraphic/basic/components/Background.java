package ru.vladrus13.jgraphic.basic.components;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.basic.event.returned.IntEvent;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Background class. You can fill it from image or color
 *
 * @author vladrus13
 */
public class Background extends Frame {

    /**
     * Is null if a color is selected
     */
    private final BufferedImage image;
    /**
     * Is null if a image is selected
     */
    private final Color color;

    /**
     * Constructor for Background with image
     *
     * @param name   system name of frame
     * @param start  start position
     * @param size   size
     * @param image  image
     * @param parent parent for this frame
     */
    public Background(String name, Point start, Size size, BufferedImage image, Frame parent) {
        super(name, start, size, parent);
        this.image = image;
        color = null;
        recalculateChildes();
    }

    /**
     * Constructor for Background with color
     *
     * @param name   system name of frame
     * @param start  start position
     * @param size   size
     * @param color  color
     * @param parent parent for this frame
     */
    public Background(String name, Point start, Size size, Color color, Frame parent) {
        super(name, start, size, parent);
        this.color = color;
        image = null;
        recalculateChildes();
    }

    /**
     * Constructor for Background with color
     *
     * @param name   system name of frame
     * @param start  start position
     * @param size   size
     * @param filler filler
     * @param parent parent for this frame
     */
    public Background(String name, Point start, Size size, Filler filler, Frame parent) {
        super(name, start, size, parent);
        if (filler.getBufferedImage() == null) {
            this.color = filler.getColor();
            this.image = null;
        } else {
            this.image = filler.getBufferedImage();
            this.color = null;
        }
        recalculateChildes();
    }

    /**
     * Parent fill background
     *
     * @param name   system name of frame
     * @param color  color
     * @param parent parent
     */
    public Background(String name, Color color, Frame parent) {
        super(name, parent.getStart(), parent.getSize(), parent);
        this.color = color;
        this.image = null;
        recalculateChildes();
    }

    @Override
    public void nonCheckingDraw(Graphics graphics) {
        if (color != null) {
            graphics.setColor(color);
            graphics.fillRect(start.x, start.y, size.x, size.y);
        }
        if (image != null) {
            graphics.drawImage(image, start.x, start.y, size.x, size.y);
        }
    }

    @Override
    public ru.vladrus13.jgraphic.basic.event.Event keyPressed(KeyEvent e) {
        return new IntEvent(IntEvent.NOTHING);
    }

    @Override
    public Event mousePressed(MouseEvent e) {
        return new IntEvent(IntEvent.NOTHING);
    }

    @Override
    public void recalculateChildes() {

    }
}
