package ru.vladrus13.jgraphic.basic.event.impl;

import ru.vladrus13.jgraphic.basic.event.Event;

public class IntEvent extends Event {
    public static final int NOTHING = 0;
    public static final int PRESSED = 1;
    public static final int TO_MENU = 101;
    public static final int TO_WORLD = 102;
    public static final int END_GAME = 103;

    private final int event;

    public IntEvent(int event) {
        this.event = event;
    }

    public int getEvent() {
        return event;
    }
}
