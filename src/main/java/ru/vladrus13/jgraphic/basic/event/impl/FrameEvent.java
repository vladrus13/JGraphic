package ru.vladrus13.jgraphic.basic.event.impl;

import ru.vladrus13.jgraphic.basic.event.Event;

public class FrameEvent extends Event {
    public static final int CLOSE_FRAME = 1;

    public final int event;

    public FrameEvent(int event) {
        this.event = event;
    }
}
