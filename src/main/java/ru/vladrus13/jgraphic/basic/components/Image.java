package ru.vladrus13.jgraphic.basic.components;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.event.returned.ReturnEvent;
import ru.vladrus13.jgraphic.basic.event.returned.ReturnInt;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.resources.ImageLoader;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

/**
 * Image class. Used for just draw a image
 *
 * @author vladrus13
 */
public class Image extends Frame {

    /**
     * Image
     */
    private final BufferedImage image;

    /**
     * Classic constructor for frame
     *
     * @param name   system name of frame
     * @param start  start position for image
     * @param size   size of image
     * @param path   path FROM folder of graphic resources
     * @param parent parent frame
     * @throws GameException if image can't be loaded
     */
    public Image(String name, Point start, Size size, Path path, Frame parent) throws GameException {
        super(name, start, size, parent);
        image = ImageLoader.load(path);
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        graphics.drawImage(image, start.x, start.y, size.x, size.y);
    }

    @Override
    public ReturnEvent keyPressed(KeyEvent e) {
        return new ReturnInt(ReturnInt.NOTHING);
    }

    @Override
    public ReturnEvent mousePressed(MouseEvent e) {
        return new ReturnInt(ReturnInt.NOTHING);
    }

    @Override
    public void recalculateChildes() {

    }
}
