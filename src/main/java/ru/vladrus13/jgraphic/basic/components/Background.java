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
     * Filler class
     */
    private final Filler filler;

    /**
     * Constructor for Background with image
     *
     * @param name   system name of frame
     * @param start  start position
     * @param size   size
     * @param image  image
     * @param parent parent for this frame
     */
    @Deprecated
    public Background(String name, Point start, Size size, BufferedImage image, Frame parent) {
        super(name, start, size, parent);
        filler = new Filler(image);
        recalculateChildes();
    }

    public Background(String name, Filler filler, Frame parent) {
        super(name, parent);
        this.filler = filler;
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
    @Deprecated
    public Background(String name, Point start, Size size, Color color, Frame parent) {
        super(name, start, size, parent);
        filler = new Filler(color);
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
        this.filler = filler;
        recalculateChildes();
    }

    /**
     * Parent fill background
     *
     * @param name   system name of frame
     * @param color  color
     * @param parent parent
     */
    @Deprecated
    public Background(String name, Color color, Frame parent) {
        super(name, parent.getStart(), parent.getSize(), parent);
        this.filler = new Filler(color);
        recalculateChildes();
    }

    @Override
    public void nonCheckingDraw(Graphics graphics) {
        if (filler != null) {
            if (filler.getBufferedImage() != null) {
                graphics.drawImage(filler.getBufferedImage(), start.x, start.y, size.x, size.y);
            }
            if (filler.getColor() != null) {
                graphics.setColor(filler.getColor());
                graphics.fillRect(start.x, start.y, size.x, size.y);
            }
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

    public Background copy() {
        if (ratioStart != null && ratioSize != null) {
            return new Background(name, ratioStart.copy(), ratioSize.copy(), filler, parent);
        }
        if (start != null && size != null) {
            return new Background(name, start.copy(), size.copy(), filler, parent);
        }
        return new Background(name, filler, parent);
    }
}
