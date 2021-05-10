package ru.vladrus13.jgraphic.basic;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Focus class.
 * This class is for those objects, who can accept updates for keyboard or mouse
 */
public interface FrameListener extends KeyListener, MouseListener, ActionListener {
}
