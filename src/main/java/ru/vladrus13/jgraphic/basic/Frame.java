package ru.vladrus13.jgraphic.basic;


import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.utils.Ratio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * The most important class.
 * Denotes some rectangle that can be drawn and can accept commands from keyboard and mouse.
 * This rectangle has its size and is set to the top left point.
 * These coordinates can be pixel-by-pixel (drawn on the screen) and relative to the parent.
 */
public abstract class Frame extends Drawn implements Focus {

    /**
     * Real start position on screen
     */
    protected Point start;
    /**
     * Real size on screen
     */
    protected Size size;
    /**
     * Ratio start position. Can be null if constructor contain real position
     */
    protected Point ratioStart;
    /**
     * Ratio size. Can be null if constructor contain real size
     */
    protected Size ratioSize;
    /**
     * Parent frame. Can be null if no parents are given
     */
    protected Frame parent;
    /**
     * Type of start position
     */
    protected CoordinatesType startType;
    /**
     * Type of size
     */
    protected CoordinatesType sizeType;
    /**
     * Focused class. The top deque is called on keyboard or mouse events
     */
    protected final Deque<Frame> focused = new LinkedList<>();
    /**
     * Logger class
     */
    protected final Logger logger = Logger.getLogger(Frame.class.getName());
    protected final String name;
    protected final Collection<Frame> childes;

    /**
     * Standard constructor for Frame
     *
     * @param name   system name of frame
     * @param start  start position for frame
     * @param size   frame size
     * @param parent parent frame
     */
    public Frame(String name, Point start, Size size, Frame parent) {
        this.name = name;
        this.parent = parent;
        childes = new ArrayList<>();
        setFrame(size, start);
        recalculate();
    }

    public Frame(String name, Frame parent) {
        this.name = name;
        this.parent = parent;
        childes = new ArrayList<>();
    }

    public void setFrame(Size size, Point start) {
        if (start != null) {
            if (start.coordinatesType == CoordinatesType.REAL) {
                this.start = start;
                this.ratioStart = null;
            } else {
                this.ratioStart = start;
                this.start = null;
            }
            startType = start.coordinatesType;
        }
        if (size != null) {
            if (size.coordinatesType == CoordinatesType.REAL) {
                this.size = size;
                this.ratioSize = null;
            } else {
                this.ratioSize = size;
                this.size = null;
            }
            sizeType = size.coordinatesType;
        }
        recalculate();
    }

    /**
     * If there are some changes in the size of the windows, then this method is called
     */
    public void recalculate() {
        if (startType == CoordinatesType.RATIO && parent != null && parent.start != null) {
            start = Ratio.getPoint(parent.start, parent.size, ratioStart);
        }
        if (sizeType == CoordinatesType.RATIO && parent != null && parent.size != null) {
            size = Ratio.getSize(parent.size, ratioSize);
        }
        recalculateChildes();
    }

    /**
     * Recalculate all childes
     */
    public void recalculateChildes() {
        for (Frame frame : childes) {
            frame.recalculate();
        }
    }

    /**
     * If there are some changes in the size of the windows, then this method is called
     *
     * @param width  new width of frame
     * @param height new height of frame
     */
    public void recalculate(int width, int height) {
        size = new Size(width, height, CoordinatesType.REAL);
        recalculate();
    }

    /**
     * Set new parent for frame
     *
     * @param parent new parent
     */
    public void setParent(Frame parent) {
        this.parent = parent;
        recalculate();
    }

    /**
     * Getter for real position of frame
     *
     * @return real position of frame
     */
    public Point getStart() {
        return start;
    }

    /**
     * Getter for frame size
     *
     * @return frame size
     */
    public Size getSize() {
        return size;
    }

    /**
     * Add new focused on top of deque
     *
     * @param focused new focused
     */
    public void addFocused(Frame focused) {
        this.focused.addFirst(focused);
    }

    /**
     * Remove focused from top of deque
     */
    public void removeFocused() {
        this.focused.removeFirst();
    }

    /**
     * Remove focused from top of deque with equality check
     *
     * @param frame which should be on top of deque
     * @throws GameException if the wrong frame is on top of the deque
     */
    public void removeFocused(Frame frame) throws GameException {
        if (this.focused.getFirst() != frame) {
            throw new GameException("Current focused frame not equal removed");
        }
        removeFocused();
    }

    public void addChild(Frame frame) {
        childes.add(frame);
    }

    public void removeChild(Frame frame) {
        childes.remove(frame);
    }
}
