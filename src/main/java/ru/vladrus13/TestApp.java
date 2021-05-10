package ru.vladrus13;

import ru.vladrus13.graphic.PCGraphicsAWTImpl;
import ru.vladrus13.jgraphic.App;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;

import java.awt.*;

/**
 * @author vladrus13 on 23.03.2021
 **/
public class TestApp extends App {

    public TestApp() {
        super(1000, 1000);
        System.out.println("Run class: Test");
        current = new TestFrame("Frame", new Point(0, 0, CoordinatesType.REAL), new Size(1000, 1000, CoordinatesType.REAL), null);
        painter();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (current != null) {
            current.draw(new PCGraphicsAWTImpl(g));
        }
    }
}
