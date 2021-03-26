package ru.vladrus13;

import ru.vladrus13.graphic.PCGraphicsAWTImpl;
import ru.vladrus13.jgraphic.App;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.services.AppService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author vladrus13 on 23.03.2021
 **/
public class TestApp extends App {

    public TestApp() {
        super(1000, 1000);
        System.out.println("Run class: Test");
        current = new TestFrame("Frame", new Point(0,0, CoordinatesType.REAL), new Size(1000, 1000, CoordinatesType.REAL), null);
        painter();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (current != null) {
            current.draw(new PCGraphicsAWTImpl(g));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
