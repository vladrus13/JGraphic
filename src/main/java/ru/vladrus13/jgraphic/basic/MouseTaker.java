package ru.vladrus13.jgraphic.basic;

import java.awt.event.MouseEvent;

/**
 * A class that mean this object can accept requests from the mouse
 */
public interface MouseTaker {
    /**
     * Get request from mouse
     *
     * @param e request from mouse
     */
    void mousePressed(MouseEvent e);
}
