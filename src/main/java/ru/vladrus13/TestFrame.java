package ru.vladrus13;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.components.Text;
import ru.vladrus13.jgraphic.basic.event.returned.ReturnEvent;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author vladrus13 on 23.03.2021
 **/
public class TestFrame extends Frame {

    private final Text text;

    /**
     * Standard constructor for Frame
     *
     * @param name   system name of frame
     * @param start  start position for frame
     * @param size   frame size
     * @param parent parent frame
     */
    public TestFrame(String name, Point start, Size size, Frame parent) {
        super(name, start, size, parent);
        Text text1;
        try {
            text1 = new Text("test_text",
                    new Point(0, 0, CoordinatesType.REAL),
                    new Size(400, 1000, CoordinatesType.RATIO),
                    "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello",
                    "Inventory",
                    new Size(50, 0, CoordinatesType.RATIO),
                    Color.BLACK, Text.TextAlign.CENTER, this);
        } catch (GameException e) {
            e.printStackTrace();
            text1 = null;
        }
        text = text1;
        recalculateChildes();
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        text.draw(graphics);
    }

    @Override
    public void recalculateChildes() {
        System.out.println("Recalculate");
        if (text != null) {
            text.recalculate();
        }
    }

    @Override
    public ReturnEvent keyPressed(KeyEvent e) {
        return null;
    }

    @Override
    public ReturnEvent mousePressed(MouseEvent e) {
        return null;
    }
}
