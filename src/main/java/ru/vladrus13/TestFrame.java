package ru.vladrus13;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.components.Choose;
import ru.vladrus13.jgraphic.basic.components.Filler;
import ru.vladrus13.jgraphic.basic.components.Text;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.basic.event.returned.IntEvent;
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

    private final Choose choose;

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
        Choose choose1;
        try {
            choose1 = Choose.getInstance("choose", 3, new Point(0, 0, CoordinatesType.RATIO),
                    new Size(1000, 1000, CoordinatesType.RATIO), this, new Size(800, 100, CoordinatesType.RATIO),
                    new Filler[]{new Filler(Color.RED), new Filler(Color.RED), new Filler(Color.RED)},
                    new Filler[]{new Filler(Color.BLUE), new Filler(Color.BLUE), new Filler(Color.BLUE)},
                    new String[]{"Ahh1", "AHAHAHAHA", "FINISH394853485"},
                    "Inventory", new Size(300, 0, CoordinatesType.RATIO), Color.BLACK, Text.TextAlign.CENTER,
                    new ru.vladrus13.jgraphic.basic.event.Event[]{new IntEvent(IntEvent.NOTHING), new IntEvent(IntEvent.NOTHING), new IntEvent(IntEvent.NOTHING)},
                    new Event[]{new IntEvent(IntEvent.NOTHING), new IntEvent(IntEvent.NOTHING), new IntEvent(IntEvent.NOTHING)});
        } catch (GameException e) {
            e.printStackTrace();
            choose1 = null;
        }
        choose = choose1;
        recalculateChildes();
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        if (choose != null) {
            choose.draw(graphics);
        }
    }

    @Override
    public void recalculateChildes() {
        if (choose != null) {
            choose.recalculate();
        }
    }

    @Override
    public ru.vladrus13.jgraphic.basic.event.Event keyPressed(KeyEvent e) {
        return null;
    }

    @Override
    public ru.vladrus13.jgraphic.basic.event.Event mousePressed(MouseEvent e) {
        return null;
    }
}
