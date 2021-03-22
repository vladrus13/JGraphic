package ru.vladrus13.jgraphic.basic;

import ru.vladrus13.jgraphic.basic.event.returned.ReturnEvent;

import java.awt.event.MouseEvent;

/**
 * A class that mean this object can accept requests from the mouse
 */
public interface MouseTaker {
    /**
     * Get request from mouse
     *
     * @param e request from mouse
     * @return response from request
     */
    ReturnEvent mousePressed(MouseEvent e);
}