package ru.vladrus13.jgraphic.basic.components;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.UpdatedFrame;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class EmptyFrame extends UpdatedFrame {

    public EmptyFrame(String name, Frame parent) {
        super(name, parent);
    }

    public EmptyFrame(String name, Point start, Size size, Frame parent) {
        super(name, start, size, parent);
    }

    @Override
    protected void nonCheckingUpdate(long delay) {
        for (Frame child : childes) {
            if (child instanceof UpdatedFrame) {
                ((UpdatedFrame) child).update(delay);
            }
        }
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        for (Frame child : childes) {
            child.draw(graphics);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!focused.isEmpty()) {
            focused.getFirst().keyPressed(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!focused.isEmpty()) {
            focused.getFirst().mousePressed(e);
        }
    }
}
