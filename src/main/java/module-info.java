module ru.vladrus13.jgraphic {
    requires transitive java.desktop;
    requires transitive java.logging;
    exports ru.vladrus13.graphic;
    exports ru.vladrus13.jgraphic;
    exports ru.vladrus13.jgraphic.basic.animation;
    exports ru.vladrus13.jgraphic.basic.components;
    exports ru.vladrus13.jgraphic.basic.event;
    exports ru.vladrus13.jgraphic.basic.event.impl;
    exports ru.vladrus13.jgraphic.basic.event.impl.animation;
    exports ru.vladrus13.jgraphic.basic;
    exports ru.vladrus13.jgraphic.bean;
    exports ru.vladrus13.jgraphic.exception;
    exports ru.vladrus13.jgraphic.factory.components;
    exports ru.vladrus13.jgraphic.property;
    exports ru.vladrus13.jgraphic.resources;
    exports ru.vladrus13.jgraphic.services;
    exports ru.vladrus13.jgraphic.utils;
}