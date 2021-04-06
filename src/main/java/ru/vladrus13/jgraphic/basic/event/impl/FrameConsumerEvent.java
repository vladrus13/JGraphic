package ru.vladrus13.jgraphic.basic.event.impl;

import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.event.Event;

import java.util.function.Consumer;

public class FrameConsumerEvent extends Event {
    public final Consumer<Frame> consumer;

    public FrameConsumerEvent(Consumer<Frame> consumer) {
        this.consumer = consumer;
    }
}
