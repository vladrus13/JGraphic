package ru.vladrus13.jgraphic.basic.components;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author vladrus13 on 26.03.2021
 **/
public class Filler {
    private final Color color;
    private final BufferedImage bufferedImage;

    public Filler(Color color) {
        this.color = color;
        this.bufferedImage = null;
    }

    public Filler(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
        this.color = null;
    }

    public Color getColor() {
        return color;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
}
