package ru.vladrus13.jgraphic.basic.components;

import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.basic.event.impl.CollectionEvent;
import ru.vladrus13.jgraphic.basic.event.impl.IntEvent;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Implementation of button class. Return Event if button activated. Use <b>Builder Pattern</b>
 *
 * @author vladrus13
 * @see Event
 * @see <a href="https://en.wikipedia.org/wiki/Builder_pattern">Builder pattern</a>
 */
public class ClassicButton extends Button {

    /**
     * Event if button activate
     */
    private Event keyEvent = new IntEvent(IntEvent.PRESSED);

    private Event mouseEvent = new IntEvent(IntEvent.PRESSED);

    /**
     * Classic frame constructor for button
     *
     * @param name   system name of frame
     * @param start  start position for button
     * @param size   size of button
     * @param parent parent frame
     */
    public ClassicButton(String name, Point start, Size size, Frame parent) {
        super(name, start, size, parent);
    }

    public ClassicButton(String name, Frame parent) {
        super(name, parent);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && keyEvent != null) {
            if (keyEvent instanceof CollectionEvent) {
                for (Event event : ((CollectionEvent) keyEvent).events) {
                    callEvent(event);
                }
            }
            callEvent(keyEvent);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Set event on activate button
     *
     * @param event event
     * @return this button
     */
    public ClassicButton setEventKey(Event event) {
        this.keyEvent = event;
        return this;
    }

    /**
     * Set event on activate click
     *
     * @param event event
     * @return this button
     */
    public ClassicButton setEventMouse(Event event) {
        this.mouseEvent = event;
        return this;
    }
}
