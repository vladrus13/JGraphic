package ru.vladrus13.jgraphic.basic.event.impl;

import ru.vladrus13.jgraphic.basic.event.Event;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionEvent extends Event {
    public final Collection<Event> events = new ArrayList<>();

    public void add(Event event) {
        events.add(event);
    }

    public void add(CollectionEvent event) {
        events.addAll(event.events);
    }
}
