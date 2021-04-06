package ru.vladrus13.jgraphic.basic.event.impl;

import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.event.Event;

import java.util.function.Consumer;

public class FrameConsumerEvent extends Event {
    public final Consumer<? extends Frame> consumer;
    public final Class<? extends Frame> input;

    public FrameConsumerEvent(Consumer<? extends Frame> consumer, Class<? extends Frame> input) {
        this.consumer = consumer;
        this.input = input;
    }
}
