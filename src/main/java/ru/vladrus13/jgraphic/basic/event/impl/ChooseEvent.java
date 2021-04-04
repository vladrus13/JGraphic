package ru.vladrus13.jgraphic.basic.event.impl;

import ru.vladrus13.jgraphic.basic.event.Event;

public class ChooseEvent extends Event {
    public static final int END_CHOOSE = 1;

    public final int event;

    public ChooseEvent(int event) {
        this.event = event;
    }
}
